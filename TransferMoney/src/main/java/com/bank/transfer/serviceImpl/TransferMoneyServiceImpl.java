package com.bank.transfer.serviceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.transfer.entity.Customer;
import com.bank.transfer.entity.TransferMoney;
import com.bank.transfer.repository.CustomerRepository;
import com.bank.transfer.repository.TransferMoneyRepository;
import com.bank.transfer.service.TransferMoneyService;

@Service
public class TransferMoneyServiceImpl implements TransferMoneyService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransferMoneyRepository transferMoneyRepository;

	@Override
	public String transferMoney(TransferMoney transfer) {
		transfer.setTransactionId(UUID.randomUUID().toString());
		if (transfer.getAmount() < 0) {
			return "Please add a valid amount";
		}

		Customer tobeDebited = customerRepository.findByAccountNumber(transfer.getFromAccount());
		Customer tobeCredited = customerRepository.findByAccountNumber(transfer.getToAccount());

		if (tobeDebited != null && tobeCredited != null) {
			if (tobeDebited.getAmount() >= transfer.getAmount()) {
				tobeDebited.setAmount(tobeDebited.getAmount() - transfer.getAmount());
				tobeCredited.setAmount(tobeCredited.getAmount() + transfer.getAmount());
				
				customerRepository.save(tobeCredited);
				customerRepository.save(tobeDebited);
				transferMoneyRepository.save(transfer);
				
				return "Transaction Successful !!";
			} else {
				return "Insufficient amount in the account for transfer";
			}
		} else {
			return "Debitter/Crediter account not found";
		}
	}

}
