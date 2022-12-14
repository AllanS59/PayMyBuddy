package com.PayMyBuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	public List<Account> findByUserEmail(String userMail);
	
	
}


