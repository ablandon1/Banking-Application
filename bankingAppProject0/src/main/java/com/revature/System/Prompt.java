package com.revature.System;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.sql.*;

import org.apache.log4j.Logger;

import com.revature.Customer.Customer;
import com.revature.Employee.Employee;


public class Prompt{
	
	

	 Scanner input = new Scanner(System.in);
	 Customer cus = new Customer();
	 Employee emp = new Employee();
	private static final Logger log = Logger.getLogger(Prompt.class.getName()); 
	UserDOA uDoa = new UserDOA();

	public void startUp() {
		
		
	
		//Config.configure();//"C:\\Users\\drebl\\eclipse-workspace\\bankingAppProject0\\src\\main\\log4j.properties");
		//log.info("\nsession start");
		System.out.println("--Thank you for choosing AB Banking!--");
		System.out.println("--Are you a new Customer?--");
		uDoa.setStrResponse(input.nextLine());
	
	
		if(uDoa.getStrResponse().equalsIgnoreCase("Yes") || uDoa.getStrResponse().equalsIgnoreCase("Y") && !validCustomer(uDoa.getUserID(),uDoa.getPassword())) {
			try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
			log.debug("Connectecd to database");
			log.debug("user selected yes, is new customer");
			System.out.println("--Follow the prompt to register new User ID--");
			System.out.println("--Please enter a userID--");
			uDoa.setUserID(input.nextLine());
			System.out.println("--Please enter a Password--");
			uDoa.setPassword(input.nextLine());
			
			PreparedStatement createUser = conn.prepareStatement("CALL create_user(?,?);");

			
			createUser.setString(1, uDoa.getUserID());
			createUser.setString(2, uDoa.getPassword());

			//while(validID(uDoa.getUserID())) {
				
				createUser.executeUpdate();
				System.out.println("--Your User Id and password have been registered!--");
				System.out.println("--Reload the Application to log in and apply for a new account with AB Banking!--");
			}

			
			/*
			 * if(!validID(uDoa.getUserID())) {
			 * System.out.println("--The User ID is not available--");
			 * System.out.println("--Enter a valid User ID and Password--");
			 * log.warn("userID already registered"); }
			 */
		
		
		
		 catch(Exception e) {
			log.error("Error detected in user_id or password");
			System.out.println("--Looks like your User ID has already registered--");
			System.out.println("--Reload Application and Try Again--");
			System.gc();
			//e.printStackTrace();
		}
		
		}	
		
