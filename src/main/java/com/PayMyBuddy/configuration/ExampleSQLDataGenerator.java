package com.PayMyBuddy.configuration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.constants.AccountType;
import com.PayMyBuddy.model.Account;
import com.PayMyBuddy.model.AssocContact;
import com.PayMyBuddy.model.Transaction;
import com.PayMyBuddy.model.User;
import com.PayMyBuddy.repository.AccountRepository;
import com.PayMyBuddy.repository.AssocContactRepository;
import com.PayMyBuddy.repository.TransactionRepository;
import com.PayMyBuddy.repository.UserRepository;

@Service
public class ExampleSQLDataGenerator {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AssocContactRepository assocContactRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	public void createExampleSQLData() throws ParseException {
		
		//Define a 'Start' Date
		String dateString = "2022-10-10 10:00:00";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = df.parse(dateString);
		
		//Creation Admin account
		User newUser = new User();	newUser.setEmail("admin@mail.com");	newUser.setFirstName("Allan");	newUser.setLastName("Admin");	newUser.setAddress("1 rue Admin 75000 Paris");	newUser.setPhone("01-01-01-01-01");	newUser.setPassword(bCryptPasswordEncoder.encode("passadmin"));	userRepository.save(newUser);
		Account newAccount = new Account();	newAccount.setUserEmail("admin@mail.com");	newAccount.setBalanceCheckpoint(1000000);	newAccount.setDateCheckpoint(date);	newAccount.setAccountType(AccountType.SYSTEM);	accountRepository.save(newAccount);	
		newAccount = new Account();			newAccount.setUserEmail("admin@mail.com");	newAccount.setBalanceCheckpoint(10000000);	newAccount.setDateCheckpoint(date);	newAccount.setAccountType(AccountType.SYSTEM);	accountRepository.save(newAccount);	
		newAccount = new Account();			newAccount.setUserEmail("admin@mail.com");	newAccount.setBalanceCheckpoint(0);			newAccount.setDateCheckpoint(date);	newAccount.setAccountType(AccountType.SYSTEM);	accountRepository.save(newAccount);	
		
		//Creation some users accounts
		newUser = new User();	newUser.setEmail("jdupont@mail.com");	newUser.setFirstName("Jean");	newUser.setLastName("Dupont");	newUser.setAddress("4 rue LaRue 50000 Ville");			newUser.setPhone("06-07-08-09-10");	newUser.setPassword(bCryptPasswordEncoder.encode("pass1234"));		userRepository.save(newUser);
		newUser = new User();	newUser.setEmail("mdupont@mail.com");	newUser.setFirstName("Marie");	newUser.setLastName("Dupont");	newUser.setAddress("4 rue LaRue 50000 Ville");			newUser.setPhone("06-05-04-03-02");	newUser.setPassword(bCryptPasswordEncoder.encode("pass4567"));		userRepository.save(newUser);
		newUser = new User();	newUser.setEmail("kdupont@mail.com");	newUser.setFirstName("Kevin");	newUser.setLastName("Dupont");	newUser.setAddress("4 rue LaRue 50000 Ville");			newUser.setPhone("06-15-14-13-12");	newUser.setPassword(bCryptPasswordEncoder.encode("pass1357"));		userRepository.save(newUser);
		newUser = new User();	newUser.setEmail("bmartin@mail.com");	newUser.setFirstName("Bernard");newUser.setLastName("Martin");	newUser.setAddress("12 rue AutreRue 75000 City");		newUser.setPhone("06-99-98-17-72");	newUser.setPassword(bCryptPasswordEncoder.encode("password1"));		userRepository.save(newUser);
		newUser = new User();	newUser.setEmail("mmartin@mail.com");	newUser.setFirstName("Maeva");	newUser.setLastName("Martin");	newUser.setAddress("12 rue AutreRue 75000 City");		newUser.setPhone("06-00-78-87-74");	newUser.setPassword(bCryptPasswordEncoder.encode("password2"));		userRepository.save(newUser);
		newUser = new User();	newUser.setEmail("ysalim@mail.com");	newUser.setFirstName("Yacine");	newUser.setLastName("Salim");	newUser.setAddress("5 avenue Lavenue 55400 Village");	newUser.setPhone("04-57-68-02-99");	newUser.setPassword(bCryptPasswordEncoder.encode("pass7234"));		userRepository.save(newUser);
		newUser = new User();	newUser.setEmail("sshoto@mail.com");	newUser.setFirstName("Sakura");	newUser.setLastName("Shoto");	newUser.setAddress("14 avenue Lavenue 55400 Village");	newUser.setPhone("07-56-88-12-99");	newUser.setPassword(bCryptPasswordEncoder.encode("password34"));	userRepository.save(newUser);
		
