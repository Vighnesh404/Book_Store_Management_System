package com.cts.bookmanagement.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	
	@NotNull
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	@NotBlank
	private Author author;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@NotBlank
	private Category category;
	
	@NotNull
	@Min(value = 0, message = "Must be more than 0")
	private Double price;
	
	@NotNull
	@Min(value = 0, message = "Out of Stock")
	private int stockQuantity;
	private LocalDateTime bookCreatedDate;
	private boolean isBookDeleted;
	
	@OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
	private Inventory inventory;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private List<Review> review;
}
