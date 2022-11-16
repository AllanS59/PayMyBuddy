package com.PayMyBuddy.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	public List<Transaction> findBySenderAccount(int senderAccount);
	
	public List<Transaction> findByReceiverAccount(int receiverAccount);
	
	public List<Transaction> findBySenderAccountAndDateAfter(int senderAccount, Date date);
	
	public List<Transaction> findByReceiverAccountAndDateAfter(int receiverAccount, Date date);
}
