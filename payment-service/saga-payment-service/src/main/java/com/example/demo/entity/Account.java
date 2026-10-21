package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String accountNumber;
	private String username;
	private BigDecimal amount;
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions=
			new ArrayList<>();
	
	public void addTransaction(Transaction transaction) {
		transaction.setAccount(this);
		transactions.add(transaction);
	}
	
	public Account(String accountNumber, String username, BigDecimal amount) {
		super();
		this.accountNumber = accountNumber;
		this.username = username;
		this.amount = amount;
	}
	
	
}