		if(uDoa.getStrResponse().equalsIgnoreCase("No") || uDoa.getStrResponse().equalsIgnoreCase("N")) {
			  
			try {
			uDoa = new UserDOA(uDoa.getUserID());
			log.debug("user selected no, is either employee or  existing customer");
			System.out.println("--Please enter your userID--");
			uDoa.setUserID(input.nextLine());
			System.out.println("--Please enter your Password--");
			uDoa.setPassword(input.nextLine());
			
			if(validEmployee(uDoa.getUserID(),uDoa.getPassword())) {
				employeeOptionsMenu();
			}
			else if(validCustomer(uDoa.getUserID(),uDoa.getPassword())) {
				customerOptionsMenu();
			}
			else {
				System.out.println("--User ID or Password is incorrect--");
				System.out.println("--Enter a valid User ID or Password--");
				log.warn("userID or password is incorrect");				
			}

		}

		 catch(Exception e) {
			log.error("Error detected in user_id or password");
			 e.printStackTrace();
			 
		 }
		}
		
}
	
	public void customerOptionsMenu() {
		  
		//while(validCustomer(uDoa.getUserID(),uDoa.getPassword())) {  
		//AppTaskManager cusSession = new AppTaskManager("CustomerSession");
		 // cusSession.run();
		  System.out.println("--Enter a corresponding number to manage/view account(s)--");
		  System.out.println("--1. View Balance(s)--");
		  System.out.println("--2. Withdrawal--");
		  System.out.println("--3. Deposit--");
		  System.out.println("--4. Send Funds--");
		  System.out.println("--5. Recieve Funds--");
		  System.out.println("--6. Apply for New Account--");
		  System.out.println("--7. Log Off--");

		  log.debug("current user " + uDoa.getUserID());
		  
				  
			 try {
				  while(uDoa.getIntResponse() == 0) {

				 uDoa.setIntResponse(input.nextInt());
			  
			  switch(uDoa.getIntResponse()) {
		  	case 1:
				log.debug("Customer option 1..");
		  		cus.viewAccount();
		  		break;
		  	case 2:
				log.debug("Customer option 2..");
		  		cus.withdrawAccount();
		  		break;

		  	case 3:
				log.debug("Customer option 3..");
		  		cus.depositAccount();
			  	break;

			case 4:
				log.debug("Customer option 4..");
		  		cus.beginTransfer();
				break;

			case 5:
				log.debug("Customer option 5..");
		  		cus.acceptTransfer();
				break;
			case 6:
				log.debug("Customer option 6..");
		  		cus.applyAccount();
				break;
			case 7:
				System.out.println("--Thank you for choosing AB Banking! Good Bye!--");
				log.warn("logging off..");
				uDoa.setLoggedIn(true);
				do {
				startUp();
				}
				while(uDoa.isLoggedIn());

				
				break;
			default:
				uDoa.setIntResponse(0);
				log.error("default IntResponse..");
				}
		  }
			 }
			 catch (Exception e) {
			  	e.printStackTrace();
			  	log.error("Error on menu customer menu");
			}
		  
		
		  
	  }

	public void employeeOptionsMenu() {
		
		//AppTaskManager empSession =  new AppTaskManager("EmployeeSession");
		//empSession.run();
		System.out.println("--Enter a corresponding number to manage/view Tasks--");
		System.out.println("--1. Update Customer Account Decision--");
		System.out.println("--2. View Customer Account(s)--");
		System.out.println("--3. View Customer Transaction Log--");
		System.out.println("--4. Log Off--");
		  log.debug("current user " + uDoa.getUserID());

		try{
			
		while(uDoa.getIntResponse() == 0) {
		
			uDoa.setIntResponse(input.nextInt());
		
			switch(uDoa.getIntResponse()) {
		  	case 1:
				log.debug("Employee option 1..");
		  		emp.approveUser();
		  		break;
		  	case 2:
				log.debug("Employee option 2..");
		  		emp.viewCustomerAcc();
		  		break;
	
		  	case 3:
				log.debug("Employee option 3..");
		  		emp.viewTransLog();
			  	break;
		  	case 4:
		  		System.out.println("--Thank you for choosing AB Banking! Good Bye!--");
				log.warn("logging off..");
				uDoa.setLoggedIn(true);
				do {
				startUp();
				}
				while(uDoa.isLoggedIn());
				break;
		  	default : 
		  		uDoa.setIntResponse(0);				
		  		log.debug("invalid employee response..");
				System.out.println("--Please enter a valid response--");
			}
		}
		}
		catch (Exception e) {
			System.out.println("--Please enter a valid response--");
			uDoa.setIntResponse(0);				
		}
	}
		
	/*
	 * SQL here for new record in user tab
	 */	
	
	public void newCustomerMenu() throws SQLException {
		
		
		try { 
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
		log.debug("Connectecd to database");
		PreparedStatement createUser = conn.prepareStatement("call create_new_customer(?,?)");	
		
		System.out.println("--Please enter a User ID--");
		uDoa.setUserID(input.nextLine());
		createUser.setString(1, uDoa.getUserID());
		System.out.println("--Please enter a Password--");
		uDoa.setPassword(input.nextLine());
		createUser.setString(2, uDoa.getPassword());
		createUser.executeUpdate();
		System.out.println("\n--Your account has been created! Our team will update you with a decision soon!--");
		}
		catch (SQLException e) {
			e.printStackTrace();
			log.error("database cannot be reached, user not found");
		}
		finally {
			log.debug("New user account has been created");
		}
		
	}
	public boolean validCustomer(String id, String password) {
		
		try { 
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
		log.debug("Connectecd to database");
		PreparedStatement validUser = conn.prepareStatement("SELECT user_id, password FROM users WHERE user_id = ? AND password = ?;");	
		validUser.setString(1, id);
		validUser.setString(2, password);
		ResultSet rs = validUser.executeQuery();
		while(rs.next()) {
		if(rs.getString("user_id").equals(id) && rs.getString("password").equals(password)) {
			uDoa.setApproved(true);
		} else {
			uDoa.setApproved(false);
		}
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
			log.error("database cannot be reached, user not found or password incorrect");
		}
		finally {
			log.debug("Determining user id validity");
		}
		return uDoa.isApproved();
		
	}
	public boolean validEmployee(String id, String password) {
		
		try { 
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
		log.debug("Connectecd to database");
		PreparedStatement validUser = conn.prepareStatement("SELECT user_id, password FROM users WHERE user_id = ? AND password = ? AND is_employee = true;");	
		validUser.setString(1, id);
		validUser.setString(2, password);
		ResultSet rs = validUser.executeQuery();
		while(rs.next()) {
		if(rs.getString("user_id").equals(id) && rs.getString("password").equals(password)) {
			uDoa.setApproved(true);
		} else {
			uDoa.setApproved(false);
		}
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
			log.error("database cannot be reached, user not found or password incorrect");
		}
		finally {
			log.debug("Determining user id and password validity");
		}
		return uDoa.isApproved();
		
	}
	
	
	
}



