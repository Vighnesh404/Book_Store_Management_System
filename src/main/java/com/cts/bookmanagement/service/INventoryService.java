package com.cts.bookmanagement.service;

import java.util.List;

import com.cts.bookmanagement.entity.Inventory;

public interface INventoryService {
	
	Inventory addInventory(Inventory inventory);
	List<Inventory> viewAllInvenory();
	Inventory getInventoryById(Long inventoryId);
	
}
