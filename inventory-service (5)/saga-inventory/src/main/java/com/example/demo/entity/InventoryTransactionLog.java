package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="inventory_transaction_log")
@Getter
@Setter
@NoArgsConstructor
public class InventoryTransactionLog {
	@Id
	private String orderCode;
	@Enumerated(EnumType.STRING)
	private TransactionStatus status;
	private LocalDateTime updatedAt=LocalDateTime.now();
	
	public InventoryTransactionLog(
			String orderCode, 
			TransactionStatus status) {
		super();
		this.orderCode = orderCode;
		this.status = status;
	}

	public enum TransactionStatus{
		DEDUCTED,
		COMPENSATED
	}

}
