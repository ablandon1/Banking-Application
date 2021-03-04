# Banking-Application
# Version 2.0

The Banking API will manage the bank accounts of its users. It will be managed by the Bank's employees and admins. 
Employees and Admins count as Standard users with additional abilities. All users must be able to update their personal information, such as username, password, 
first and last names, as well as email. Accounts owned by users must support withdrawal, deposit, and transfer. 
Transfer of funds should be allowed between accounts owned by the same user, as well as between accounts owned by different users. Standard users should be able to register
and login to see their account information. They can have either Checking or Savings accounts. Employees can view all customer information, but not modify in any way.
Admins can both view all user information, as well as directly modify it.

1. Functionality should reflect the below user stories.
2. Data is stored in a database.
3. A custom stored procedure is called to perform some portion of the functionality.
4. Data Access is performed through the use of JDBC in a data layer consisting of Data Access Objects.
5. All input is received using the java.util.Scanner class.
6. Log4j is implemented to log events to a file.
7. JUnit test is written to test some functionality.


User Stories

* As a user, I can login.
* As a customer, I can apply for a new bank account with a starting balance.
* As a customer, I can view the balance of a specific account.
* As a customer, I can make a withdrawal or deposit to a specific account.
* As the system, I reject invalid transactions.
	* Ex:
		* A withdrawal that would result in a negative balance.
		* A deposit or withdrawal of negative money.
* As an employee, I can approve or reject an account.
* As an employee, I can view a customer's bank accounts.
* As a user, I can register for a customer account.
* As a customer, I can post a money transfer to another account.
* As a customer, I can accept a money transfer from another account.
* A an employee, I can view a log of all transactions.

TECHNOLOGY USED:
JDBC, Java, PostgreSQL, HTML/CSS, REST Services, Apache Tomcat


TO-DO LIST:
. Make UI more dynamic
. Add more styling to UI

Getting Started
1. To clone project type : git clone Application url from an initialize git repository.
2. When your Tomcat server is running visit : http://localhost:8080/T3Bank/ to run the Application
4. From here application is functional when a new account id and password is initialized and approved by database administrator
6. Sign after credentials are approved and apply for accounts using prompts.
