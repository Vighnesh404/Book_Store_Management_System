package com.cts.bookmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bookmanagement.dto.BookDto;
import com.cts.bookmanagement.service.IBookService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/bookmanage")
public class BookController {

	@Autowired
	IBookService bookService;
	
	@PostMapping("/addbook")
	public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto){
		return new ResponseEntity<BookDto>(bookService.addBook(bookDto), HttpStatus.OK);
	}
	
	@GetMapping("/viewallbooks")
	public ResponseEntity<List<BookDto>> viewAllBooks(){
		return new ResponseEntity<List<BookDto>>(bookService.viewAllBooks(), HttpStatus.OK);
	}

	@GetMapping("/viewbookbyid/{bookId}")
	public ResponseEntity<BookDto> viewBookById(@PathVariable Long bookId)
	{
		return new ResponseEntity<BookDto>(bookService.getBookById(bookId), HttpStatus.OK);
	}
	
	@PutMapping("/updatebook/{bookId}")
	public ResponseEntity<BookDto> updateBookById(@PathVariable Long bookId, @RequestBody BookDto book)
	{
		return new ResponseEntity<BookDto>(bookService.updateBookById(bookId, book), HttpStatus.OK);
	}
	
	@DeleteMapping("/deletebook/{bookId}")
	public ResponseEntity<String> deleteBookById(@PathVariable Long bookId)
	{
		  bookService.deleteBookById(bookId);
		
		  return new ResponseEntity<String>("deleted successfully", HttpStatus.OK);
	}
}
