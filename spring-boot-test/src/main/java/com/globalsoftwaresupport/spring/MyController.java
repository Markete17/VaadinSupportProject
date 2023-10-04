package com.globalsoftwaresupport.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@Autowired
	private Student student;
	
	@GetMapping(value="/index")
	public String sayHello() {
		return student.showInfo();
	}
}
