package com.PayMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.PayMyBuddy.model.AssocContact;

@SpringBootTest
public class AssocContactServiceTest {

	@Autowired
	private AssocContactService assocContactService;

	@Test
	public void getAllContactsTest() {

		Iterable<AssocContact> assocContact = assocContactService.getAllContacts();
		AssocContact[] contactsArray = StreamSupport.stream(assocContact.spliterator(), false).toArray(AssocContact[]::new);
		assertEquals(6, contactsArray.length);
	}
	
	@Test
	public void getUserContactsTest() {

		Iterable<AssocContact> assocContact = assocContactService.getUserContacts("kdupont@mail.com");
		AssocContact[] contactsArray = StreamSupport.stream(assocContact.spliterator(), false).toArray(AssocContact[]::new);
		assertEquals(3, contactsArray.length);
	}

}

//	//Add new Contact
//		public AssocContact addContact(String userMail, String contactMail)
