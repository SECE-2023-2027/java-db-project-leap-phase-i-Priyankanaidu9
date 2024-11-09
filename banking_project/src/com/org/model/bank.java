package com.org.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class bank {
          public static Connection db_connection()throws SQLException
          {
        	  return DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop","root","Priya@090506");
          }
}
