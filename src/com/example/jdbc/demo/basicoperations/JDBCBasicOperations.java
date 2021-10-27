package com.example.jdbc.demo.basicoperations;

import java.sql.*;

import com.example.jdbc.demo.ConnectionProperties;


// Some examples of basic database operations: SELECT, INSERT, UPDATE and DELETE. 
public class JDBCBasicOperations extends ConnectionProperties {

	static Connection conn = null;
	static Statement sqlSta = null;
	static ResultSet resSet = null;

	public static void BasicOperations() throws SQLException {
		System.out.println("Displaying all employees...");
		selectAll();

		System.out.println("Inserting a new employee (Nelson) on the table and displaying all employees again...");
		InsertData();

		System.out.println("Updating Wesley's email and displaying all employees again...");
		UpdateData();

		System.out.println("Deleting Nelson from the table and displaying all employees again...");
		DeleteData();

	}

	public static void selectAll() throws SQLException {

		try {

			// Establishing a connection between database and Java application
			conn = DriverManager.getConnection(dbUrl, user, password);

			// Creating statement
			sqlSta = conn.createStatement();

			// Executing statement
			resSet = sqlSta.executeQuery("SELECT * FROM employee");

			// Processing the result and displaying it on the screen
			while (resSet.next()) {
				System.out.println(resSet.getString("last_name") + ", " + resSet.getString("first_name"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Inserting data
	public static void InsertData() throws SQLException {

		try {

			// Establishing a connection between database and Java application
			conn = DriverManager.getConnection(dbUrl, user, password);

			// Creating statement
			sqlSta = conn.createStatement();

			// Executing statement
			int rowsAffected = sqlSta.executeUpdate(
					"INSERT INTO employee (first_name, last_name, email, department, salary)" +
							"VALUES ('Nelson', 'Widmer', 'nelsonwidmer81@outlook.com', 'Informarion Technology', 5300.0);"
					);

			// Processing the result and displaying it on the screen
			while (resSet.next()) {
				System.out.println(resSet.getString("last_name") + ", " + resSet.getString("first_name"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Updating data
	public static void UpdateData() throws SQLException {

		try {

			// Establishing a connection between database and Java application
			conn = DriverManager.getConnection(dbUrl, user, password);

			// Creating statement
			sqlSta = conn.createStatement();

			// Executing statement
			int rowsAffected = sqlSta.executeUpdate(
					"UPDATE employee SET email ='wesleyboetus@hotmail.com' WHERE first_name='Wesley' AND last_name='Boetus'"
					);

			// Processing the result and displaying it on the screen
			while (resSet.next()) {
				System.out.println(resSet.getString("last_name") + ", " + resSet.getString("first_name"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Deleting data
	public static void DeleteData() throws SQLException {

		try {

			// Establishing a connection between database and Java application
			conn = DriverManager.getConnection(dbUrl, user, password);

			// Creating statement
			sqlSta = conn.createStatement();

			// Executing statement
			int rowsAffected = sqlSta.executeUpdate(
					"DELETE FROM employee WHERE first_name= 'Nelson' AND last_name= 'Widmer'"
					);

			// Processing the result and displaying it on the screen
			while (resSet.next()) {
				System.out.println(resSet.getString("last_name") + ", " + resSet.getString("first_name"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}