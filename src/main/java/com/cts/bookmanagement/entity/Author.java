package com.cts.bookmanagement.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authId;
	
	@NotBlank
	private String authName;
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Book> books;
	
	private LocalDateTime authorCreatedDate;
	private boolean isAuthDeleted;
}
