package com.amdocs.bankTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Test {

	// SQL queries
	private static final String SELECT_ALL_TRANSACTIONS = "SELECT * FROM transactions";
	private static final String UPDATE_TRANSACTION = "UPDATE transactions SET NewBal=?, TransStat=? WHERE TransID=?";
	private static final String INSERT_VALID_TRANS = "INSERT INTO ValidTrans VALUES(?, ?, ?, ?)";
	private static final String INSERT_INVALID_TRANS = "INSERT INTO InValidTrans VALUES(?, ?, ?, ?)";

	
	public static void main(String[] args) 
	{

		try {
			// Process transactions and print success message
			processTransactions();
			System.out.println("Transaction completed!!!!");
			
			// Close the database connection
			ConnectionProvider.closeConnection();
		} 
		catch (Exception e) 
		{
			// Print the stack trace in case of an exception
			e.printStackTrace();
		} 

	}

	// Retrieve transactions and process each one
	private static void processTransactions() throws SQLException 
	{
		// Establish a database connection
		Connection con = ConnectionProvider.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_ALL_TRANSACTIONS);

		// Process each transaction
		while (rs.next()) 
		{
			processSingleTransaction(con, rs);
		}

	}

	// Process a single transaction
	private static void processSingleTransaction(Connection con, ResultSet rs) throws SQLException 
	{
		// Extract transaction details from the result set
		int transID = rs.getInt(1);
		int acctNo = rs.getInt(2);
		double oldBal = rs.getDouble(3);
		String transType = rs.getString(4);
		double transAmt = rs.getDouble(5);

		// Calculate new balance and determine transaction validity
		double newBal = calculateNewBalance(oldBal, transType, transAmt);

		String validity = "";

		if (newBal < 0) 
		{
			validity = "Invalid";
		} 
		else 
		{
			validity = "Valid";
		}

		// Update transaction details in the database
		updateTransaction(con, newBal, validity, transID);

		// Determine the target table based on transaction validity and insert the transaction
		String tableName = "";

		if (validity.equals("Valid")) 
		{
			tableName = "ValidTrans";
		} 
		else 
		{
			tableName = "InValidTrans";
		}

		insertIntoTransactionTable(con, tableName, transID, transType, transAmt, validity);

	}

	// Insert a transaction into the appropriate table
	private static void insertIntoTransactionTable(Connection con, String tableName, int transID, String transType,
			double transAmt, String validity) throws SQLException 
	{

		// Determine the appropriate SQL query for insertion
		String insertQuery = "";
		if (tableName.equals("ValidTrans")) 
		{
			insertQuery = INSERT_VALID_TRANS;
		} 
		else 
		{
			insertQuery = INSERT_INVALID_TRANS;
		}

		// Prepare and execute the insertion statement
		PreparedStatement ptsmt = con.prepareStatement(insertQuery);

		ptsmt.setInt(1, transID);
		ptsmt.setString(2, transType);
		ptsmt.setDouble(3, transAmt);
		ptsmt.setString(4, validity);
		ptsmt.executeUpdate();

	}

	// Update transaction details in the database
	private static void updateTransaction(Connection con, double newBal, String validity, int transID)
			throws SQLException 
	{
		
		// Prepare and execute the update statement
		PreparedStatement update = con.prepareStatement(UPDATE_TRANSACTION);

		update.setDouble(1, newBal);
		update.setString(2, validity);
		update.setInt(3, transID);

		update.executeUpdate();

	}

	// Calculate new balance based on transaction type
	private static double calculateNewBalance(double oldBal, String transType, double transAmt) 
	{
		
		// Determine the new balance based on the transaction type
		if (transType.equals("W")) 
		{
			return oldBal - transAmt;	
		} 
		else 
		{
			return oldBal + transAmt;
		}

	}

}

