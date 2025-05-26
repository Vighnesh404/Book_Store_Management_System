package com.cts.bookmanagement.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inventoryId;
	
	@OneToOne
	@JoinColumn(name = "book_id", unique = true)
	private Book book;
	
	private long quantity;
	
	private LocalDateTime invoCreatedDate;
	private boolean isInvoDeleted;
	
}
