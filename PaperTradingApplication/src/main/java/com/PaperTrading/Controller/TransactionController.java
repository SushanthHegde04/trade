package com.PaperTrading.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PaperTrading.Model.Transaction;
import com.PaperTrading.Model.User;
import com.PaperTrading.Service.Transactionservice;
import com.PaperTrading.Service.UserService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    Transactionservice ts;
    
    @Autowired
    UserService userService; // Service to handle user operations
    
    @GetMapping("/bought/{userid}")
    public List<Transaction> getInvestedStocks(@PathVariable int userid) {
        return ts.getAllTransactionsByUserId(userid);
    }
    
    @PostMapping("/buy")
    public ResponseEntity<?> buyStocks(@RequestBody Transaction data) {
        int userId = data.getUser().getUserId();
        int stockId = data.getStock().getStockId();
        int quantity = data.getQuantity();
        BigDecimal pricePerShare = data.getPricePerShare();
        
        BigDecimal totalCost = pricePerShare.multiply(BigDecimal.valueOf(quantity));

        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            BigDecimal currentBalance = user.getBalance();

            if (currentBalance.compareTo(totalCost) >= 0) {
                BigDecimal newBalance = currentBalance.subtract(totalCost);
                BigDecimal investedamt=user.getInvested().add(totalCost);
                user.setInvested(investedamt);
                user.setBalance(newBalance);
                userService.saveUser(user);

                Optional<Transaction> existingTransaction = ts.findtheuser(userId, stockId);
                if (existingTransaction.isPresent()) {
                    Transaction transaction = existingTransaction.get();
                    int newQuantity = transaction.getQuantity() + quantity;
                    transaction.setQuantity(newQuantity);
                    ts.saveTransaction(transaction);
                } else {
                    ts.saveTransaction(data);
                }

                return ResponseEntity.ok().body(Map.of("message", "Stock bought successfully.", "newBalance", newBalance));
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Insufficient balance to buy the stock."));
            }
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "User not found."));
        }
    }
    
    @PostMapping("/sell")
    public ResponseEntity<?> sellStocks(@RequestBody Transaction data) {
        int userId = data.getUser().getUserId();
        int stockId = data.getStock().getStockId();
        int quantity = data.getQuantity();
        BigDecimal pricePerShare = data.getPricePerShare();
        
        // Calculate the total price of the stocks being sold
        BigDecimal totalCost = pricePerShare.multiply(BigDecimal.valueOf(quantity));

        // Fetch the user to check their balance and update their investment
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            BigDecimal currentBalance = user.getBalance();

            // Fetch the existing transaction for this stock
            Optional<Transaction> existingTransaction = ts.findtheuser(userId, stockId);
            if (existingTransaction.isPresent()) {
                Transaction transaction = existingTransaction.get();

                // Check if the user has enough quantity to sell
                int currentQuantity = transaction.getQuantity();
                if (currentQuantity >= quantity) {
                    int newQuantity = currentQuantity - quantity;

                    // Update user balance and invested amount
                    BigDecimal newBalance = currentBalance.add(totalCost);
                    BigDecimal investedAmount = user.getInvested().subtract(totalCost);
                    user.setBalance(newBalance);
                    user.setInvested(investedAmount);
                    userService.saveUser(user);

                    // Update or delete the transaction based on the remaining quantity
                    if (newQuantity > 0) {
                        transaction.setQuantity(newQuantity);
                        ts.saveTransaction(transaction);
                    } else {
                        // Delete the transaction if no stocks remain
                        ts.deleteTransaction(transaction);
                    }

                    return ResponseEntity.ok().body(Map.of(
                        "message", "Stock sold successfully.",
                        "newBalance", newBalance
                    ));
                } else {
                    // Insufficient quantity to sell
                    return ResponseEntity.badRequest().body(Map.of("error", "Insufficient stock quantity to sell."));
                }
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "No stock transaction found for this stock."));
            }
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "User not found."));
        }
    }

    
    
    
}
