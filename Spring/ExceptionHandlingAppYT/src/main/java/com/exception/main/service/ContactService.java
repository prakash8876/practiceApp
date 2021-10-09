package com.exception.main.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.main.dto.ContactDTO;
import com.exception.main.entity.Contact;
import com.exception.main.exception.ContactNotValidException;
import com.exception.main.exception.EmailExistsException;
import com.exception.main.exception.PhoneNotValidException;
import com.exception.main.exception.RecordNotFoundException;
import com.exception.main.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository repo;

	public ContactDTO saveContact(ContactDTO dto) {
		try {
			isContactValid(dto);
			Contact contact = convertToContact(dto);
			Contact contactDB = repo.save(contact);
			return convertToDTO(contactDB);
		} catch (EmailExistsException ex) {
			throw new EmailExistsException(ex.getMessage());
		} catch (ContactNotValidException ex) {
			throw new ContactNotValidException(ex.getMessage());
		} catch (PhoneNotValidException ex) {
			throw new PhoneNotValidException(ex.getMessage());
		}
	}

	private Contact convertToContact(ContactDTO dto) {
		return new Contact(dto.getFirstName(), 
							dto.getLastName(), 
							dto.getEmail(), 
							dto.getPhoneNo());
	}

	private boolean isContactValid(ContactDTO dto) {
		if (StringUtils.isEmpty(dto.getEmail()))
			throw new EmailExistsException("Email already exists or empty.");
		else if (dto.getPhoneNo().length() != 10)
			throw new PhoneNotValidException("Phone no is not valid or proper.");
		else
			return true;
	}

	public ContactDTO getContactById(Long id) {
		return repo.findById(id).map(contact -> {
			return convertToDTO(contact);
		}).orElseThrow(() -> new RecordNotFoundException("No ID found for this one : " + id));
	}

	public boolean isEmailPresent(String email) {
		return repo.findByEmail(email).isPresent();
	}

	private ContactDTO convertToDTO(Contact contact) {
		ContactDTO dto = new ContactDTO(contact.getId(), contact.getFirstName(), contact.getLastName(),
				contact.getEmail(), contact.getPhoneNo());
		return dto;
	}

	public ContactDTO updateContact(Contact contact) {
		if (contact.getId() != null) {
			Contact updated = repo.saveAndFlush(contact);
			return convertToDTO(updated);
		} else
			throw new RecordNotFoundException("No ID present, can't update : " + contact);
	}

	public void deleteContact(Long id) {
		if (isIdPresent(id)) {
			repo.deleteById(id);
		} else
			throw new RecordNotFoundException("ID not exists, can't delete : " + id);
	}

	public boolean isIdPresent(Long id) {
		return repo.findById(id).isPresent();
	}

	public List<ContactDTO> getContactByFirstName(String firstName) {
		List<Contact> contacts = repo.findByFirstName(firstName);
		if (contacts.size() > 0) { 
		return contacts.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
		} else throw new RecordNotFoundException("No contacts found for " + firstName);
	}
	
	public Contact getContactByFirstNameAndLastName(String firstName, String lastName) {
		Optional<Contact> contact = repo.findByFirstNameAndLastName(firstName, lastName);
		if (contact.isPresent()) { 
			return contact.get();
		} else throw new RecordNotFoundException("No contacts found for " + firstName);
	}

	public List<ContactDTO> getContactByPhoneNo(String phoneNo) {
		List<Contact> contacts = repo.findByPhoneNo(phoneNo);
		if (!contacts.isEmpty() && phoneNo.length() == 10) {
			return contacts.stream()
						.map(this::convertToDTO)
						.collect(Collectors.toList());
		}else throw new RecordNotFoundException("No contacts found for " + phoneNo);
	}
}
