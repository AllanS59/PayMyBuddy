//package com.PayMyBuddy;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class PayMyBuddyApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(PayMyBuddyApplication.class, args);
//	}
//
//}

package com.PayMyBuddy;

import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.PayMyBuddy.model.Account;
import com.PayMyBuddy.model.AssocContact;
import com.PayMyBuddy.model.User;
import com.PayMyBuddy.service.AccountService;
import com.PayMyBuddy.service.AssocContactService;
import com.PayMyBuddy.service.UserService;

@SpringBootApplication
public class PayMyBuddyApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private AssocContactService assocContactService;

	public static void main(String[] args) {
		SpringApplication.run(PayMyBuddyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
		
		System.out.println("Appli lancée");
		System.out.println("TOUS LES UTILISATEURS:");	
		Iterable<User> users = userService.getUsers();
		users.forEach(user -> System.out.println(user.getFirstName() + " " + user.getLastName()));
		
		System.out.println("TOUS LES COMPTES:");
		Iterable<Account> accounts = accountService.getAccounts();
		//accounts.forEach(account -> System.out.println(account.getUserEmail()));
		Account[] accountsArray = StreamSupport.stream(accounts.spliterator(), false).toArray(Account[]::new);
		for( Account a : accountsArray) {
			System.out.println(a.getUserEmail());
		}
	
		System.out.println("UN UTILISATEUR PAR MAIL:");	
		Iterable<User> usersMail = userService.getUserByEmail("kdupont@mail.com");
		usersMail.forEach(user -> System.out.println(user.getFirstName() + " " + user.getLastName()));
	
		System.out.println("CONTACTS DE KEVIN:");		
		Iterable<AssocContact> allContacts = assocContactService.getUserContacts("kdupont@mail.com");
		allContacts.forEach(relation -> System.out.println(relation.getUserMail() + " -- " + relation.getContactMail()));
	
		
		System.out.println("MISE A JOUR DU COMPTE DE KEVIN");
		System.out.println("état avant Maj: argent = " + accountService.getAccountbyId(6).get().getBalanceCheckpoint() + "   Date = " + accountService.getAccountbyId(6).get().getDateCheckpoint());	
		accountService.updateAccountCheckpoint(6);
		System.out.println("état avant Maj: argent = " + accountService.getAccountbyId(6).get().getBalanceCheckpoint() + "   Date = " + accountService.getAccountbyId(6).get().getDateCheckpoint());	
		
	}
}
