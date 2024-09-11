package com.PaperTrading.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PaperTrading.Model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUser_UserId(int userId);
    Optional<Transaction> findByUser_UserIdAndStock_StockId(int userId, int stockId);
}
