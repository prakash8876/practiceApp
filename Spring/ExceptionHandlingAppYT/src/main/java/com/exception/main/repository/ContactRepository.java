package com.exception.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exception.main.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

	Optional<Contact> findByEmail(String email);

	List<Contact> findByFirstName(String firstName);

	List<Contact> findByPhoneNo(String phoneNo);

	Optional<Contact> findByFirstNameAndLastName(String firstName, String lastName);

}
