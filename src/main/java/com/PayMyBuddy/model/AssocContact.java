package com.PayMyBuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "assoc_contacts")
@IdClass(AssocContactId.class)
public class AssocContact {

	@Id
	@Column(name = "email_user")
	private String userMail;
	
	@Id
	@Column(name = "email_contact")
	private String contactMail;

	
	
	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}
	
	
}
