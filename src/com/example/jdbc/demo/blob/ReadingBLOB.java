package com.example.jdbc.demo.blob;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.jdbc.demo.ConnectionProperties;

// A binary large object (BLOB) is a collection of binary data stored as a single entity.
// BLOBs are typically images, audio or other multimedia objects, though sometimes binary executable code is stored as a BLOB.
public class ReadingBLOB extends ConnectionProperties {

	static Connection conn = null;
	static Statement sqlSta = null;
	static ResultSet res = null;

	static InputStream input = null;
	static FileOutputStream output = null;

	public static void ReadingBlob() throws Exception, IOException {		

		try {
			// Establishing a connection between database and Java application
			conn = DriverManager.getConnection(dbUrl, user, password);

			// Executing statement
			sqlSta = conn.createStatement();
			String sql = "SELECT resume FROM employee WHERE email='christophermorgan@yahoo.com'";
			res = sqlSta.executeQuery(sql);

			// Setting up a handle to the file
			File theFile = new File("C:\\Users\\guilh\\Desktop\\Christopher_Morgan_Resume.pdf");
			output = new FileOutputStream(theFile);

			if (res.next()) {
				input = res.getBinaryStream("resume"); 
				System.out.println("Reading resume from database...");
				System.out.println(sql);

				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}

				System.out.println("\nSaved to file: " + theFile.getAbsolutePath());

				System.out.println("\nCompleted successfully!");				
			}

		} catch (Exception exc) {
			exc.printStackTrace();
			
		} finally {
			if (input != null) {
				input.close();
			}
			if (output != null) {
				output.close();
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
