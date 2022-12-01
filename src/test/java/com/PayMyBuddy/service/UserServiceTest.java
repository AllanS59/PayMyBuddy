package com.PayMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.AfterAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.PayMyBuddy.model.User;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class UserServiceTest {

	
	@Autowired
	private UserService userService;
	
	
	@Test
	public void getUsersTest() {
		
		List<User> users = userService.getUsers();
		User[] usersArray = users.toArray(new User[0]);
		
		assertEquals("Bernard", usersArray[1].getFirstName());
		assertEquals("06-07-08-09-10", usersArray[2].getPhone());
	}
	
	@Test
	public void getUserByEmailTest() {
		
		List<User> users = userService.getUserByEmail("sshoto@mail.com");
		User[] usersArray = users.toArray(new User[0]);
		assertEquals("Sakura", usersArray[0].getFirstName());
		
	}
	
	@Test
	public void addUserTest() {
		
		User newUser = new User();
		newUser.setFirstName("Marcel");
		newUser.setLastName("Deschamps");
		newUser.setEmail("mdeschamps@mail.com");
		newUser.setPassword("passTest");
		newUser.setAddress("5 test address 12345 City");
		newUser.setPhone("175-475-954");
		userService.addUser(newUser);
		
		List<User> users = userService.getUserByEmail("mdeschamps@mail.com");
		User[] usersArray = users.toArray(new User[0]);
		
		assertEquals("Marcel", usersArray[0].getFirstName());	
		
		userService.deleteUserById("mdeschamps@mail.com");
	}
	
	
	@Test
	public void deleteUserTest() {
		
		User newUser = new User();
		newUser.setFirstName("Jean");
		newUser.setLastName("Moulin");
		newUser.setEmail("jmoulin@mail.com");
		newUser.setPassword("passTest");
		newUser.setAddress("5 test address 12345 City");
		newUser.setPhone("175-475-954");
		userService.addUser(newUser);
		
		userService.deleteUserById("jmoulin@mail.com");
		
		List<User> users = userService.getUserByEmail("jmoulin@mail.com");
		
		assertEquals(false, users.iterator().hasNext());	
	}
}


