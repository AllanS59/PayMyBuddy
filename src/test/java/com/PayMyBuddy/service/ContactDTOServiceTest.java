package com.PayMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.PayMyBuddy.DTO.ContactDTO;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class ContactDTOServiceTest {

	@Autowired
	private ContactDTOService contactDTOService;
	

	@Test
	public void getContactDTOTest() {
		List<ContactDTO> contactDTO = contactDTOService.getContactsDTO("kdupont@mail.com");
		ContactDTO[] contactsDTOArray = contactDTO.toArray(new ContactDTO[0]);
		assertEquals("jdupont@mail.com", contactsDTOArray[0].getContactMail());
		assertEquals("Jean", contactsDTOArray[0].getContactFirstName());
		assertEquals("Dupont", contactsDTOArray[0].getContactLastName());
		
	}

	
}
