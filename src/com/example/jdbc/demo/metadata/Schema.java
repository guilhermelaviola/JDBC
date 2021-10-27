package com.example.jdbc.demo.metadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.jdbc.demo.ConnectionProperties;
import com.mysql.cj.jdbc.DatabaseMetaData;

// Metadata is “the data about the data.” Anything that describes the database—as opposed to being the contents
// of the database—is metadata. Thus column names, database names, user names, version names, and most of the
// string results from SHOW are metadata.
public class Schema extends ConnectionProperties {

	static String catalog = null;
	static String schemaPattern = null;
	static String tableNamePattern = null;
	static String columnNamePattern = null;
	static String[] types = null;

	static Connection conn = null;
	static ResultSet res = null;		

	public static void SchemaInfo() throws SQLException {

		try {
			// Establishing a connection between database and Java application
			conn = DriverManager.getConnection(dbUrl, user, password);

			// Getting metadata
			DatabaseMetaData databaseMetaData = (DatabaseMetaData) conn.getMetaData();

			// Header for tables
			System.out.println("List of tables:");
			System.out.println("---------------");

			// Getting number of tables
			res = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);

			while (res.next()) {
				System.out.println(res.getString("TABLE_NAME"));
			}

			// Getting number of tables
			res = databaseMetaData.getColumns(catalog, schemaPattern, "employee", columnNamePattern);

			while (res.next()) {
				System.out.println(res.getString("COLUMN_NAME"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn, res);
		}
	}

	// Close() method
	private static void close(Connection conn, ResultSet res) throws SQLException {
		if (res != null) {
			res.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}