		newAccount = new Account();	newAccount.setUserEmail("jdupont@mail.com");	newAccount.setBalanceCheckpoint(500);	newAccount.setDateCheckpoint(date);	newAccount.setAccountType(AccountType.USER);	accountRepository.save(newAccount);	
		newAccount = new Account();	newAccount.setUserEmail("mdupont@mail.com");	newAccount.setBalanceCheckpoint(500);	newAccount.setDateCheckpoint(date);	newAccount.setAccountType(AccountType.USER);	accountRepository.save(newAccount);	
		newAccount = new Account();	newAccount.setUserEmail("kdupont@mail.com");	newAccount.setBalanceCheckpoint(40);	newAccount.setDateCheckpoint(date);	newAccount.setAccountType(AccountType.USER);	accountRepository.save(newAccount);	
		newAccount = new Account();	newAccount.setUserEmail("bmartin@mail.com");	newAccount.setBalanceCheckpoint(200);	newAccount.setDateCheckpoint(date);	newAccount.setAccountType(AccountType.USER);	accountRepository.save(newAccount);	
		newAccount = new Account();	newAccount.setUserEmail("mmartin@mail.com");	newAccount.setBalanceCheckpoint(200);	newAccount.setDateCheckpoint(date);	newAccount.setAccountType(AccountType.USER);	accountRepository.save(newAccount);	
		newAccount = new Account();	newAccount.setUserEmail("ysalim@mail.com");		newAccount.setBalanceCheckpoint(70);	newAccount.setDateCheckpoint(date);	newAccount.setAccountType(AccountType.USER);	accountRepository.save(newAccount);	
		newAccount = new Account();	newAccount.setUserEmail("sshoto@mail.com");		newAccount.setBalanceCheckpoint(70);	newAccount.setDateCheckpoint(date);	newAccount.setAccountType(AccountType.USER);	accountRepository.save(newAccount);	

		//Creation some contact connections
		AssocContact newContact = new AssocContact();	newContact.setUserMail("jdupont@mail.com");	newContact.setContactMail("mdupont@mail.com");	assocContactRepository.save(newContact);
		newContact = new AssocContact();				newContact.setUserMail("jdupont@mail.com");	newContact.setContactMail("kdupont@mail.com");	assocContactRepository.save(newContact);
		newContact = new AssocContact();				newContact.setUserMail("jdupont@mail.com");	newContact.setContactMail("bmartin@mail.com");	assocContactRepository.save(newContact);
		newContact = new AssocContact();				newContact.setUserMail("kdupont@mail.com");	newContact.setContactMail("jdupont@mail.com");	assocContactRepository.save(newContact);
		newContact = new AssocContact();				newContact.setUserMail("kdupont@mail.com");	newContact.setContactMail("ysalim@mail.com");	assocContactRepository.save(newContact);
		newContact = new AssocContact();				newContact.setUserMail("kdupont@mail.com");	newContact.setContactMail("sshoto@mail.com");	assocContactRepository.save(newContact);
		
		
		//Creation of some transactions
		dateString = "2022-10-10 22:00:00";		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	date = df.parse(dateString);
		Transaction newTransaction = new Transaction();	newTransaction.setSenderAccount(6);	newTransaction.setReceiverAccount(5);	newTransaction.setAmount(5);	newTransaction.setDate(date);	newTransaction.setDescription("virement 5e");	newTransaction.setCommissionRate(0.5);	transactionRepository.save(newTransaction);
	
		dateString = "2022-10-11 14:00:00";		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	date = df.parse(dateString);
		newTransaction = new Transaction();				newTransaction.setSenderAccount(6);	newTransaction.setReceiverAccount(9);	newTransaction.setAmount(20);	newTransaction.setDate(date);	newTransaction.setDescription("virement 20e");	newTransaction.setCommissionRate(0.5);	transactionRepository.save(newTransaction);
		
		dateString = "2022-10-11 14:00:00";		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	date = df.parse(dateString);
		newTransaction = new Transaction();				newTransaction.setSenderAccount(7);	newTransaction.setReceiverAccount(10);	newTransaction.setAmount(40);	newTransaction.setDate(date);	newTransaction.setDescription("virement 40e");	newTransaction.setCommissionRate(0.5);	transactionRepository.save(newTransaction);
		
		dateString = "2022-10-11 14:00:00";		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	date = df.parse(dateString);
		newTransaction = new Transaction();				newTransaction.setSenderAccount(9);	newTransaction.setReceiverAccount(5);	newTransaction.setAmount(5);	newTransaction.setDate(date);	newTransaction.setDescription("virement 5e");	newTransaction.setCommissionRate(0.5);	transactionRepository.save(newTransaction);
	
	}
	
}
