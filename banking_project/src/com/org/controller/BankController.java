package com.org.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.org.model.bank;
import com.org.service.service;
import com.org.service.serviceimplementation;
public class BankController {
	        private static Scanner sn = new Scanner (System.in);
            public static void main(String args[])
            {
            	bank n = new bank();
            	service sv = new serviceimplementation();
            	try {
            	n.db_connection();
            	
            	System.out.println("connection successfull.....");
            	while(true)
            	{
                  	System.out.println("1.Create Account");
                	System.out.println("2.View Account");
                	System.out.println("3. Update Account info");
                	System.out.println("4.deposit amount");
                	System.out.println("5.withdraw amount");
                	System.out.println("6.transaction amount");
                	System.out.println("7.view transaction");
                	System.out.println("8.Exit");
                	int input = sn.nextInt();
                	switch (input)
                	{
                	case 1:
                	{
                		sv.createaccount();
                		//System.out.println("1");
                		break;
                	}
                	case 2:
                	{
                		sv.viewaccount();
                		//System.out.println("2");
                		break;
                	}
                	case 3 :
                	{
                		sv.updateaccount();
                		//System.out.println("3");
                		break;
                	}
                	case 4:
                	{
                		sv.deposit();
                		//System.out.println("4");
                		break;
                	}
                	case 5 :
                	{
                		sv.withdraw();
                		//System.out.println("5");
                		break;
                	}
                	case 6 :
                	{
                		sv.amount_trans();
                		//System.out.println("6");
                		break;
                	}
                	case 7 :
                	{
                		sv.view_trans();
                		//System.out.println("7");
                		break;
                	}
                	case 8 :
                	{
                		System.out.println("Terminating....");
                		break;
                	}
                	default :
                	{
                	    System.out.println("Enter valid operation");
                		break;
                	}
                		
                	}
            	}
            	}
            catch(SQLException e)
            {
            	e.printStackTrace();
            }
            }
}
