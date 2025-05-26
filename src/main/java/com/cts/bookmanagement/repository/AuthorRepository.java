package com.cts.bookmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.bookmanagement.entity.Author;
import com.cts.bookmanagement.entity.Book;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	
	List<Book> findByBooksBookId(long id);

}
