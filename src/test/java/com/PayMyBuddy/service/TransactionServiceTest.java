package com.PayMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.PayMyBuddy.model.Transaction;

@SpringBootTest
public class TransactionServiceTest {

	@Autowired
	private TransactionService transactionService;
	
	@Test
	public void getTransactionsTest() {
		
		Iterable<Transaction> transactions = transactionService.getTransactions();
		Transaction[] transactionsArray = StreamSupport.stream(transactions.spliterator(), false).toArray(Transaction[]::new);
		assertEquals(6, transactionsArray[0].getSenderAccount());
		assertEquals(20, transactionsArray[0].getAmount());
		
	}
	
	
}
