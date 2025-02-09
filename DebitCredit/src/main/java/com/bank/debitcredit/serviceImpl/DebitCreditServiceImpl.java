package com.bank.debitcredit.serviceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.debitcredit.entity.Customer;
import com.bank.debitcredit.entity.DebitCredit;
import com.bank.debitcredit.repository.CustomerRepository;
import com.bank.debitcredit.repository.DebitCreditRepository;
import com.bank.debitcredit.service.DebitCreditService;

@Service
public class DebitCreditServiceImpl implements DebitCreditService {

	@Autowired
	private DebitCreditRepository debitCreditRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public String debitMoney(DebitCredit transaction) {
		transaction.setTransactionId(UUID.randomUUID().toString());
		transaction.setType("debit");
		Customer customer = customerRepository.findByAccountNumber(transaction.getAccountNumber());

		// deduct the money from the customer account
		if (customer.getAmount() < transaction.getAmount()) {
			return "Insufficient balance in your account";
		} else {
			customer.setAmount(customer.getAmount() - transaction.getAmount());
		}

		customerRepository.save(customer);
		debitCreditRepository.save(transaction);
		return "Amount Debitted successfully , current balance is " + customer.getAmount();
	}

	@Override
	public String creditMoney(DebitCredit transaction) {
		transaction.setTransactionId(UUID.randomUUID().toString());
		transaction.setType("credit");
		Customer customer = customerRepository.findByAccountNumber(transaction.getAccountNumber());

		// add money in customer account
		customer.setAmount(customer.getAmount() + transaction.getAmount());

		customerRepository.save(customer);
		debitCreditRepository.save(transaction);
		return "Amount Credited successfully , current balance is " + customer.getAmount();
	}

}
