package com.PayMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.PayMyBuddy.constants.DBConstants;
import com.PayMyBuddy.model.Transaction;
import com.PayMyBuddy.model.User;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class TransactionServiceTest {

	@Autowired
	private TransactionService transactionService;
	
	
	@Test
	public void getTransactionsTest() {
		
		List<Transaction> transactions = transactionService.getTransactions();
		Transaction[] transactionsArray = transactions.toArray(new Transaction[0]);
		assertEquals(6, transactionsArray[0].getSenderAccount());
		assertEquals(5, transactionsArray[0].getAmount());	
	}
	
	@Test
	public void getTransactionsBySenderTest() {
		
		List<Transaction> transactions = transactionService.getTransactionsBySender(6);
		Transaction[] transactionsArray = transactions.toArray(new Transaction[0]);
		assertEquals(6, transactionsArray[0].getSenderAccount());
		assertEquals(2, transactionsArray.length);	
	}
	
	@Test
	public void getTransactionsByReceiverTest() {
		
		List<Transaction> transactions = transactionService.getTransactionsByReceiver(5);
		Transaction[] transactionsArray = transactions.toArray(new Transaction[0]);
		assertEquals(6, transactionsArray[0].getSenderAccount());
		assertEquals(2, transactionsArray.length);	
	}
	
	@Test
	public void getTransactionsBySenderAndMinDateTest() throws ParseException {
		
		String dateString = "2022-10-11 12:00:00";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date minDate = df.parse(dateString);
		
		List<Transaction> transactions = transactionService.getTransactionsBySenderAndMinDate(6,minDate);
		Transaction[] transactionsArray = transactions.toArray(new Transaction[0]);
		assertEquals(6, transactionsArray[0].getSenderAccount());
		assertEquals(1, transactionsArray.length);	
	}
	
	@Test
	public void getTransactionsByReceiverAndMinDateTest() throws ParseException {
		
		String dateString = "2022-10-11 12:00:00";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date minDate = df.parse(dateString);
		
		List<Transaction> transactions = transactionService.getTransactionsByReceiverAndMinDate(5,minDate);
		Transaction[] transactionsArray = transactions.toArray(new Transaction[0]);
		assertEquals(9, transactionsArray[0].getSenderAccount());
		assertEquals(1, transactionsArray.length);	
	}

	
	@Test
	public void addTransactionTest() throws ParseException {
		
		String dateString = "2022-10-10 16:00:00";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date newDate = df.parse(dateString);
		
		Transaction newTransaction = new Transaction();
		newTransaction.setId(5);
		newTransaction.setSenderAccount(8);
		newTransaction.setReceiverAccount(4);
		newTransaction.setAmount(10);
		newTransaction.setDate(newDate);
		newTransaction.setDescription("test method addTransaction");
		newTransaction.setCommissionRate(DBConstants.FeesRatePerTransaction);
		
		transactionService.addTransaction(newTransaction);
		
		List<Transaction> transactions = transactionService.getTransactionsBySender(8);
		Transaction[] transactionsArray = transactions.toArray(new Transaction[0]);
		assertEquals(4, transactionsArray[0].getReceiverAccount());
		
		transactionService.deleteTransactionByIdGreaterThan(4);
	}
	
	@Test
	public void newTransactionBetweenUsersTest() throws ParseException {
		
		transactionService.newTransactionBetweenUsers(4, 8, 35, "Test method newTransaction");
		
		List<Transaction> transactions = transactionService.getTransactionsBySender(4);
		Transaction[] transactionsArray = transactions.toArray(new Transaction[0]);
		assertEquals(8, transactionsArray[0].getReceiverAccount());
		
		transactionService.deleteTransactionByIdGreaterThan(4);
	}
	
}
	
