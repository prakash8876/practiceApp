package com.exception.main.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Contact Controller")
@RestController
@RequestMapping(path = "api/v1/contact")
public class ContactController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private ContactService service;

	@ApiOperation(value = "Add new contact")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Contact created"),
			@ApiResponse(code = 400, message = "Invalid input"),
			@ApiResponse(code = 409, message = "Contact details already exists") })
	@PostMapping(path = "/add", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<ContactDTO> saveContact(
			@ApiParam(value = "Contact Details") @Valid @RequestBody ContactDTO contactDTO) {
		try {
			ContactDTO dto = service.saveContact(contactDTO);
			LOGGER.info("Contact {} saved.", contactDTO);
			return new ResponseEntity<ContactDTO>(dto, HttpStatus.CREATED);
		} catch (EmailExistsException ex) {
			LOGGER.error("Error: {}",ex.getMessage());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}

	@ApiOperation(value = "Update existing Contact. NOTE: include ID", notes = "ID should present")
	@PutMapping(path = "/update", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<ContactDTO> updateContact(
			@ApiParam(value = "Enter contact details with ID") @Valid @RequestBody Contact contact) {
		try {
			ContactDTO dto = service.updateContact(contact);
			LOGGER.info("{} updated", contact);
			return new ResponseEntity<ContactDTO>(dto, HttpStatus.ACCEPTED);
		} catch (RecordNotFoundException e) {
			LOGGER.error("Error: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Delete Contact via ID only")
	@DeleteMapping(path = "/{id}", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<Void> deleteContact(
			@ApiParam(name = "numeric id only", required = true) @PathVariable("id") Long id) {
		try {
			service.deleteContact(id);
			LOGGER.info("{} id deleted", id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (RecordNotFoundException e) {
			LOGGER.error("Error: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Fetch Contact by ID")
	@GetMapping(value = "/{id}", produces = { "application/json" })
	public ResponseEntity<ContactDTO> fetchContactById(
			@ApiParam(value = "Id of the contact to be obtained. Cannot be empty.", required = true) @PathVariable("id") Long id) {
		try {
			ContactDTO dto = service.getContactById(id);
			LOGGER.info("{} id contact fetched", id);
			return new ResponseEntity<ContactDTO>(dto, HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			LOGGER.error("Error: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
