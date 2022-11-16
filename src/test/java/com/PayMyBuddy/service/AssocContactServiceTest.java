package com.PayMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.PayMyBuddy.model.AssocContact;
import com.PayMyBuddy.model.User;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class AssocContactServiceTest {

	@Autowired
	private AssocContactService assocContactService;

	@AfterAll
	public void cleanAfterTests() {
		//assocContactService.deleteContact("ysalim@mail.com", "sshoto@mail.com");
	}
	
	
	@Test
	public void getAllContactsTest() {

		List<AssocContact> assocContact = assocContactService.getAllContacts();
		AssocContact[] contactsArray = assocContact.toArray(new AssocContact[0]);
		assertNotNull(contactsArray);
	}
	
	@Test
	public void getUserContactsTest() {

		List<AssocContact> assocContact = assocContactService.getUserContacts("kdupont@mail.com");
		AssocContact[] contactsArray = assocContact.toArray(new AssocContact[0]);
		assertEquals(3, contactsArray.length);
	}
	
	
	@Test
	public void addContactTest() {

		assocContactService.addContact("ysalim@mail.com", "sshoto@mail.com");
		
		List<AssocContact> assocContact = assocContactService.getUserContacts("ysalim@mail.com");
		AssocContact[] contactsArray = assocContact.toArray(new AssocContact[0]);
		assertEquals(1, contactsArray.length);
		assertEquals("sshoto@mail.com", contactsArray[0].getContactMail());
	}
	
//	@Test
//	public void deleteContactTest() {
//
//		assocContactService.addContact("sshoto@mail.com", "kdupont@mail.com");
//		assocContactService.deleteContact("sshoto@mail.com", "kdupont@mail.com");
//		
//		List<AssocContact> assocContact = assocContactService.getUserContacts("sshoto@mail.com");
//		assertEquals(false, assocContact.iterator().hasNext());
//	}

}

