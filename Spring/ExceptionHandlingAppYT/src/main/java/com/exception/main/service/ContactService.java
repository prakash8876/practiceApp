package com.exception.main.service;

import java.util.Optional;

import javax.validation.Valid;

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
	
	public boolean isEmailPresent(String email) {
		return repo.findByEmail(email).isPresent();
	}

	private ContactDTO from(Contact contact) {
		ContactDTO dto = new ContactDTO();
		dto.setId(contact.getId());
		dto.setFirstName(contact.getFirstName());
		dto.setLastName(contact.getLastName());
		dto.setEmail(contact.getEmail());
		dto.setPhoneNo(contact.getPhoneNo());
		return dto;
	}

	public ContactDTO updateContact(Contact contact) {
		Contact updated = repo.saveAndFlush(contact);
		return from(updated);
	}

	public void deleteContact(Long id) {
		repo.deleteById(id);
	}

	public boolean isIdPresent(Long id) {
		return repo.findById(id).isPresent();
	}
}
