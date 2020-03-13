package com.java.model;

public class Employee {

	private String firstname;
	private String lastname;
	private String email;
	private int age;
	public Employee(String firstname, String lastname, String email, int age) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.age = age;
	}
	public Employee()
	{
		
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
}
