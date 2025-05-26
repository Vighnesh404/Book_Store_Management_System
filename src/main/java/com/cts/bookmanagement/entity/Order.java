package com.cts.bookmanagement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private LocalDate orderDate;
	
	private Double totalAmount;
	private String status;
	
	private LocalDateTime orderCreatedDate;
	private boolean isOrderDeleted;
	
}
