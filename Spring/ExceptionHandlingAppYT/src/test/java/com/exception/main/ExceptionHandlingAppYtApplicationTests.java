package com.exception.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exception.main.dto.ContactDTO;
import com.exception.main.entity.Contact;
import com.exception.main.service.ContactService;

@SpringBootTest
class ExceptionHandlingAppYtApplicationTests {

	@Autowired
	private ContactService service;
	
	
	@Test
	void testCreateContact() {
		ContactDTO original = new ContactDTO();
		original.setFirstName("first");
		original.setLastName("last");
		original.setEmail("email@gmail.com");
		original.setPhoneNo("9901236598");
		
		ContactDTO saved = service.saveContact(original);
		
		assertEquals(original.getFirstName(), saved.getFirstName());
		assertEquals(original.getLastName(), saved.getLastName());
		assertEquals(original.getEmail(), saved.getEmail());
		assertEquals(original.getPhoneNo(), saved.getPhoneNo());
		
	}
	
	@Test
	void testGetContactByName() {
		String firstName = "text";
		List<ContactDTO> contacts = service.getContactByFirstName(firstName);
		
		assertEquals(firstName, contacts.get(0).getFirstName());
	}
	
	@Test
	void testGetContactByPhoneNo() {
		String phoneNo = "900000000";
		List<ContactDTO> contacts = service.getContactByPhoneNo(phoneNo);
		
		assertEquals(phoneNo, contacts.get(0).getPhoneNo());
	}
	
	@Test
	void testUpdateContact() {
		String firstName = "firstName";
		String lastName = "lastName";
		String email = "updatemail@gmail.com";
		Contact contact = service.getContactByFirstNameAndLastName(firstName, lastName);
		contact.setEmail(email);
		ContactDTO dto = service.updateContact(contact);
		
		assertEquals(email, dto.getEmail());
	}
}
