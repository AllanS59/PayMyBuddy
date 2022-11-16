package com.PayMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.PayMyBuddy.constants.AccountType;
import com.PayMyBuddy.model.Account;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transactionService;

	// PROBLEME : ERREUR QUAND ON ESSAYE DE SUPPRIMER LES COMPTES ET TRANSACTIONS DE
	// TESTS
	@AfterAll
	public void cleanAfterTests() throws ParseException {

		String dateString = "2022-10-10 10:00:00"; // INITIAL DATE FOR ACCOUNTS IN DATABASE
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = df.parse(dateString);

		Account newAccount = new Account(); // REINITIATIZATION OF ACCOUNTS AS BEFORE TESTS
		newAccount.setId(10);
		newAccount.setUserEmail("sshoto@mail.com");
		newAccount.setAccountType(AccountType.USER);
		newAccount.setBalanceCheckpoint(70);
		newAccount.setDateCheckpoint(date);
		accountService.addAccount(newAccount);

		newAccount.setId(4);
		newAccount.setUserEmail("jdupont@mail.com");
		newAccount.setAccountType(AccountType.USER);
		newAccount.setBalanceCheckpoint(500);
		newAccount.setDateCheckpoint(date);
		accountService.addAccount(newAccount);

		newAccount.setId(8);
		newAccount.setUserEmail("mmartin@mail.com");
		newAccount.setAccountType(AccountType.USER);
		newAccount.setBalanceCheckpoint(200);
		newAccount.setDateCheckpoint(date);
		accountService.addAccount(newAccount);

		transactionService.deleteTransactionById(7);
		transactionService.deleteTransactionById(6);
		transactionService.deleteTransactionById(5);

		accountService.deleteAccount(11);
		accountService.deleteAccount(12);
		accountService.deleteAccount(13);

	}

	@Test
	public void getAccountsTest() {

		List<Account> accounts = accountService.getAccounts();
		Account[] accountsArray = accounts.toArray(new Account[0]);
		assertEquals("admin@mail.com", accountsArray[0].getUserEmail());
		assertEquals(500, accountsArray[3].getBalanceCheckpoint());
	}

	@Test
	public void getAccountbyIdTest() {

		Optional<Account> account = accountService.getAccountbyId(7);
		assertEquals("bmartin@mail.com", account.get().getUserEmail());
		assertEquals(200, account.get().getBalanceCheckpoint());
	}

	@Test
	public void getAccountbyUserTest() {

		List<Account> accounts = accountService.getAccountbyUser("admin@mail.com");
		Account[] accountsArray = accounts.toArray(new Account[0]);

		assertEquals("admin@mail.com", accountsArray[1].getUserEmail());
		assertEquals(3, accountsArray.length);
	}

	@Test
	public void addAccountTest() throws ParseException {

		String dateString = "2022-10-11 12:00:00";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = df.parse(dateString);

		Account newAccount = new Account();
		newAccount.setId(11);
		newAccount.setUserEmail("jdupont@mail.com");
		newAccount.setAccountType(AccountType.USER);
		newAccount.setBalanceCheckpoint(75);
		newAccount.setDateCheckpoint(date);

		accountService.addAccount(newAccount);

		List<Account> accounts = accountService.getAccountbyUser("jdupont@mail.com");
		Account[] accountsArray = accounts.toArray(new Account[0]);

		assertEquals("jdupont@mail.com", accountsArray[1].getUserEmail());
		assertEquals(2, accountsArray.length);
	}

	@Test
	public void updateAccountCheckpointTest() throws ParseException {

		String dateString = "2022-10-10 10:00:00";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = df.parse(dateString);

		Account newAccount = new Account();
		newAccount.setId(10);
		newAccount.setUserEmail("sshoto@mail.com");
		newAccount.setAccountType(AccountType.USER);
		newAccount.setBalanceCheckpoint(70);
		newAccount.setDateCheckpoint(date);

		accountService.updateAccountCheckpoint(10);

		List<Account> accounts = accountService.getAccountbyUser("sshoto@mail.com");
		Account[] accountsArray = accounts.toArray(new Account[0]);

		assertEquals("sshoto@mail.com", accountsArray[0].getUserEmail());
		assertEquals(110, accountsArray[0].getBalanceCheckpoint());
		assertNotEquals(date, accountsArray[0].getDateCheckpoint());
	}

	@Test
	public void addMoneyToUserAccountTest() throws ParseException {

		accountService.addMoneyToUserAccount(4, 100);

		List<Account> accounts = accountService.getAccountbyUser("jdupont@mail.com");
		Account[] accountsArray = accounts.toArray(new Account[0]);

		assertEquals("jdupont@mail.com", accountsArray[0].getUserEmail());
		assertEquals(600, accountsArray[0].getBalanceCheckpoint());
	}

	@Test
	public void extractMoneyFromUserAccountTest() throws ParseException {

		accountService.extractMoneyFromUserAccount(8, 100);

		List<Account> accounts = accountService.getAccountbyUser("mmartin@mail.com");
		Account[] accountsArray = accounts.toArray(new Account[0]);

		assertEquals("mmartin@mail.com", accountsArray[0].getUserEmail());
		assertEquals(100, accountsArray[0].getBalanceCheckpoint());
	}

	@Test
	public void sentMoneyFromUserToUserTest() throws ParseException {

		String dateString = "2022-10-10 10:00:00";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = df.parse(dateString);

		Account newAccount1 = new Account();
		newAccount1.setId(12);
		newAccount1.setUserEmail("sshoto@mail.com");
		newAccount1.setAccountType(AccountType.USER);
		newAccount1.setBalanceCheckpoint(100);
		newAccount1.setDateCheckpoint(date);
		accountService.addAccount(newAccount1);

		Account newAccount2 = new Account();
		newAccount2.setId(13);
		newAccount2.setUserEmail("ysalim@mail.com");
		newAccount2.setAccountType(AccountType.USER);
		newAccount2.setBalanceCheckpoint(100);
		newAccount2.setDateCheckpoint(date);
		accountService.addAccount(newAccount2);

		accountService.sentMoneyFromUserToUser(12, 13, 50, "Transaction test entre users");

		Optional<Account> account1 = accountService.getAccountbyId(11);
		Optional<Account> account2 = accountService.getAccountbyId(12);

		assertEquals(50, account1.get().getBalanceCheckpoint());
		assertEquals(150, account2.get().getBalanceCheckpoint());
	}
}