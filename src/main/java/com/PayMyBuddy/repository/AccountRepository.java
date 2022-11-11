package com.PayMyBuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{

	public Iterable<Account> findByUserEmail(String userMail);
	
	
}


