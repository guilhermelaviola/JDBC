package com.example.jdbc.demo.transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.example.jdbc.demo.ConnectionProperties;

// A transaction in MySQL is a sequential group of statements, queries, or operations such as select, insert, update
// or delete to perform as a one single work unit that can be committed or rolled back.
public class BasicTransaction extends ConnectionProperties {
	
	static Connection conn = null;
	static Statement sqlSta = null;

	// IncreaseSalariesForDepartment() method
	public static void DeleteAndUpdateTransaction() throws Exception {

		try {
			// Establishing a connection between database and Java application
			conn = DriverManager.getConnection(dbUrl, user, password);

			// Deactivating auto-commit on MySQL
			conn.setAutoCommit(false);

			// Showing salaries before the raise
			System.out.println("Salaries before:");
			showSalaries(conn, "Human Resources");
			showSalaries(conn, "Information Technology");
			showSalaries(conn, "Engineering");

			// Transaction (part 1): deleting all human resources employees from the table
			sqlSta = conn.createStatement();
			sqlSta.executeUpdate("DELETE FROM employee WHERE department = 'Engineering'");
			
			// Transaction (part 2): setting all human resources salaries to 4000
			sqlSta.executeUpdate("UPDATE employee SET salary = 4000.00 WHERE department = 'Human Resources'");

			// Invoking askUsersIfOkToSave() method
			
			boolean ok = askUsersIfOkToSave();
			
			if (ok) {
				// Storing changes on database
				conn.commit();
				System.out.println("Changes saved to database!");
			} else {
				// Rolling back
				conn.rollback();
				System.out.println("Changes NOT saved to database!");
			}

			// Showing salaries after the raise
			System.out.println("Salaries after:");
			showSalaries(conn, "Human Resources");
			showSalaries(conn, "Information Technology");
			showSalaries(conn, "Engineering");

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, sqlSta, null);
		}
	}
	
	// Asking User if it's okay to save
	private static boolean askUsersIfOkToSave() {
		Scanner s = new Scanner(System.in);

		System.out.println("Save changes on database? YES or NO? ");
		String input = s.nextLine();

		s.close();

		return input.equalsIgnoreCase("YES");
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
