package com.PayMyBuddy.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.PayMyBuddy.model.Transaction;
import com.PayMyBuddy.service.TransactionService;

@RestController
public class MainController {

	@Autowired
	private TransactionService transactionService;
	
	@RolesAllowed("USER")
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home(Model model) {

		List<Transaction> transactions = transactionService.getTransactionsBySender(6);
		model.addAttribute("transactions", transactions);
		
		return "home";
	}
	
//	   @RolesAllowed("USER")
//	   @RequestMapping("/*")
//	   public String getUser()
//	   {
//	      return "Welcome User";
//	   }
	   
	
}
