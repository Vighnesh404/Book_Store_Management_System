package com.cts.bookmanagement.service;

import java.util.List;

import com.cts.bookmanagement.dto.BookDto;

public interface IBookService {
	
//	Book addBook(Book book);
	List<BookDto> viewAllBooks();
	BookDto getBookById(Long bookId);
	BookDto updateBookById(Long bookId, BookDto bookDto);
	void deleteBookById(Long bookId);
	
	
	BookDto addBook(BookDto bookDto);
	
}
