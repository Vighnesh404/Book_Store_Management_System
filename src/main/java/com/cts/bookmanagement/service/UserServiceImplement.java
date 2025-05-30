package com.cts.bookmanagement.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bookmanagement.dto.UserDto;
import com.cts.bookmanagement.entity.User;
import com.cts.bookmanagement.exception.EmailAlreadyExistsException;
import com.cts.bookmanagement.exception.ResourceNotFoundException;
import com.cts.bookmanagement.repository.UserRepository;

@Service
public class UserServiceImplement implements IUserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	
	@Override
	public UserDto addUser(UserDto userDto) {
		
//		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
//		if(optionalUser.isPresent()) {
//			throw new EmailAlreadyExistsException("Email Already Exists");
//		}
		
		Optional<User> optionalUserName = userRepository.findByName(userDto.getName());
		if(optionalUserName.isPresent()) {
			throw new EmailAlreadyExistsException("User Name Already Exists");
		}
		
		User newUser = modelMapper.map(userDto , User.class);
		
		String password = userDto.getPassword();
	    if (!isValidPassword(password)) {
	        throw new IllegalArgumentException("Password must be at least 8 characters "
	        		+ "long and include at least one alphabet, "
	        		+ "one number, and one special character.");
	    }
	    newUser.setPassword(password);
	    
		newUser.setRole("user");
		newUser.setCreatedDate(LocalDateTime.now());
		newUser.setUpdatedDate(LocalDateTime.now());
		newUser.setDeleted(false);
		
		User saveUser = userRepository.save(newUser);
		return modelMapper.map(saveUser, UserDto.class);
//		return userDto;
	}
	
	
//---this gives the users only who has the isDelete as the false---
	
	@Override
	public List<UserDto> viewAllUsers() {
	
		List<User> users = userRepository.findAll();
		List<UserDto> userDtos = new ArrayList<>();
		
		for(User user : users ) {
			if(!user.isDeleted()) {
				userDtos.add(modelMapper.map(user, UserDto.class));
			}
		}
		
		return userDtos;
	}

	
//---this gives the users only who has the isDelete as the false---
	
//	@Override
//	public UserDto getUserById(Long userId) {
//		Optional<User> optionalUser = userRepository.findById(userId);
//	    if (optionalUser.isPresent() && !optionalUser.get().isDeleted()) {
//	        return modelMapper.map(optionalUser.get(), UserDto.class);
//	    }
//
//	    return null; // Returns null if user doesn't exist or is deleted
//	}

	
	@Override
	public UserDto getUserById(Long userId) {
		User optionalUser = userRepository.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User", "id", userId)
				);
			return modelMapper.map(optionalUser, UserDto.class);
		// Returns null if user doesn't exist or is deleted
	}


//	@Override
//	public UserDto updateUserById(Long userId, UserDto userDto) {
//		
//		User updateUser = userRepository.findById(userId).get();
//		
//		updateUser.setName(userDto.getName());
//		updateUser.setEmail(userDto.getEmail());
//		
//		
//		 String password = userDto.getPassword();
//		    if (!isValidPassword(password)) {
//		        throw new IllegalArgumentException("Password must be at least 8 characters long and include at least one alphabet, one number, and one special character.");
//		    }
//		 updateUser.setPassword(password);
//		 
////		updateUser.setRole(user.getRole());
//		 
////		  String role = userDto.getRole();
////		    if (!isValidRole(role)) {
////		        throw new IllegalArgumentException("Role must be either 'user' or 'admin'.");
////		    }
////		    updateUser.setRole(role);
//		    
//		    updateUser.setUpdatedDate(LocalDateTime.now());
//		    
//		
//		User saveUser = userRepository.save(updateUser);
//		
//		return modelMapper.map(saveUser, UserDto.class);
//	}
	
	
	@Override
	public UserDto updateUserById(Long userId, UserDto userDto) {
		
		User updateUser = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "Id", userId)
				);
		
		updateUser.setName(userDto.getName());
		updateUser.setEmail(userDto.getEmail());
		
		
		String password = userDto.getPassword();
		if (!isValidPassword(password)) {
			throw new IllegalArgumentException("Password must be at least 8 characters long and include at least one alphabet, one number, and one special character.");
		}
		updateUser.setPassword(password);
		
//		updateUser.setRole(user.getRole());
		
//		  String role = userDto.getRole();
//		    if (!isValidRole(role)) {
//		        throw new IllegalArgumentException("Role must be either 'user' or 'admin'.");
//		    }
//		    updateUser.setRole(role);
		
		updateUser.setUpdatedDate(LocalDateTime.now());
		
		
		User saveUser = userRepository.save(updateUser);
		
		return modelMapper.map(saveUser, UserDto.class);
	}
	
	
	@Override
	public void deleteUserById(long userId) {
		
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "Id", userId)
				);
	
		
		userRepository.save(user);
	}
	
	
	@Override
	public void deleteUserByIdPermenent(long userId) {
		
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "Id", userId)
				);
		
		userRepository.delete(user);
	}

	
	
//Password Validation for having atleast 1 alphabet,, 1 number and 1 special Character 

	private boolean isValidPassword(String password) {
	    if (password == null || password.length() < 8) {
	        return false;
	    }
	    boolean hasAlphabet = false;
	    boolean hasNumber = false;
	    boolean hasSpecialChar = false;
	    String specialChars = "!@#$%^&*()-+_=~`[]\\{}|;':\",./<>?";

	    for (char ch : password.toCharArray()) {
	        if (Character.isLetter(ch)) {
	            hasAlphabet = true;
	        } else if (Character.isDigit(ch)) {
	            hasNumber = true;
	        } else if (specialChars.contains(String.valueOf(ch))) {
	            hasSpecialChar = true;
	        }
	    }
	    return hasAlphabet && hasNumber && hasSpecialChar;
	}
	
	
//-------------Role Validation------------+
	
//	private boolean isValidRole(String role) {
//	    return "user".equalsIgnoreCase(role) || "admin".equalsIgnoreCase(role);
//	}


}
