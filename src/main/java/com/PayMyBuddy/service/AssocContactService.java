package com.PayMyBuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.model.AssocContact;
import com.PayMyBuddy.repository.AssocContactRepository;

@Service
public class AssocContactService {

	@Autowired
	private AssocContactRepository assocContactRepository;
	
	//Get all contacts relations
	public Iterable<AssocContact> getAllContacts() {
		return assocContactRepository.findAll();	
	}
	
	//Get all contacts by User
	public Iterable<AssocContact> getUserContacts(String userMail) {
		return assocContactRepository.findByuserMail(userMail);	
	}
	
	//Add new Contact
		public AssocContact addContact(String userMail, String contactMail) {
			AssocContact relation = new AssocContact();
			relation.setUserMail(userMail);
			relation.setContactMail(contactMail);
			
			return assocContactRepository.save(relation);	
		}
}
