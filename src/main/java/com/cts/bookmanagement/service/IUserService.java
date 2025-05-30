package com.cts.bookmanagement.service;

import java.util.List;

import com.cts.bookmanagement.dto.UserDto;

public interface IUserService {
//	User addUser(User user);
//	List<User> viewAllUsers();
//	User getUserById(Long userId);
//	User updateUserById(Long userId, User user);
//	void deleteUserById(long userId);
	
	
	UserDto addUser(UserDto user);
	List<UserDto> viewAllUsers();
	UserDto getUserById(Long userId);
	UserDto updateUserById(Long userId, UserDto userDto);
	void deleteUserById(long userId);
	void deleteUserByIdPermenent(long userId);
	

}
