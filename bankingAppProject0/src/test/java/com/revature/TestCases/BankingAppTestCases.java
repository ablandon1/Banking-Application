package com.revature.TestCases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;

import com.revature.Customer.Customer;
import com.revature.System.Prompt;

public class BankingAppTestCases {
	
Customer cus = new Customer();
Prompt prom = new Prompt();

	//@Disabled
	@Test
	void testWithDraw() {
		System.out.println("--Testing INVALID WITHDRAWAL: amount <=0--");
		Assertions.assertEquals(true, cus.validAmount(-1));

	}
	//@Disabled
	@Test
	void testValidUser() {
		System.out.println("--Testing Valid User: return true--");
		Assertions.assertEquals(true, prom.validCustomer("user2", "password"));

	}
	
	@Test
	void testAccountEligible() {
		System.out.println("--Testing Valid User: return true--");
		Assertions.assertEquals(true, cus.accEligible("user1"));

	}
}
