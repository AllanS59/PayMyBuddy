package com.PayMyBuddy.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PayMyBuddy.constants.DBConstants;
import com.PayMyBuddy.model.Account;
import com.PayMyBuddy.model.Transaction;
import com.PayMyBuddy.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionService transactionService;
	
	
	//Get all accounts
	public List<Account> getAccounts() {
		return accountRepository.findAll();	
	}
	
	//Get account for one user
		public Optional<Account> getAccountbyId( int accountId) {
			return accountRepository.findById(accountId);
		}
		
	//Get account for one user
	public List<Account> getAccountbyUser(String userMail) {
		return accountRepository.findByUserEmail(userMail);
	}
	
	//Add/Update a new account
	public Account addAccount(Account account) {
		return accountRepository.save(account);	
	}
	

	//Update amountCheckpoint and DateCheckpoint
	@Transactional
	public Optional<Account> updateAccountCheckpoint (int accountId ) {
		
		//Get current values of BalanceCheckpoint and DateCheckpoint
		Optional<Account> account = getAccountbyId(accountId);
		
		float amountCheckPoint = account.get().getBalanceCheckpoint();
		Date dateCheckPoint = account.get().getDateCheckpoint();
		
		// Get all transaction for concerned UserAccount, with date more recent than last checkpoint
		List<Transaction> transactionsAsSender = transactionService.getTransactionsBySenderAndMinDate(accountId, dateCheckPoint);
		for(Transaction t : transactionsAsSender){
			 amountCheckPoint=  amountCheckPoint - t.getAmount();
		    }
		
		List<Transaction> transactionsAsReceiver = transactionService.getTransactionsByReceiverAndMinDate(accountId, dateCheckPoint);
		for(Transaction t : transactionsAsReceiver){
			 amountCheckPoint=  amountCheckPoint + t.getAmount();
		    }
		
		// update Account information with new calculated amount and todays date
		account.get().setBalanceCheckpoint(amountCheckPoint);
		
        Date todaysDate = new Date();
		account.get().setDateCheckpoint(todaysDate);
		
		return account;
		
	}
	
	//Delete account by Id
	@Transactional
	public void  deleteAccount(int id) {
		accountRepository.deleteById(id);	
	}
	
	
	//Add Money to user account (from system appro account)
	@Transactional
		public void addMoneyToUserAccount (int receiverAccount, float amount) {
			
			//Update Balance of the System Appro Account
			updateAccountCheckpoint (DBConstants.ApproSystemAccountId);
			
			//Check if enough money in APpro account to execute the transfer of Money
			float availableBalance = getAccountbyId(DBConstants.ApproSystemAccountId).get().getBalanceCheckpoint();
			
			// Create a transaction (if enough money)
			if (availableBalance >= amount) {		
				transactionService.newTransactionBetweenUsers (DBConstants.ApproSystemAccountId, receiverAccount, amount, "Ajout d'argent sur le compte utilisateur");
			}
			
			//Update Balances of User and System Account
			updateAccountCheckpoint (DBConstants.ApproSystemAccountId);
			updateAccountCheckpoint (receiverAccount);
			
		}
		
		
		//Extract Money from user account (to system appro account)
	@Transactional
		public void extractMoneyFromUserAccount (int senderAccount, float amount) {
			
			//Update Balance of the Sender Account
			updateAccountCheckpoint (senderAccount);
			
			//Check if Sender has enough money to execute the transfer of Money
			float availableBalance = getAccountbyId(senderAccount).get().getBalanceCheckpoint();
			
			// Create a transaction (if enough money)
			if (availableBalance >= amount) {
				transactionService.newTransactionBetweenUsers (senderAccount, DBConstants.ApproSystemAccountId, amount, "Retrait d'argent depuis compte utilisateur");	
			}
			
			//Update Balances of User and System Account
			updateAccountCheckpoint (DBConstants.ApproSystemAccountId);
			updateAccountCheckpoint (senderAccount);
		}
		
		
		
	//Send Money from a User to the other
	@Transactional
	public void sentMoneyFromUserToUser (int senderAccount, int receiverAccount, float amount, String description) {
		
		//Update Balance of the Sender Account
		updateAccountCheckpoint (senderAccount);
		
		//Check if Sender has enough money to execute the transfer of Money
		float availableBalance = getAccountbyId(senderAccount).get().getBalanceCheckpoint();
		
		// Create a transaction (if enough money)
		if (availableBalance >= amount) {
			transactionService.newTransactionBetweenUsers (senderAccount, receiverAccount, amount, description);
		}
		
		//Update Balances of Sender and Receiver accounts
				updateAccountCheckpoint (senderAccount);
				updateAccountCheckpoint (receiverAccount);
	}
	
	
}
