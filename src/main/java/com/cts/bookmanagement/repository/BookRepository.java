package com.cts.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.bookmanagement.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	

}
