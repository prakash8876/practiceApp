package com.exception.main.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ContactDTO {

	private Long id;
	private String firstName;
	private String lastName;

	@Email
	@NotEmpty(message = "email id is required")
	private String email;

	@Size(min = 10, max = 10, message = "phone no must be 10 characters")
	@Pattern(regexp = "[0-9]+", message = "phone no must be numeric")
	private String phoneNo;

	public ContactDTO() {}

	public ContactDTO(Long id, String firstName, String lastName,
			@Email @NotEmpty(message = "email id is required") String email,
			@Size(min = 10, max = 10, message = "phone no must be 10 characters") @Pattern(regexp = "[0-9]+", message = "phone no must be numeric") String phoneNo) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("ContactDTO [firstName=" + firstName);
		str.append(", lastName=" + lastName);
		str.append(", email=" + email);
		str.append(", phoneNo=" + phoneNo + "]");
		return str.toString();
	}
}
