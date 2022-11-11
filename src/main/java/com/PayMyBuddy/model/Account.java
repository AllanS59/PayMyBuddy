package com.PayMyBuddy.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.PayMyBuddy.constants.AccountType;

@Entity
@Table (name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "email_user")
	private String userEmail;
	
	@Column(name = "balance_Checkpoint")
	private float balanceCheckpoint;
	
	@Column(name = "date_Checkpoint")
	private Date dateCheckpoint;

	@Enumerated(EnumType.STRING)
	@Column(name = "type_account")
	private AccountType accountType;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public float getBalanceCheckpoint() {
		return balanceCheckpoint;
	}

	public void setBalanceCheckpoint(float balanceCheckpoint) {
		this.balanceCheckpoint = balanceCheckpoint;
	}

	public Date getDateCheckpoint() {
		return dateCheckpoint;
	}

	public void setDateCheckpoint(Date dateCheckpoint) {
		this.dateCheckpoint = dateCheckpoint;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	
}
