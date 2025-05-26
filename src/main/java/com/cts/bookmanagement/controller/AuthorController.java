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

import com.cts.bookmanagement.dto.AuthorDto;
import com.cts.bookmanagement.dto.BookDto;
import com.cts.bookmanagement.service.IAuthorService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/bookmanage")
public class AuthorController {
	
	@Autowired
	IAuthorService authorService;
	
	@PostMapping("/addnewauthor")
	public ResponseEntity<AuthorDto> addAuthor(@Valid @RequestBody AuthorDto authorDto){
		return new ResponseEntity<AuthorDto>(authorService.addAuthor(authorDto), HttpStatus.OK);
	}
	
	@GetMapping("/viewallauthor")
	public ResponseEntity<List<AuthorDto>> viewAllAuthors(){
		return new ResponseEntity<List<AuthorDto>>(authorService.viewAllAuthors(), HttpStatus.OK);
	}
	
	@GetMapping("/viewallbooksbyauthid/{authId}")
	public ResponseEntity<List<BookDto>> getBooksByAuthId(@Valid @PathVariable Long authId){
		return new ResponseEntity<List<BookDto>>(authorService.getBooksByAuthId(authId), HttpStatus.OK);
	}
	
	@GetMapping("/getauthorbyid/{authId}")
	public ResponseEntity<AuthorDto> getAuthorById(@Valid @PathVariable Long authId){
		return new ResponseEntity<AuthorDto>(authorService.getAuthorById(authId), HttpStatus.OK);
	}
	
	@PutMapping("/updateauthor/{authId}")
	public ResponseEntity<AuthorDto> updateAuthorById(@Valid @PathVariable Long authId, @RequestBody AuthorDto authorDto){
		return new ResponseEntity<AuthorDto>(authorService.updateAuthorById(authId, authorDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteauthor/{authId}")
	
	public ResponseEntity<String> deleteAuthorById(@Valid @PathVariable Long authId){
		authorService.deleteAuthorById(authId);
		
		return new ResponseEntity<String>("Author has been successfully deleted", HttpStatus.OK);
	}
	
}
