package com.PayMyBuddy.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

	public Iterable<Transaction> findBySenderAccount(int senderAccount);
	
	public Iterable<Transaction> findByReceiverAccount(int receiverAccount);
	
	public Iterable<Transaction> findBySenderAccountAndDateAfter(int senderAccount, Date date);
	
	public Iterable<Transaction> findByReceiverAccountAndDateAfter(int receiverAccount, Date date);
}
