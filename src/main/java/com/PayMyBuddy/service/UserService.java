package com.PayMyBuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.model.User;
import com.PayMyBuddy.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	//Get all Users
	public List<User> getUsers() {
		return userRepository.findAll();		
	}
	
	//Get user by Id (mail)
		public List<User> getUserByEmail(String email) {
			return userRepository.findByEmail(email);		
		}
	
	//Get add a new user
	public User addUser(User user) {
		return userRepository.save(user);	
	}
	
	//Delete a new user
	public void deleteUserById(String userMail) {
		userRepository.deleteById(userMail);
		}
	
	
}
