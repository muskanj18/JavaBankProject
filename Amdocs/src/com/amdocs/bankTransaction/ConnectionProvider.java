package com.amdocs.bankTransaction;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	// Database connection details
		private static final String URL = "jdbc:mysql://localhost:3306/BankTransaction";
		private static final String USERNAME = "root";
		private static final String PASSWORD = "Muskan@123";
		
		// Singleton pattern: Ensure only one connection instance is created
		private static Connection con = null;
		
		// Get a database connection
		public static Connection getConnection()
		{
			// If no connection exists, create a new one
			if(con==null)
			{
				try 
				{
					// Load the MySQL JDBC driver
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					// Establish a new connection
					con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
					
				} 
				catch (Exception e) 
				{
					// Print the stack trace if an exception occurs during connection creation
					e.printStackTrace();
				}
				
			}
			
			// Return the established connection (either new or existing)
			return con;
		}
		
		// Close the database connection
		public static void closeConnection()
		{
			// If a connection exists, close it
			if(con!=null)
			{
				try 
				{
					// Close the connection
					con.close();
				} 
				catch (Exception e) 
				{
					// Print the stack trace if an exception occurs during connection closure
					e.printStackTrace();
				} 
				finally 
				{
					// Set the connection instance to null to indicate that it has been closed
					con = null;
				}
				}
}
		}
