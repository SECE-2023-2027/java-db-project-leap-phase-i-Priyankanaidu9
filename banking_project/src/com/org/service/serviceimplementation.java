package com.org.service;

import java.io.BufferedWriter;
import java.sql.Timestamp;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.org.model.bank;

public class serviceimplementation implements service {
	private static Scanner sn = new Scanner (System.in);

	@Override
	public void createaccount() {
		try {
		// TODO Auto-generated method stub
		Connection conn = bank.db_connection();
		System.out.println("Enter customer id:");
		int c_id = sn.nextInt();
		System.out.println("Enter account type (1.Savings account /n 2. current account");
		int id = sn.nextInt();
		String type;
		if (id == 1)
		{
			type = "savings";
		}
		else
		{
			type = "current";
		}
		System.out.println("Enter your amount");
		double balance = sn.nextDouble();
		
		System.out.println("Enter your address");
		String address = sn.next();
		
		System.out.println("Enter your mobile: ");
		String mobile_no = sn.next();
		
		String sql = "insert into account (customer_id ,account_type , balance , address , mobile ) values (? ,? ,?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, c_id);
		pstmt.setString(2, type);
		pstmt.setDouble(3, balance);
		pstmt.setString(4, address);
		pstmt.setString(5, mobile_no);
		pstmt.executeUpdate();
		String query  = "select account_id from account where customer_id = ?  and account_type = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1 ,c_id);
		pstmt.setString(2, type);
		ResultSet rs = pstmt.executeQuery();
		int accountID = rs.next()? rs.getInt("account_id"): 0;
		if(type.equals("savings")) {
		    String query2 = "Insert into savings_Account (account_id, interest_rate) values (?, ?)";
		    pstmt = conn.prepareStatement(query2);
		    pstmt.setInt(1, accountID);
		    pstmt.setDouble(2, 1.05);
		    pstmt.executeUpdate(); 
		}else {
			String query2 = "INSERT INTO current_account (account_id, overdraft_limit) values (?, ?)";
			pstmt = conn.prepareStatement(query2);
			pstmt.setInt(1,  accountID);
			pstmt.setDouble(2,1000.0 );
			pstmt.executeUpdate();
		}
 
		
		System.out.println("inserted successfully");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void viewaccount() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Enter account id :");
			int a_id = sn.nextInt();
			Connection conn = bank.db_connection();
			String sql = "select * from account where account_id =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,a_id);
			ResultSet  rs = pstmt.executeQuery();
			if(rs.next())
			{
				System.out.println("Account id"+rs.getInt("account_id"));
				System.out.println("Customer id "+ rs.getInt("customer_id"));
				System.out.println("Balance "+ rs.getDouble("balance"));
				System.out.println("account_type:" + rs.getString("account_type"));
			}
				System.out.println();
			}
			
			
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateaccount() {
		// TODO Auto-generated method stub
		try {
		System.out.println("Enter account id :");
		int a_id = sn.nextInt();
		System.out.println("enter address:");
		String address = sn.next();
		System.out.println("enter mobile number");
		String mobile = sn.next();
		Connection  conn  = bank.db_connection();
		String sql = "update account set address = ? , mobile =? where account_id =?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, address);
		pstmt.setString(2,  mobile);
		pstmt.setInt(3 ,  a_id);
		pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void withdraw() {
		// TODO Auto-generated method stub
		try {
			Connection conn = bank.db_connection();
			System.out.println("Enter account id:");
			int a_id = sn.nextInt();
			System.out.println("Enter withdrawal amount:");
			double amount = sn.nextDouble();
			
			String query = "SELECT * FROM account WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, a_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String account = rs.getString("account_type");
				if(!account.equals("current")) {
					System.out.println(account);
					double balance = rs.getDouble("balance");
					if( balance >= amount) {
						String sql = "INSERT INTO Transaction (account_id, transaction_type, amount) values (?, ?, ?)";
					    pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, a_id);
						pstmt.setString(2, "Withdraw");
						pstmt.setDouble(3,  amount);
						pstmt.executeUpdate();
						
						String query1 = "UPDATE account SET balance = balance - ? WHERE account_id = ?";
						pstmt = conn.prepareStatement(query1);
						pstmt.setDouble(1, amount);
						pstmt.setInt(2, a_id);		
						pstmt.executeUpdate();
					}
				
				}
				
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deposit() {
		// TODO Auto-generated method stub
		/*try {
			Connection conn = bank.db_connection();
			System.out.println("Enter account id :");
			int a_id = sn.nextInt();
			System.out.println("Enter  deposit amount:");
			double amount = sn.nextDouble();
			String sql = "inset into transaction (account_id , transaction_type, amount) values (? ,? ,?)";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a_id);
			pstmt.setString(2,  "Deposit");
			pstmt.setDouble(3,amount);
			pstmt.executeUpdate();
			String query = "update account set balance + ? where account_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setDouble(1, amount);
			pstmt.setInt(2 , accountID);
			pstmt.executeUpdate();
			
			System.out.println("Deposit successful!");
			
			
		}*/
		try {
		    Connection conn = bank.db_connection();
		    System.out.println("Enter account id :");
		    int a_id = sn.nextInt();
		    System.out.println("Enter deposit amount:");
		    double amount = sn.nextDouble();
		    
		   
		    String sql = "insert into transaction (account_id, transaction_type, amount) values (?, ?, ?)";
		    PreparedStatement pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, a_id);
		    pstmt.setString(2, "Deposit");
		    pstmt.setDouble(3, amount);
		    pstmt.executeUpdate();

		  
		    String query = "update account set balance = balance + ? where account_id = ?";
		    pstmt = conn.prepareStatement(query);
		    pstmt.setDouble(1, amount);
		    pstmt.setInt(2, a_id); 
		    pstmt.executeUpdate();

		    System.out.println("Deposit successful!");
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		
		
		
	}

	@Override
	public void amount_trans() {
	    Scanner sn = new Scanner(System.in);
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = bank.db_connection(); // Establish the database connection
	        conn.setAutoCommit(false); // Start a transaction

	        System.out.println("Enter source account id:");
	        int s_id = sn.nextInt();
	        System.out.println("Enter destination account id:");
	        int d_id = sn.nextInt();
	        System.out.println("Enter transfer amount:");
	        double amount = sn.nextDouble();

	        // Check source account balance
	        String query = "SELECT balance FROM account WHERE account_id = ?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, s_id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            double balance = rs.getDouble("balance");
	            if (balance >= amount) {
	                // Deduct from source account
	                String query1 = "UPDATE account SET balance = balance - ? WHERE account_id = ?";
	                pstmt = conn.prepareStatement(query1);
	                pstmt.setDouble(1, amount);
	                pstmt.setInt(2, s_id);
	                pstmt.executeUpdate();

	                // Add to destination account
	                String query2 = "UPDATE account SET balance = balance + ? WHERE account_id = ?";
	                pstmt = conn.prepareStatement(query2);
	                pstmt.setDouble(1, amount);
	                pstmt.setInt(2, d_id);
	                pstmt.executeUpdate();

	                // Log transaction for source account
	                String sqlSource = "INSERT INTO Transaction (account_id, transaction_type, amount) VALUES (?, ?, ?)";
	                pstmt = conn.prepareStatement(sqlSource);
	                pstmt.setInt(1, s_id);
	                pstmt.setString(2, "Transfer");
	                pstmt.setDouble(3, amount);
	                pstmt.executeUpdate();

	                // Log transaction for destination account
	                String sqlDestination = "INSERT INTO Transaction (account_id, transaction_type, amount) VALUES (?, ?, ?)";
	                pstmt = conn.prepareStatement(sqlDestination);
	                pstmt.setInt(1, d_id);
	                pstmt.setString(2, "Transfer");
	                pstmt.setDouble(3, amount);
	                pstmt.executeUpdate();

	                // Write to log file
	                try (FileWriter writer = new FileWriter("transaction_log.txt", true);
	                     BufferedWriter bw = new BufferedWriter(writer)) {
	                    String log = "Account ID: " + d_id + ", Type: Transfer, Amount: " + amount + ", Date: " + new Timestamp(System.currentTimeMillis());
	                    bw.write(log);
	                    bw.newLine();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }

	                // Commit the transaction
	                conn.commit();
	                System.out.println("Transfer successful");
	            } else {
	                System.out.println("Insufficient balance!");
	            }
	        } else {
	            System.out.println("Source account not found");
	        }
	    } catch (SQLException e) {
	        if (conn != null) {
	            try {
	                conn.rollback(); // Rollback transaction on error
	                System.out.println("Transaction failed, changes rolled back.");
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	        e.printStackTrace();
	    } finally {
	        // Close resources
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	@Override
	public void view_trans() {
		// TODO Auto-generated method stub
		try {
			Connection conn = bank.db_connection();
			System.out.println("Enter account id:");
			int a_id = sn.nextInt();
			String query = "SELECT * FROM transaction WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, a_id);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				System.out.println("Transaction ID: " + rs.getInt("transaction_id"));
				System.out.println("Account ID: " + rs.getInt("account_id"));
				System.out.println("Transaction Type: " + rs.getString("transaction_type"));
				System.out.println("Amount: " + rs.getDouble("amount"));
			
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	}
	


