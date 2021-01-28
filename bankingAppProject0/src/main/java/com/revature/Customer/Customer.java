package com.revature.Customer;

import java.sql.*;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.System.Prompt;
import com.revature.System.UserDOA;


public class Customer implements CustomerInt{
	
	private static final Logger log = Logger.getLogger(Customer.class.getName()); 
	Scanner input = new Scanner(System.in);
	Prompt prom;
	UserDOA uDoa = new UserDOA();
	
	
	public void viewAccount() {
		//SELECT SQL
		log.debug("customer view account..");
		prom = new Prompt();
		System.out.println("--Please re-enter your user ID--");
		uDoa.setUserID(input.nextLine());

		try {
			System.out.println(uDoa.getUserID());
			 Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
			log.debug("Connected to database");
			System.out.println("Enter the account ID number of the account to view current balance");
			uDoa.setAccountID(input.nextInt());
			PreparedStatement viewCusAcc = conn.prepareStatement("select balance from accounts where account_id = ?;");
			viewCusAcc.setInt(1, uDoa.getAccountID());
			ResultSet rs = viewCusAcc.executeQuery();
			while(rs.next()) {
				System.out.println("\nCurrent balance is: $" + rs.getInt("balance") + "\n");
				pendingNotification();

			}
		}
		catch(SQLException e) {
			System.out.println("..There seems to be an error on our end, please contact support!..");
			log.error("error with connecting to database, check SQL");
			e.printStackTrace();
		}
			System.out.println("\n--Enter '0' to return to the main menu--");
			uDoa.setIntResponse(input.nextInt());
			if(uDoa.getIntResponse() == 0) {
				
				uDoa.setUserID(uDoa.getUserID());
				prom.customerOptionsMenu(); 
			}
			
	}
	
	public void withdrawAccount() {
		//UPDATE SQL WITH MESSAGE
		log.debug("customer withdraw account..");
		prom = new Prompt();
		try {
			 Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
			log.debug("Connectecd to database");
			CallableStatement wdAcc = conn.prepareCall("CALL withdraw_account(?,?);");

			System.out.println("\n--Enter the account ID number of the account to you wish to withdraw from--");
			uDoa.setAccountID(input.nextInt());
			System.out.println("\n--Enter the amount you wish to withdraw--");
			uDoa.setAmount(input.nextInt());
			wdAcc.setInt(1, uDoa.getAccountID());
			wdAcc.setInt(2, uDoa.getAmount());
			if(validAmount(uDoa.getAmount())){
				wdAcc.execute();
				System.out.println("\n--Your funds are on their way--");
			}
			
		}catch(SQLException e) {
			System.out.println("..There seems to be an error on our end, please contact support!..");
			log.error("error with connecting to database, check SQL");
			e.printStackTrace();
		}
			System.out.println("\n--Enter '0' to return to the main menu--");
			uDoa.setIntResponse(input.nextInt());
			if(uDoa.getIntResponse() == 0) prom.customerOptionsMenu(); 
			
	}
	
