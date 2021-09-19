package com.exception.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.main.dto.ContactDTO;
import com.exception.main.entity.Contact;
import com.exception.main.exception.EmailExistsException;
import com.exception.main.exception.RecordNotFoundException;
import com.exception.main.service.ContactService;

@RestController
@RequestMapping(path = "api/v1/contact")
public class ContactController {

	@Autowired
	private ContactService service;

	@PostMapping("/add")
	public ResponseEntity<ContactDTO> saveContact(@Valid @RequestBody ContactDTO contactDTO) {
		if (service.isEmailPresent(contactDTO.getEmail())) {
			throw new EmailExistsException("Email already exists : " + contactDTO.getEmail());
		} else {
			ContactDTO dto = service.saveContact(contactDTO);
			return new ResponseEntity<ContactDTO>(dto, HttpStatus.CREATED);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<ContactDTO> updateContact(@Valid @RequestBody Contact contact) {
		if (contact.getId() != null) {
			ContactDTO dto = service.updateContact(contact);
			return new ResponseEntity<ContactDTO>(dto, HttpStatus.ACCEPTED);
		} else throw new RecordNotFoundException("No ID present, can't update : "+ contact);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable("id") Long id) {
		if (service.isIdPresent(id)) {
			service.deleteContact(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else throw new RecordNotFoundException("ID not exists, can't delete : " + id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContactDTO> fetchContactById(@PathVariable("id") Long id) {
		ContactDTO dto = service.getContactById(id);
		return new ResponseEntity<ContactDTO>(dto, HttpStatus.OK);
	}
}
