package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.InventoryTransactionLog;

public interface InventoryTransactionLogDao 
extends JpaRepository<InventoryTransactionLog,String>{
	
}
