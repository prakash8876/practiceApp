package com.exception.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.main.dto.ContactDTO;
import com.exception.main.entity.Contact;
import com.exception.main.exception.RecordNotFoundException;
import com.exception.main.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository repo;
	
	public ContactDTO saveContact(ContactDTO dto) {
		Contact contact = new Contact(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPhoneNo());
		Contact contactDB = repo.save(contact);
		return from(contactDB);
	}
	
	public ContactDTO getContactById(Long id) {
		return repo.findById(id).map(contact -> {
			return from(contact);
		}).orElseThrow(() -> new RecordNotFoundException("No ID found for this one : " + id));
	}

	private ContactDTO from(Contact contactDB) {
		ContactDTO dto = new ContactDTO();
		dto.setId(contactDB.getId());
		dto.setFirstName(contactDB.getFirstName());
		dto.setLastName(contactDB.getLastName());
		dto.setEmail(contactDB.getEmail());
		dto.setPhoneNo(contactDB.getPhoneNo());
		return dto;
	}
}
