package com.globalsoftwaresupport.spring;

import org.springframework.stereotype.Component;

@Component
public class Address {

	private String address;
	
	public Address() {
		this.address = "Wall Street 20";
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
