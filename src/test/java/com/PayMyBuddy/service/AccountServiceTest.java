package com.PayMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.PayMyBuddy.model.Account;


@SpringBootTest
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;
	

	@Test
	public void getAccountsTest() {
		
		Iterable<Account> accounts = accountService.getAccounts();
		Account[] accountsArray = StreamSupport.stream(accounts.spliterator(), false).toArray(Account[]::new);
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
		
		Iterable<Account> accounts = accountService.getAccountbyUser("admin@mail.com");
		Account[] accountsArray = StreamSupport.stream(accounts.spliterator(), false).toArray(Account[]::new);
	
		assertEquals("admin@mail.com", accountsArray[1].getUserEmail());
		assertEquals(3, accountsArray.length);
		
	}
	
}



//	//Add/Update a new account
//	public Account addAccount(Account account)
//	
//	//Update amountCheckpoint and DateCheckpoint
//	public Optional<Account> updateAccountCheckpoint (int accountId )
//	
//	//Add Money to user account (from system appro account)
//	public void addMoneyToUserAccount (int receiverAccount, float amount)
//		
//	//Extract Money from user account (to system appro account)
//	public void extractMoneyFromUserAccount (int senderAccount, float amount)

//	//Send Money from a User to the other
//	public void sentMoneyFromUserToUser (int senderAccount, int receiverAccount, float amount, String description)