package com.PayMyBuddy.service;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.constants.DBConstants;
import com.PayMyBuddy.model.Transaction;
import com.PayMyBuddy.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	// Get all transactions
	public List<Transaction> getTransactions() {
		return transactionRepository.findAll();	
	}
	
	// Get all transaction for one Sender
	public List<Transaction> getTransactionsBySender(int senderAccount){
		return transactionRepository.findBySenderAccount(senderAccount);
	}
	
	// Get all transaction for one Receiver
	public List<Transaction> getTransactionsByReceiver(int receiverAccount){
		return transactionRepository.findByReceiverAccount(receiverAccount);
	}
	
	// Get all transaction for one Sender
		public List<Transaction> getTransactionsBySenderAndMinDate(int senderAccount, Date minDate){
			return transactionRepository.findBySenderAccountAndDateAfter(senderAccount, minDate);
		}
		
		// Get all transaction for one Receiver
		public List<Transaction> getTransactionsByReceiverAndMinDate(int receiverAccount, Date minDate){
			return transactionRepository.findByReceiverAccountAndDateAfter(receiverAccount, minDate);
		}
		
		
	
	
	
	// Add a new transaction to database
	public Transaction addTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);	
	}
	
	// Delete a transaction 
	public void deleteTransactionById(int id){
		transactionRepository.deleteById(id);
	}
	
	
	// Send money to a contact
	public Transaction newTransactionBetweenUsers (int senderAccount, int receiverAccount, float amount, String description) {
			Transaction transaction = new Transaction();
			
			transaction.setSenderAccount(senderAccount);
			transaction.setReceiverAccount(receiverAccount);
			transaction.setAmount(amount);
			transaction.setDate(new Date());
			transaction.setDescription(description);
			transaction.setCommissionRate(DBConstants.FeesRatePerTransaction);
				
			transaction = addTransaction(transaction);
		
		return null;
		
	}
}