	public void depositAccount() {
		//UPDATE SQL WITH MESSSAGE
		log.debug("customer deposit account..");
		prom = new Prompt();
	
			try {
				 Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
				log.debug("Connectecd to database");
				CallableStatement depAcc = conn.prepareCall("CALL deposit_account(?,?);");

				System.out.println("\n--Enter the account ID number of the account to you wish to deposit to--");
				uDoa.setAccountID(input.nextInt());
				System.out.println("\n--Enter the amount you wish to deposit--");
				uDoa.setAmount(input.nextInt());
				System.out.println(uDoa.getAccountID());

				depAcc.setInt(1, uDoa.getAccountID());
				depAcc.setInt(2, uDoa.getAmount());
				
				if(validAmount(uDoa.getAmount())){
					depAcc.execute();
					System.out.println("\n--Your funds are on their way--");
				}
		}catch(SQLException e) {
			System.out.println("..There seems to be an error on our end, please contact support!..");
			log.error("error with connecting to database, check SQL");
			e.printStackTrace();
		}
			System.out.println("\n--Enter '0' to return to the main menu--");
			uDoa.setIntResponse(input.nextInt());
			if(uDoa.getIntResponse() == 0) prom.customerOptionsMenu();
		
	}
	public void beginTransfer() {
		//UPDATE WITH TRIGGER
		log.debug("customer begin transfer..");
		prom = new Prompt();
		try {
			 Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
			log.debug("Connectecd to database");
			CallableStatement begTrans = conn.prepareCall("CALL begin_transfer(?,?,?);");

			System.out.println("\n--Enter the Account ID of the SENDING account-");
			uDoa.setAccountID(input.nextInt());
			
			System.out.println("\n--Enter the Account ID of the RECIEVING account-");
			uDoa.setReceiveID(input.nextInt());
			
			System.out.println("\n--Enter the amount you wish to SEND--");
			uDoa.setAmount(input.nextInt());
			
			begTrans.setInt(1, uDoa.getAccountID());
			begTrans.setInt(2, uDoa.getReceiveID());
			begTrans.setInt(3, uDoa.getAmount());
			begTrans.execute();
			
			System.out.println("\n--Your transfer has started!--");
		}catch(SQLException e) {
			System.out.println("..There seems to be an error on our end, please contact support!..");
			log.error("error with connecting to database, check SQL");
			e.printStackTrace();
		}
			System.out.println("\n--Enter '0' to return to the main menu--");
			uDoa.setIntResponse(input.nextInt());
			if(uDoa.getIntResponse() == 0) prom.customerOptionsMenu();
		

	}
	public void acceptTransfer() {
		//RESPONSE FROM TRIGGER
		log.debug("customer accept account..");
		log.debug("customer begin transfer..");
		prom = new Prompt();
		try {
			 Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
			log.debug("Connectecd to database");
			CallableStatement depAcc = conn.prepareCall("CALL finish_transfer(?,?);");

			System.out.println("\n--Please Re-Enter your  User ID--");
			uDoa.setUserID(input.nextLine());
			
			System.out.println("\n--Enter the User ID of account that has sent the funds-");
			uDoa.setSenderID(input.nextLine());
			
			depAcc.setString(2, uDoa.getUserID());
			depAcc.setString(1, uDoa.getSenderID());
			depAcc.execute();
			
			System.out.println("\n--Your transfer has been Accepted!--");
			System.out.println("\n--Check your balance to view your Funds!--");

		}catch(SQLException e) {
			System.out.println("..There seems to be an error on our end, please contact support!..");
			log.error("error with connecting to database, check SQL");
			e.printStackTrace();
		}
			System.out.println("\n--Enter '0' to return to the main menu--");
			uDoa.setIntResponse(input.nextInt());
			if(uDoa.getIntResponse() == 0) prom.customerOptionsMenu();
	}
	public void applyAccount() {
		//INSERT SQL WITH MESSSAGE WITH BALANCE
		
		log.debug("customer apply account..");
		prom = new Prompt();
		
		try {
			 Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
			log.debug("Connectecd to database");
			CallableStatement appAcc = conn.prepareCall("CALL apply_account_balance(?,?);");
			System.out.println("\n--Please Re-Enter your User ID--");
			uDoa.setUserID(input.nextLine());
			
			if(accEligible(uDoa.getUserID())) {
			
			System.out.println("\n--Enter the starting balance for your new Account--");
			uDoa.setAmount(input.nextInt());
			appAcc.setInt(1, uDoa.getAmount());
			appAcc.setString(2, uDoa.getUserID());

			appAcc.execute();
			
			System.out.println("\n--Your new account has been initialized!--");
		   }
		/*else { 
		System.out.println("--Your User ID has yet to be verified, Support has been notified!--");
		log.warn("userID not approved");
		}*/
		}catch(SQLException e) {
			System.out.println("..There seems to be an error on our end, please contact support!..");
			log.error("error with connecting to database, check SQL");
			e.printStackTrace();
		}
		
		 System.out.println("\n--Enter '0' to return to the main menu--");
		 uDoa.setIntResponse(input.nextInt()); 
		 if(uDoa.getIntResponse() == 0) prom.customerOptionsMenu(); //else prom.customerOptionsMenu();
	
	}
	public void pendingNotification() {
		try {
			 Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
			log.debug("Connectecd to database");
			PreparedStatement pendNotif = conn.prepareStatement("select has_pending from accounts where user_id = ?;");
			pendNotif.setString(1, uDoa.getUserID());
			ResultSet rs = pendNotif.executeQuery();
			while(rs.next()) 
			if(rs.getBoolean("has_pending")  == true)	{
				System.out.println("--------------------------------------");
				System.out.println("\n--You have a pending transaction!--\n");
				System.out.println("--------------------------------------");
			}
		}catch(SQLException e) {
			System.out.println("..There seems to be an error on our end, please contact support!..");
			log.error("error with connecting to database, check SQL");
			e.printStackTrace();
		}
			
	}
	
	
	public boolean validAmount(int amount) {
		if(amount > 0) {
			log.debug("input is valid, > 0");
			uDoa.setApproved(true);
		}
		else {
			log.error("input is not valid, <= 0");
			uDoa.setApproved(false);
		}
		return uDoa.isApproved();
	}		
	
	public boolean accEligible(String id) {
	
	try { 
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
		log.debug("Connectecd to database");
		PreparedStatement accElig = conn.prepareStatement("SELECT is_approved FROM users WHERE user_id = ?;");	
		accElig.setString(1, id);
		ResultSet rs = accElig.executeQuery();
		while(rs.next()) {
			if(rs.getBoolean("is_approved")) {
				log.debug("user is eligble to make transactions to an account");
				uDoa.setApproved(true);
		}
			else {
				uDoa.setApproved(false);
				System.out.println("--Your User ID has not been verified--");
				System.out.println("--Our support Team has been notified--");
				prom.customerOptionsMenu();
			}
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
			log.error("database cannot be reached, problem in iuser id validation");
		}
	
	return uDoa.isApproved();

		
	}
}


