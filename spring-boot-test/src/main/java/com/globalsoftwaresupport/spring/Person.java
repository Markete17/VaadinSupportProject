package com.globalsoftwaresupport.spring;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class Person {
	
	@NotEmpty(message = "Not empty")
	private String name;
	
	//Opcional
	private Integer age;
	
	@NotEmpty(message = "Not empty")
	@Email(message = "Must has email format")
	private String email;
	
	public Person() {
		// init person
	}

	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public Person(String name, Integer age, String email) {
		super();
		this.name = name;
		this.age = age;
		this.email = email;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", email=" + email + "]";
	}
}
