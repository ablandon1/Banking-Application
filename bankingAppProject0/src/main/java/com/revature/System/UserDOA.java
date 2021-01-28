package com.revature.System;

import org.apache.log4j.Logger;


public class UserDOA {
	
	private static final Logger log = Logger.getLogger(UserDOA.class.getName());

	protected String password;
	protected String userID;
	protected int intResponse;
	protected String strResponse;
	protected int accountID; 
	protected int amount;
	protected int receiveID; 
	protected boolean approved;
	protected String senderID;
	private boolean loggedIn;
	
	
	public UserDOA() {
		super();
	}
	public UserDOA(String userID) {
		this.userID = getUserID();
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public String getSenderID() {
		return senderID;
	}
	public void setSenderID(String senderID) {
		this.senderID = senderID;
		log.debug("Sender ID set to " + senderID);
	}
	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
		log.debug("isApproved set to " + approved);

	}

	public int getReceiveID() {
		return receiveID;
	}

	public void setReceiveID(int receiveID) {
		this.receiveID = receiveID;
		log.debug("receiveID set to " + receiveID);

	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		log.debug("amount set to " + amount);

	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
		log.debug("accountID set to " + amount);
	}

	public int getIntResponse() {
		return intResponse;
	}

	public void setIntResponse(int intResponse) {
		this.intResponse = intResponse;
		log.debug("intRespose set to " + intResponse);

	}

	public String getStrResponse() {
		return strResponse;
	}

	public void setStrResponse(String strResponse) {
		this.strResponse = strResponse;
		log.debug("strRespose set to " + strResponse);

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		log.debug("password set to " + password);

	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
		log.debug("userID set to " + userID);

	} 
}
