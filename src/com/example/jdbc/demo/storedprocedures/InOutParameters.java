package com.example.jdbc.demo.storedprocedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.example.jdbc.demo.ConnectionProperties;

// An input/output parameter is a parameter that functions as an IN or an OUT parameter or both.
// The value of the IN/OUT parameter is passed into the stored procedure/function and a new value can be assigned
// to the parameter and passed out of the module. An IN/OUT parameter must be a variable, not a constant.
public class InOutParameters extends ConnectionProperties {

	static Connection conn = null;
	static CallableStatement sqlSta = null;

	// GreetDepartment() method
	public static void GreetDepartment() throws Exception {

		try {
			// Establishing a connection between database and Java application
			conn = DriverManager.getConnection(dbUrl, user, password);

			String theDepartment = "Engineering";

			// Preparing the stored procedure call
			sqlSta = conn.prepareCall("{call greet_the_department(?)}");

			// Setting the parameters
			sqlSta.registerOutParameter(1, Types.VARCHAR);
			sqlSta.setString(1, theDepartment);

			// Calling the procedure
			System.out.println("Calling stored procedure greet_the_department: " + theDepartment);
			sqlSta.execute();
			System.out.println("Finished calling stored procedure.");

			// Creating variable to store the IN/OUT parameter
			String result = sqlSta.getString(1);

			// Displaying result
			System.out.println("Result: " + result);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, sqlSta, null);
		}
	}
	
	// Close() method
	private static void close(Connection conn, Statement sqlSta,
			ResultSet res) throws SQLException {
		if (res != null) {
			res.close();
		}

		if (sqlSta != null) {
			sqlSta.close();
		}

		if (conn != null) {
			conn.close();
		}
	}
}
