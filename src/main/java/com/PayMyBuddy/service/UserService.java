package com.PayMyBuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.model.User;
import com.PayMyBuddy.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	//Get all Users
	public Iterable<User> getUsers() {
		return userRepository.findAll();		
	}
	
	//Get user by Id (mail)
		public Iterable<User> getUserByEmail(String email) {
			return userRepository.findByEmail(email);		
		}
	
	//Get add a new user
	public User addUser(User user) {
		return userRepository.save(user);	
	}
	
	
}
