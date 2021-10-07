package com.exception.main.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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

	@Autowired
	private ContactService service;

	@ApiOperation(value = "Add new contact")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Contact created"),
			@ApiResponse(code = 400, message = "Invalid input"),
			@ApiResponse(code = 409, message = "Contact details already exists") })
	@PostMapping(path = "/add", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<ContactDTO> saveContact(
			@ApiParam(value = "Contact Details") @Valid @RequestBody ContactDTO contactDTO) {
		if (StringUtils.isEmpty(contactDTO.getEmail()) || 
				service.isEmailPresent(contactDTO.getEmail())) {
			throw new EmailExistsException("Email already exists or empty.");
		} else {
			ContactDTO dto = service.saveContact(contactDTO);
			return new ResponseEntity<ContactDTO>(dto, HttpStatus.CREATED);
		}
	}

	@ApiOperation(value = "Update existing Contact. NOTE: include ID", notes = "ID should present")
	@PutMapping(path = "/update", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<ContactDTO> updateContact(
			@ApiParam(value = "Enter contact details with ID") @Valid @RequestBody Contact contact) {
		if (contact.getId() != null) {
			ContactDTO dto = service.updateContact(contact);
			return new ResponseEntity<ContactDTO>(dto, HttpStatus.ACCEPTED);
		} else
			throw new RecordNotFoundException("No ID present, can't update : " + contact);
	}

	@ApiOperation(value = "Delete Contact via ID only")
	@DeleteMapping(path = "/{id}", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<Void> deleteContact(
			@ApiParam(name = "numeric id only", required = true) @PathVariable("id") Long id) {
		if (service.isIdPresent(id)) {
			service.deleteContact(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else
			throw new RecordNotFoundException("ID not exists, can't delete : " + id);
	}

	@ApiOperation(value = "Fetch Contact by ID")
	@GetMapping(value = "/{id}", produces = { "application/json" })
	public ResponseEntity<ContactDTO> fetchContactById(
			@ApiParam(value = "Id of the contact to be obtained. Cannot be empty.", required = true) @PathVariable("id") Long id) {
		ContactDTO dto = service.getContactById(id);
		return new ResponseEntity<ContactDTO>(dto, HttpStatus.OK);
	}
}
