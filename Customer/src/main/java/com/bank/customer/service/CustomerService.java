package com.bank.customer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.customer.entity.Customer;

public interface CustomerService {
	
	Customer createCustomer(Customer customer);
	
	String deleteCustomer(String userName);
	
	String updateCustomer(Customer updatedCustomer);
	
	Customer viewCustomer(String userName);
	
	List<Customer> viewAllCustomers();
}
