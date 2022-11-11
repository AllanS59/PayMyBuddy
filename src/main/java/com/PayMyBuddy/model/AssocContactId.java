package com.PayMyBuddy.model;

import java.io.Serializable;

public class AssocContactId implements Serializable {

	private String userMail;
	
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
