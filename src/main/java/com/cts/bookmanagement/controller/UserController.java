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

import com.cts.bookmanagement.dto.UserDto;
import com.cts.bookmanagement.service.IUserService;

import jakarta.validation.Valid;


//@Validated
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	

	@PostMapping("/adduser")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
		return new ResponseEntity<UserDto>(userService.addUser(userDto), HttpStatus.OK);
	}
	
	@GetMapping("/viewallusers")
	public ResponseEntity<List<UserDto>> viewAllUsers(){
		return new ResponseEntity<List<UserDto>>(userService.viewAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/viewuserbyid/{userId}")
	public ResponseEntity<UserDto> viewUserById(@PathVariable Long userId)
	{
		return new ResponseEntity<UserDto>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	@PutMapping("/updateuser/{userId}")
	public ResponseEntity<UserDto> updateUserById(@Valid @PathVariable Long userId, @RequestBody UserDto user)
	{
		return new ResponseEntity<UserDto>(userService.updateUserById(userId, user), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long userId){
		  userService.deleteUserById(userId);
		  return new ResponseEntity<String>("deleted successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteusertotally/{userId}")
	public ResponseEntity<String> deleteUserByIdPermenent(@PathVariable Long userId){
		userService.deleteUserById(userId);
		return new ResponseEntity<String>("deleted successfully fro the database", HttpStatus.OK);
	}
	

}
