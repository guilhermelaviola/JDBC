package com.example.jdbc.demo.blob;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.jdbc.demo.ConnectionProperties;

// A binary large object (BLOB) is a collection of binary data stored as a single entity.
// BLOBs are typically images, audio or other multimedia objects, though sometimes binary executable code is stored as a BLOB.
public class WritingBLOB extends ConnectionProperties {

	static Connection conn = null;
	static PreparedStatement sqlSta = null;

	static FileInputStream input = null;

	public static void WritingBlob() throws Exception {

		try {
			// Establishing a connection between database and Java application
			conn = DriverManager.getConnection(dbUrl, user, password);

			// Preparing statement
			String sql = "UPDATE employee SET resume=? WHERE email='christophermorgan@yahoo.com'";
			sqlSta = conn.prepareStatement(sql);

			// Setting parameter for resume file name
			File theFile = new File("C:\\Users\\guilh\\Desktop\\Christopher_Morgan_Resume.pdf");
			input = new FileInputStream(theFile);
			sqlSta.setBinaryStream(1, input);

			System.out.println("Reading input file: " + theFile.getAbsolutePath());

			// Executing statement
			System.out.println("\nStoring resume in database: " + theFile);
			System.out.println(sql);

			sqlSta.executeUpdate();

			System.out.println("\nCompleted successfully!");

		} catch (Exception exc) {
			exc.printStackTrace();

		} finally {			
			if (input != null) {
				input.close();
			}

			close(conn, sqlSta);			
		}
	}

	private static void close(Connection conn, Statement sqlSta) throws SQLException {

		if (sqlSta != null) {
			sqlSta.close();
		}

		if (conn != null) {
			conn.close();
		}
	}
}
