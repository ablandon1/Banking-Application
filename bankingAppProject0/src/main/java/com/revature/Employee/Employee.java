package com.revature.Employee;

import com.revature.System.Driver;
import com.revature.System.EmployeeInt;
import com.revature.System.Prompt;
import com.revature.System.UserDOA;

import java.sql.*;
import java.util.Scanner;

import org.apache.log4j.Logger;



public class Employee implements EmployeeInt{
	
	private static final Logger log = Logger.getLogger(Employee.class.getName()); 
	Scanner input = new Scanner(System.in);
	Prompt prom;
	UserDOA uDoa = new UserDOA();

	
	public void approveUser() {
			log.debug("employee approve account..");
			//UPDATE SQL FIELD ISAPPROVED TO TRUE
			prom = new Prompt();
			try {
				 Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
				log.debug("Connectecd to database");
				CallableStatement updateUser = conn.prepareCall("CALL user_status_update(?,?);");
				System.out.println("\n--Enter the User ID you wish to validate--");
				
				String userId = input.nextLine();
				updateUser.setString(1, userId);
				
				System.out.println("\n--Enter the status of the NEW Account--");
				uDoa.setStrResponse(input.nextLine());
				if(uDoa.getStrResponse().equalsIgnoreCase("approve")) {
					uDoa.setApproved(true);
				}
				else if(uDoa.getStrResponse().equalsIgnoreCase("deny")) {
					uDoa.setApproved(false);
				}
				else {
					System.out.println("--Please enter a valid response--");
					uDoa.setStrResponse(input.nextLine());
				}
				
				updateUser.setBoolean(2, uDoa.isApproved());
				
				updateUser.execute();
				
				System.out.println("\n--The status of the new account has been updated!--");
			}
			catch(SQLException e) {
				System.out.println("..There seems to be an error on our end, please contact support!..");
				log.error("error with connecting to database, check SQL");
				e.printStackTrace();
			}
				System.out.println("\n--Enter '0' to return to the main menu--");
				uDoa.setIntResponse(input.nextInt());
				if(uDoa.getIntResponse() == 0) prom.employeeOptionsMenu();
			

	 }
	 public void viewCustomerAcc() {
			log.debug("employee view customer account..");
			//UPDATE SQL FIELD ISAPPROVED TO TRUE
			prom = new Prompt();
			try {
				 Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
				log.debug("Connectecd to database");
				PreparedStatement getAllAcc = conn.prepareStatement("SELECT * FROM accounts WHERE user_id = ?;");
				
				System.out.println("\n--Enter the User ID of the customer to view all accounts--");
				uDoa.setUserID(input.nextLine());
				getAllAcc.setString(1, uDoa.getUserID());
								
				ResultSet rs = getAllAcc.executeQuery();
				while(rs.next()) {
					System.out.print("\nAccount ID: " + rs.getInt("account_id") + " || ");
					System.out.print("Balance: $" +rs.getInt("balance") + " || ");
					System.out.print("Pending Transations: " +rs.getBoolean("has_pending") + " || ");
					System.out.print("User ID: " +rs.getString("user_id") + "\n");

				}
			}
			catch(SQLException e) {
				System.out.println("..There seems to be an error on our end, please contact support!..");
				log.error("error with connecting to database, check SQL");
				e.printStackTrace();
			}finally {
				System.out.println("\n--Enter '0' to return to the main menu--");
				uDoa.setIntResponse(input.nextInt());
				if(uDoa.getIntResponse() == 0) prom.employeeOptionsMenu();
			}

	 }
	 public void viewTransLog() {
			log.debug("employee view trans log..");
			//VIEW LOG OF TRANSACTIONS MADE FROM SPECIFIED USER ID
			prom = new Prompt();
			try {
				 Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/BankingApp", "postgres", "pgadmin");
				log.debug("Connectecd to database");
				PreparedStatement getAllAcc = conn.prepareStatement("SELECT * FROM transactions where account_id = ?;");
				
				System.out.println("\n--Enter the Account ID of the customer to view all related transactions--");
				uDoa.setAccountID(input.nextInt());
				getAllAcc.setInt(1, uDoa.getAccountID());
								
				ResultSet rs = getAllAcc.executeQuery();
				while(rs.next()) {
					//System.out.print("\nUser ID: " + uDoa.getUserID() + " || ");
					System.out.print("Sending Account ID: " +rs.getInt("account_id") + " || ");
					System.out.print("Recieving Account ID: " +rs.getInt("receiver_id") + " || ");
					System.out.print("Transaction ID: " +rs.getInt("transaction_id") + " || ");
					System.out.print("Amount: $" +rs.getInt("amount") + " || ");
					System.out.print("Pending Transactions: " +rs.getBoolean("is_pending") + " || \n");
				}
			}
			catch(SQLException e) {
				System.out.println("..There seems to be an error on our end, please contact support!..");
				log.error("error with connecting to database, check SQL");
				e.printStackTrace();
			}
				System.out.println("\n--Enter '0' to return to the main menu--");
				uDoa.setIntResponse(input.nextInt());
				if(uDoa.getIntResponse() == 0) prom.employeeOptionsMenu();
			
	 }
	 

}
