package com.cts.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.bookmanagement.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{

}
