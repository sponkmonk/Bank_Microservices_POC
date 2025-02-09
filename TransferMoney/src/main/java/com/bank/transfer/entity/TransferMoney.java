package com.bank.transfer.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferMoney {
	
	@Id
	private String transactionId;
	
	private String fromAccount;
	
	private String toAccount;
	
	private double amount;
	
	@CreationTimestamp
	private LocalDateTime time;

}
