package com.example.jdbc.demo.storedprocedures;

import java.sql.*;

import com.example.jdbc.demo.ConnectionProperties;

// An input/output parameter is a parameter that functions as an IN or an OUT parameter or both.
// The value of the IN/OUT parameter is passed into the stored procedure/function and a new value can be assigned
// to the parameter and passed out of the module. An IN/OUT parameter must be a variable, not a constant.
public class InParameters extends ConnectionProperties {

	static Connection conn = null;
	static CallableStatement sqlSta = null;

	// IncreaseSalariesForDepartment() method
	public static void IncreaseSalariesForDepartment() throws Exception {

		try {
			// Establishing a connection between database and Java application
			conn = DriverManager.getConnection(dbUrl, user, password);

			String theDepartment = "Information Technology";
			int theIncreasedAmount = 900;

			// Showing salaries before the raise
			System.out.println("Salaries before:");
			showSalaries(conn, theDepartment);

			// Preparing the stored procedure call
			sqlSta = conn.prepareCall("{call increase_salaries_for_department(?, ?)}");

			// Setting the parameters
			sqlSta.setString(1, theDepartment);
			sqlSta.setDouble(2, theIncreasedAmount);

			// Calling the procedure
			System.out.println("Calling stored procedure increase_salaries_for_department: " + theDepartment);
			sqlSta.execute();
			System.out.println("Finished calling stored procedure.");

			// Showing salaries after the raise
			System.out.println("Salaries after:");
			showSalaries(conn, theDepartment);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, sqlSta, null);
		}
	}

	// Displaying salaries
	private static void showSalaries(Connection conn, String theDepartment) throws SQLException {
		PreparedStatement sqlSta = null;
		ResultSet res = null;

		try {
			// Preparing statement
			sqlSta = conn.prepareStatement("SELECT * FROM employee WHERE department=?");

			sqlSta.setString(1, theDepartment);

			// Executing SQL query
			res = sqlSta.executeQuery();

			// Processing result set
			while (res.next()) {
				String lastName = res.getString("last_name");
				String firstName = res.getString("first_name");
				double salary = res.getDouble("salary");
				String department = res.getString("department");

				System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(sqlSta, res);
		}
	}

	// Close() methods
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

	private static void close(Statement sqlsta, ResultSet res)
			throws SQLException {

		close(null, sqlsta, res);
	}
}
