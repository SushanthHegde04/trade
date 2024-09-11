package com.PaperTrading.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PaperTrading.Model.Transaction;
import com.PaperTrading.Repository.TransactionRepo;

@Service
public class Transactionservice {
	@Autowired
	private TransactionRepo transactionRepository;

	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
	

	    public List<Transaction> getAllTransactionsByUserId(int userId) {
	        return transactionRepository.findAllByUser_UserId(userId);
	    }


		public Optional<Transaction> findtheuser(int userId, int stockId) {
			// TODO Auto-generated method stub
			return transactionRepository.findByUser_UserIdAndStock_StockId(userId, stockId);
		}


		public void save(Transaction transaction) {
			// TODO Auto-generated method stub
			transactionRepository.save(transaction);
			
		}


		public void deleteTransaction(Transaction transaction) {
			// TODO Auto-generated method stub
			transactionRepository.delete(transaction);
			
		}


		


	    
	
}
