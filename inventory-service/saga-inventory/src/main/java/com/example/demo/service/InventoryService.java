package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.demo.dao.InventoryTransactionLogDao;
import com.example.demo.dao.ProductInventoryDao;
import com.example.demo.ds.CartItem;
import com.example.demo.ds.InventoryTransactionDto;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.InventoryTransactionLog;
import com.example.demo.exception.InsufficientQuantityException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
	private final ProductInventoryDao productInventoryDao;
	private final InventoryTransactionLogDao inventoryTransactionLogDao;
	private Logger logger =LoggerFactory
			.getLogger(getClass().getName().toString());
	@Transactional
	@RabbitListener(queues = "inventory_transaction_queue")
	public void inventoryDeductTransaction(InventoryTransactionDto dto) {
		var existingLog = inventoryTransactionLogDao
				.findById(dto.orderCode());
		if(existingLog.isPresent()) {
			logger.info("Order {} is already processed with status {}.",
					dto.orderCode(),existingLog.get().getStatus());
			return;
		}
		List<CartItem> cartItems =dto.cartItems();
		for(CartItem item:cartItems) {
			Inventory inventory=getProduct(item.id());
			if(item.quantity() <= inventory.getQuantity()) {
				inventory.setQuantity(inventory.getQuantity() 
						- item.quantity());
			}
			else {
				throw new InsufficientQuantityException();
			}
			productInventoryDao.save(inventory);
		}
		InventoryTransactionLog log=
				new InventoryTransactionLog(dto.orderCode(),
				InventoryTransactionLog.TransactionStatus.DEDUCTED);
		inventoryTransactionLogDao.save(log);
		
	}
	private Inventory getProduct(long id) {
		return productInventoryDao.findById(id)
				.get();
	}
}
