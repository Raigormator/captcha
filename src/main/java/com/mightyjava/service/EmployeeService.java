package com.mightyjava.service;

import java.util.Set;

import com.mightyjava.model.Employee;

public interface EmployeeService {
	void add(Employee employee);
	
	Set<Employee> employees();
}
