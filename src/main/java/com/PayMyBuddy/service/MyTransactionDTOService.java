package com.PayMyBuddy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.DTO.MyTransactionDTO;
import com.PayMyBuddy.model.Transaction;

@Service
public class MyTransactionDTOService {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	
		public List<MyTransactionDTO> getTransactionsDTO(int userAccount) {
			
			
			List<MyTransactionDTO> transactionsDTO = new ArrayList<>();
			
			// Get Transaction where the User is Sender
			List<Transaction> transactions = transactionService.getTransactionsBySender(userAccount);
			for (Transaction t : transactions) {
				
				String connectionMail = accountService.getAccountbyId(t.getReceiverAccount()).get().getUserEmail();
				String connectionName = userService.getUserByEmail(connectionMail).get().getFirstName();
				String transactionAmountString = "-" + t.getAmount() + "€";
				
				MyTransactionDTO transactionDTO = new MyTransactionDTO() ;
				transactionDTO.setId(t.getId());
				transactionDTO.setDate(t.getDate());
				transactionDTO.setConnection(connectionName);
				transactionDTO.setDescription(t.getDescription());
				transactionDTO.setAmount(transactionAmountString);
				
				transactionsDTO.add(transactionDTO);
			}
			
			// Get Transaction where the User is Receiver
						transactions = transactionService.getTransactionsByReceiver(userAccount);
						for (Transaction t : transactions) {
							
							String connectionMail = accountService.getAccountbyId(t.getSenderAccount()).get().getUserEmail();
							String connectionName = userService.getUserByEmail(connectionMail).get().getFirstName();
							String transactionAmountString = "+" + t.getAmount() + "€";
							
							MyTransactionDTO transactionDTO = new MyTransactionDTO() ;
							transactionDTO.setId(t.getId());
							transactionDTO.setDate(t.getDate());
							transactionDTO.setConnection(connectionName);
							transactionDTO.setDescription(t.getDescription());
							transactionDTO.setAmount(transactionAmountString);
							
							transactionsDTO.add(transactionDTO);
						}
			
			return transactionsDTO;
		}
	
	
}
