package com.exception.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.main.dto.ContactDTO;
import com.exception.main.service.ContactService;

@RestController
@RequestMapping(path = "api/v1/contact")
public class ContactController {
	
	@Autowired
	private ContactService service;

	@PostMapping
	public ResponseEntity<ContactDTO> saveContact(@Valid @RequestBody ContactDTO contactDTO) {
		ContactDTO dto = service.saveContact(contactDTO);
		return new ResponseEntity<ContactDTO>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContactDTO> fetchContactById(@PathVariable("id") Long id) {
		ContactDTO dto = service.getContactById(id);
		return new ResponseEntity<ContactDTO>(dto, HttpStatus.OK);
	}
}
