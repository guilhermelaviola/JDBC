package com.example.jdbc.demo.metadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.jdbc.demo.ConnectionProperties;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

// Metadata is “the data about the data.” Anything that describes the database—as opposed to being the contents
// of the database—is metadata. Thus column names, database names, user names, version names, and most of the
// string results from SHOW are metadata.
public class TableMetadata extends ConnectionProperties {
	
	static Connection conn = null;
	static Statement sqlSta = null;
	static ResultSet res = null;		

	public static void SchemaInfo() throws SQLException {

		try {
			// Establishing a connection between database and Java application
			conn = DriverManager.getConnection(dbUrl, user, password);

			// Running query
			sqlSta = conn.createStatement();
			res = sqlSta.executeQuery("SELECT id, last_name, first_name, salary FROM employee");
			
			// Getting result set metadata
			ResultSetMetaData rsMetaData = (ResultSetMetaData) res.getMetaData();
			
			// Displaying general info about tables
			int columnCount = rsMetaData.getColumnCount();
			System.out.println("Number of columns: " + columnCount + "\n");
			
			// Header for tables
			for(int column = 1; column <= columnCount; column++) {
				System.out.println("Column name: " + rsMetaData.getColumnName(column));
				System.out.println("Column type name: " + rsMetaData.getColumnTypeName(column));
				System.out.println("Is the column nullable(?): " + rsMetaData.isNullable(column));
				System.out.println("Is the column auto increment(?): " + rsMetaData.isAutoIncrement(column));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
	}

	// Close() method
	private static void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
}
