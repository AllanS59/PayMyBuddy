package com.PayMyBuddy.service;



import java.util.Date;

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
	public Iterable<Transaction> getTransactions() {
		return transactionRepository.findAll();	
	}
	
	// Get all transaction for one Sender
	public Iterable<Transaction> getTransactionsBySender(int senderAccount){
		return transactionRepository.findBySenderAccount(senderAccount);
	}
	
	// Get all transaction for one Receiver
	public Iterable<Transaction> getTransactionsByReceiver(int receiverAccount){
		return transactionRepository.findByReceiverAccount(receiverAccount);
	}
	
	// Get all transaction for one Sender
		public Iterable<Transaction> getTransactionsBySenderAndMinDate(int senderAccount, Date minDate){
			return transactionRepository.findBySenderAccountAndDateAfter(senderAccount, minDate);
		}
		
		// Get all transaction for one Receiver
		public Iterable<Transaction> getTransactionsByReceiverAndMinDate(int receiverAccount, Date minDate){
			return transactionRepository.findByReceiverAccountAndDateAfter(receiverAccount, minDate);
		}
	
	
	
	// Add a new transaction to database
	public Transaction addTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);	
	}
	
	
	// Send money to a contact
	public Transaction newTransactionBetweenUsers (int senderAccount, int receiverAccount, float amount, String description) {
			Transaction transaction = new Transaction();
			
			transaction.setSenderAccount(senderAccount);
			transaction.setReceiverAccount(receiverAccount);
			transaction.setAmount(amount);
			transaction.setDate(new Date(0));
			transaction.setDescription(description);
			transaction.setCommissionRate(DBConstants.FeesRatePerTransaction);
				
			transaction = addTransaction(transaction);
		
		return null;
		
	}
}
