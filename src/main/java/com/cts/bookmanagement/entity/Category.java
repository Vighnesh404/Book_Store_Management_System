package com.cts.bookmanagement.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long catId;
	private String catName;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Book> books;
	
	private LocalDateTime catCreatedDate;
	private boolean isCatDeleted;
	
}
