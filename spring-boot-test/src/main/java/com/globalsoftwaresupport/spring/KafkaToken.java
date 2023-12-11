//package com.globalsoftwaresupport.spring;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import lombok.Getter;
//
//@Getter
//@Component
//public class KafkaToken {
//	
//	private String token;
//	
//	@KafkaListener(topics = "java_in_use_topic", groupId = "grupo")
//	public void listenGroupFoo(String message) {
//	    System.out.println("Received Message in group foo: " + message);
//	    System.out.println("Received Message in group foo: " + message);
//	    System.out.println("Received Message in group foo: " + message);
//	    System.out.println("Received Message in group foo: " + message);
//	    System.out.println("Received Message in group foo: " + message);
//	    this.token = message;
//	}
//
//}
