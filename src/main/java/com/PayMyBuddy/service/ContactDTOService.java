package com.PayMyBuddy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.DTO.ContactDTO;
import com.PayMyBuddy.DTO.MyTransactionDTO;
import com.PayMyBuddy.model.AssocContact;

@Service
public class ContactDTOService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AssocContactService assocContactService;
	
	
	public List<ContactDTO> getContactsDTO (String userMail){
		
		List<ContactDTO> contactsDTO = new ArrayList<>();
		
		List<AssocContact> assocContacts = assocContactService.getUserContacts(userMail);
		for (AssocContact as : assocContacts) {
			
			ContactDTO contactDTO = new ContactDTO() ;
	
			String contactMail = as.getContactMail();
			String contactFirstName= userService.getUserByEmail(contactMail).get().getFirstName();
			String contactLastName= userService.getUserByEmail(contactMail).get().getLastName();
			
			contactDTO.setContactMail(contactMail);
			contactDTO.setContactFirstName(contactFirstName);
			contactDTO.setContactLastName(contactLastName);
			
			contactsDTO.add(contactDTO);
			
		}
		
		
		return contactsDTO;
		
	}
}
