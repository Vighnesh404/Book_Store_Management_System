package com.cts.bookmanagement.service;

import java.util.List;

import com.cts.bookmanagement.dto.AuthorDto;
import com.cts.bookmanagement.dto.BookDto;

public interface IAuthorService {
	
	AuthorDto addAuthor(AuthorDto authorDto);
	List<AuthorDto> viewAllAuthors();
	AuthorDto getAuthorById(Long authId);
	
	
	
	AuthorDto updateAuthorById(Long authId, AuthorDto authorDto);
	void deleteAuthorById(Long authId);
	List<BookDto> getBooksByAuthId(Long authId);
}
