package com.cts.bookmanagement.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bookmanagement.dto.BookDto;
import com.cts.bookmanagement.entity.Book;
import com.cts.bookmanagement.repository.AuthorRepository;
import com.cts.bookmanagement.repository.BookRepository;
import com.cts.bookmanagement.repository.CategoryRepository;

@Service
public class BookServiceImplement implements IBookService{
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorRepository authRepo;
	
	@Autowired
	CategoryRepository catRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public BookDto addBook(BookDto bookDto) {
		
		Book newBook = modelMapper.map(bookDto, Book.class);
		
		newBook.setBookCreatedDate(LocalDateTime.now());
		newBook.setBookDeleted(false);
		
		Book saveBook = bookRepository.save(newBook);
		
		BookDto mappedDto =  modelMapper.map(saveBook, BookDto.class);
		
		return mappedDto;
	}

	@Override
	public List<BookDto> viewAllBooks() {
//		return bookRepository.findAll();
		
		List<Book> books = bookRepository.findAll();
		List<BookDto> bookDtos = new ArrayList<>();
		for(Book book : books) {
			if(!book.isBookDeleted()) {
				bookDtos.add(modelMapper.map(book, BookDto.class));
			}
		}
		return bookDtos;
	}
	
	@Override
	public BookDto getBookById(Long bookId) {
	    Book book = bookRepository.findById(bookId).get();
	    	if(!book.isBookDeleted()) {
	    		return modelMapper.map(book, BookDto.class);
	    	}
	    	return null;
	}

	@Override
	public BookDto updateBookById(Long bookId, BookDto bookDto) {
	    Book updateBook = bookRepository.findById(bookId).get();
	    if(!updateBook.isBookDeleted()) {
	    	updateBook.setTitle(bookDto.getTitle());
	    	updateBook.setPrice(bookDto.getPrice());
	    	updateBook.setStockQuantity(bookDto.getStockQuantity());
	    
	    	Book savedBook = bookRepository.save(updateBook);
	    	return modelMapper.map(savedBook, BookDto.class);
	    }
	    return null;
	}

	@Override
	public void deleteBookById(Long bookId) {
	    Book book = bookRepository.findById(bookId).get();
//	    		orElseThrow(() -> new ResourceNotFoundException("Book not found"));
	    book.setBookDeleted(true);
	    bookRepository.save(book);
	}



}
