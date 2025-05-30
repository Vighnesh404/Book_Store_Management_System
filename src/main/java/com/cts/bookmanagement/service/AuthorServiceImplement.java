package com.cts.bookmanagement.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bookmanagement.dto.AuthorDto;
import com.cts.bookmanagement.dto.BookDto;
import com.cts.bookmanagement.entity.Author;
import com.cts.bookmanagement.entity.Book;
import com.cts.bookmanagement.repository.AuthorRepository;

@Service
public class AuthorServiceImplement implements IAuthorService{
	
	@Autowired
	AuthorRepository authorRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public AuthorDto addAuthor(AuthorDto authorDto) {
		Author newAuthor = modelMapper.map(authorDto, Author.class);
		
		newAuthor.setAuthorCreatedDate(LocalDateTime.now());
		newAuthor.setAuthDeleted(false);
		
//		newAuthor.getBooks().forEach(b -> b.setAuthor(newAuthor));
		
		Author savedAuthor = authorRepo.save(newAuthor);
		return modelMapper.map(savedAuthor, AuthorDto.class);
	}

	@Override
	public List<AuthorDto> viewAllAuthors() {
		List<Author> authors = authorRepo.findAll();
		List<AuthorDto> authorDtos = new ArrayList<>();
		for(Author author : authors) {
			if(!author.isAuthDeleted()) {
				authorDtos.add(modelMapper.map(author, AuthorDto.class));
			}
		}
		return authorDtos;
	}
	
	@Override
	public AuthorDto getAuthorById(Long authId) {
			Author author = authorRepo.findById(authId).get();
			if(!author.isAuthDeleted()) {
				return modelMapper.map(author, AuthorDto.class);
			}
		return null;
	}
	
	@Override
	public List<BookDto> getBooksByAuthId(Long authId){
		Author author = authorRepo.findById(authId).orElse(null);
		if(author != null && !author.isAuthDeleted()) {
			List<BookDto> bookDtos = new ArrayList<>();
			for(Book book : author.getBooks()) {
				if(!book.isBookDeleted()) {
					bookDtos.add(modelMapper.map(book, BookDto.class));
				}
			}	
			return bookDtos;
		}
		return new ArrayList<>();
		
	}

	@Override
	public AuthorDto updateAuthorById(Long authId, AuthorDto authorDto) {
			Author updateAuth = authorRepo.findById(authId).get();
			if(!updateAuth.isAuthDeleted()) {
				updateAuth.setAuthName(authorDto.getAuthName());
				
				Author saveAuth = authorRepo.save(updateAuth);
				return modelMapper.map(saveAuth, AuthorDto.class);
			}
			return null;
	}

	@Override
	public void deleteAuthorById(Long authId) {
		Author author = authorRepo.findById(authId).get();
		author.setAuthDeleted(true);
		authorRepo.save(author);
	}

	

}
