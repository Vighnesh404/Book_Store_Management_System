package com.cts.bookmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cts.bookmanagement.entity.Inventory;
import com.cts.bookmanagement.repository.InventoryRepository;

public class InventoryServiceImplement implements INventoryService{
	
	
	@Autowired
	InventoryRepository inventoryRepo;
	
	
	@Override
	public Inventory addInventory(Inventory inventory) {
		
		return null;
	}

	@Override
	public List<Inventory> viewAllInvenory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inventory getInventoryById(Long inventoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
