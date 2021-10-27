package com.example.jdbc.demo.basicoperations;

import java.sql.*;

import com.example.jdbc.demo.ConnectionProperties;

// A Java JDBC PreparedStatement is a special kind of Java JDBC Statement object with some useful additional features.
// Remember, you need a Statement in order to execute either a query or an update.
// The Java JDBC PreparedStatement primary features are: Easy to insert parameters into the SQL statement.
public class JDBCPreparedStatement extends ConnectionProperties {
	
	static Connection conn = null;
	static PreparedStatement sqlSta = null;
	static ResultSet resSet = null;

	public static void PreparedStatement() throws SQLException {

		// Establishing a connection between database and Java application
		conn = DriverManager.getConnection(dbUrl, user, password);
		
		// Preparing a statement
		sqlSta = conn.prepareStatement("SELECT * FROM employee WHERE salary > 4000 AND department = ?");

		// Setting the parameters
		sqlSta.setDouble(1, 4000);
		sqlSta.setString(1, "Information Technology");

		// executing query
		resSet = sqlSta.executeQuery();

		// Displaying the result
		System.out.println(resSet);
	}
}
