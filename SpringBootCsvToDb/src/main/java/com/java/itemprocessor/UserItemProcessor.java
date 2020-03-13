package com.java.itemprocessor;

import org.springframework.batch.item.ItemProcessor;

import com.java.model.Employee;

public class UserItemProcessor implements ItemProcessor<Employee, Employee> {

	@Override
	public Employee process(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		Employee transform=new Employee(employee.getFirstname(), employee.getLastname(), employee.getEmail(),employee.getAge());
		return transform;
	}

}
