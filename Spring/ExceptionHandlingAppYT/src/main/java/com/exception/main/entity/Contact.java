package com.exception.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {

	@ApiModelProperty(notes = "Unique identifier of the Contact.", 
	        example = "1", required = true, position = 0)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phoneNo;

	public Contact(String firstName, String lastName, String email, String phoneNo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
	}


	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Contact [id=" + id);
		str.append(", firstName=" + firstName);
		str.append(", lastName=" + lastName);
		str.append(", email=" + email);
		str.append(", phoneNo=" + phoneNo + "]");
		return str.toString();
	}

}
