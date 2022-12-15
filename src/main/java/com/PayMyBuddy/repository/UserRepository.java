package com.PayMyBuddy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	public Optional<User> findByEmail(String email);
	
	public Integer deleteByEmail(String email);
	List<User> removeByEmail(String email);
		
}
