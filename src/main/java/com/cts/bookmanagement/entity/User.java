package com.cts.bookmanagement.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String password;
	
	private String role;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private boolean isDeleted;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<Order> order;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Review> review;
}

