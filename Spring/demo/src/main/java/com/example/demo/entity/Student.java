package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "students")
public class Student {
	
	private int id;
	private String firstName;
	private String lastName;
	private String className;
	private int rollNo;
	
	public Student() {}
	
	public Student(String firstName, String lastName, String className, int rollNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.className = className;
		this.rollNo = rollNo;
	}

	public Student(int id, String firstName, String lastName, String className, int rollNo) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.className = className;
		this.rollNo = rollNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	@Override
	public String toString() {
		StringBuilder std = new StringBuilder();
		std.append("Student [firstName=" + firstName); 
		std.append(", lastName=" + lastName);
		std.append(", className=" + className);
		std.append(", rollNo=" + rollNo + "]");
		return std.toString();
	}
	
}
