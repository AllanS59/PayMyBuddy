package com.PayMyBuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.model.AssocContact;
import com.PayMyBuddy.repository.AssocContactRepository;

@Service
public class AssocContactService {

	@Autowired
	private AssocContactRepository assocContactRepository;
	
	//Get all contacts relations
	public List<AssocContact> getAllContacts() {
		return assocContactRepository.findAll();	
	}
	
	//Get all contacts by User
	public List<AssocContact> getUserContacts(String userMail) {
		return assocContactRepository.findByUserMail(userMail);	
	}
	
	//Add new Contact
		public AssocContact addContact(String userMail, String contactMail) {
			AssocContact relation = new AssocContact();
			relation.setUserMail(userMail);
			relation.setContactMail(contactMail);
			
			return assocContactRepository.save(relation);	
		}
		
	//Delete new Contact
		public void deleteContact(String userMail, String contactMail) {
			assocContactRepository.deleteByUserMailAndContactMail(userMail, contactMail);	
		}
}
