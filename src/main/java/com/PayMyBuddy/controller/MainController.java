package com.PayMyBuddy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.PayMyBuddy.DTO.ContactDTO;
import com.PayMyBuddy.DTO.MyTransactionDTO;
import com.PayMyBuddy.DTO.NewTransactionDTO;
import com.PayMyBuddy.model.Account;
import com.PayMyBuddy.model.AssocContact;
import com.PayMyBuddy.model.Transaction;
import com.PayMyBuddy.service.AssocContactService;
import com.PayMyBuddy.service.ContactDTOService;
import com.PayMyBuddy.service.MyTransactionDTOService;
import com.PayMyBuddy.service.TransactionService;
import com.PayMyBuddy.service.AccountService;

@Controller
public class MainController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private MyTransactionDTOService transactionDTOService;
	
	@Autowired
	private AssocContactService assocContactService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ContactDTOService contactDTOService;
	
	
	
	@RolesAllowed("USER")
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		
		//Get current user main information
		//----------------------------------
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserMail = authentication.getName();
		
		List<Account> currentUserAccounts = accountService.getAccountbyUser(currentUserMail);
		int currentUserAccountNumber = currentUserAccounts.get(0).getId();
		
		//Create Transaction FOrm 
		NewTransactionDTO newTransactionDTO = new NewTransactionDTO();
		model.addAttribute("newTransactionDTO", newTransactionDTO);
		
	
		//Get the contact list for the connected user
		//----------------------------------
		List<AssocContact> listAssocContact = assocContactService.getUserContacts(currentUserMail);
		List<String> listContactsNames = new ArrayList<>();
		
		for (AssocContact assocContact : listAssocContact) {
			listContactsNames.add(assocContact.getContactMail());
		}
		model.addAttribute("listContactsNames", listContactsNames);
		
		
		//Show the transactions historic for connected User
		//----------------------------------
		List<MyTransactionDTO> transactions = transactionDTOService.getTransactionsDTO(currentUserAccountNumber);
		model.addAttribute("transactions", transactions);
		
		
		return "home";
	}
	
	
	
	@RolesAllowed("USER")
	@RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
	public String contact(Model model) {
		
		//Get current user main information
		//----------------------------------
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserMail = authentication.getName();
		
		
		//Show the transactions historic for connected User
		//----------------------------------
		List<ContactDTO> contacts = contactDTOService.getContactsDTO(currentUserMail);
		ContactDTO contactDTO = new ContactDTO();
		model.addAttribute("contacts", contacts);
		model.addAttribute("contactDTO", contactDTO);
		
		return "contact";
	}
	
	
	
	@RolesAllowed("USER")
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.POST)
	public String newTransaction(Model model, @ModelAttribute("newTransactionDTO") NewTransactionDTO newTransactionDTO) {
		
		//Get current user main information
		//----------------------------------
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserMail = authentication.getName();
				
		List<Account> currentUserAccounts = accountService.getAccountbyUser(currentUserMail);
		int currentUserAccountNumber = currentUserAccounts.get(0).getId();

		//Get information from Form
		String contactMail = newTransactionDTO.getConnection();
		float amount = newTransactionDTO.getAmount();
		String description = newTransactionDTO.getDescription();

		List<Account> contactAccounts = accountService.getAccountbyUser(contactMail);
		int contactAccountNumber = contactAccounts.get(0).getId();
		
		// Create the new transaction
		if (contactMail != null && contactMail.length() > 0 && amount>0)  {
			transactionService.newTransactionBetweenUsers(currentUserAccountNumber, contactAccountNumber, amount, description);
			
			return "redirect:/home";
		}
		return "home";
	}
	
	
	
	
	
	@RolesAllowed("USER")
	@RequestMapping(value = {"/contact" }, method = RequestMethod.POST)
	public String newContact(Model model, @ModelAttribute("contactDTO") ContactDTO contactDTO) {
		
		//Get current user main information
		//----------------------------------
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserMail = authentication.getName();

		String newContactMail = contactDTO.getContactMail();

		if (newContactMail != null && newContactMail.length() > 0) {
			assocContactService.addContact(currentUserMail, newContactMail);
			
			return "redirect:/contact";
		}
		return "contact";
	}

	   
	
}
