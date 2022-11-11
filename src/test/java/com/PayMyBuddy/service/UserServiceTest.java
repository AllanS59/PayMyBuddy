package com.PayMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.PayMyBuddy.model.User;

@SpringBootTest
public class UserServiceTest {

	
	@Autowired
	private UserService userService;
	
	
	@Test
	public void getUsersTest() {
		
		Iterable<User> users = userService.getUsers();
		User[]usersArray = StreamSupport.stream(users.spliterator(), false).toArray(User[]::new);
		assertEquals("Bernard", usersArray[1].getFirstName());
		assertEquals("06-07-08-09-10", usersArray[2].getPhone());
	}
	
	@Test
	public void getUserByEmailTest() {
		
		Iterable<User> users = userService.getUserByEmail("sshoto@mail.com");
		User[]usersArray = StreamSupport.stream(users.spliterator(), false).toArray(User[]::new);
		assertEquals("Sakura", usersArray[0].getFirstName());
		
	}
}


//	//Get add a new user
//	public User addUser(User user)