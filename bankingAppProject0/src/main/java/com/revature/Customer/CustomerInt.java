package com.revature.Customer;

public interface CustomerInt {
	/*
	 * Customer Actions
	 */	
		  void applyAccount();
		  void viewAccount();
		  void withdrawAccount();
		  void depositAccount();
		  void beginTransfer();
		  void acceptTransfer();
		  boolean validAmount(int i);


}
