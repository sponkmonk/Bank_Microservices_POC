package com.bank.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.transfer.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{
	
	Customer findByAccountNumber(String accountNumber);

}
