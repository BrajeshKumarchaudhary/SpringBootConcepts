package com.java.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Person {
   /*
    * alternative way of bean init and destroy method
    */
	
	@PreDestroy
	public void personInit() {
		System.out.println("Person init is called===================");
	}
	
	@PostConstruct
	public void personDestroy() {
		System.out.println("Person is going to called=======================");
	}
}
