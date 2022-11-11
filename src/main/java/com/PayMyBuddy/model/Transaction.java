package com.PayMyBuddy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "account_sender")
	private int senderAccount;
	
	@Column(name = "account_receiver")
	private int receiverAccount;
	
	@Column(name = "amount")
	private float amount;
	
	@Column(name = "date")
	private Date date;
	
	@Column (name = "description")
	private String description;
	

	@Column(name = "commission_Rate")
	private double commissionRate;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(int senderAccount) {
		this.senderAccount = senderAccount;
	}

	public int getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(int receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(double feesratepertransaction) {
		this.commissionRate = feesratepertransaction;
	}
	
	
}
