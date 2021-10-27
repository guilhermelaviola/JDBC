package com.example.jdbc.demo.test;

import com.example.jdbc.demo.basicoperations.JDBCBasicOperations;
import com.example.jdbc.demo.basicoperations.JDBCPreparedStatement;
import com.example.jdbc.demo.blob.ReadingBLOB;
import com.example.jdbc.demo.blob.WritingBLOB;
import com.example.jdbc.demo.metadata.Schema;
import com.example.jdbc.demo.metadata.TableMetadata;
import com.example.jdbc.demo.storedprocedures.*;
import com.example.jdbc.demo.transactions.BasicTransaction;

public class Test {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("JDBC BASIC OPERATIONS");
		System.out.println("Insert, Update and Delete!");
		JDBCBasicOperations.BasicOperations();
		
		System.out.println("");
		
		System.out.println("Prepared Statement:");
		JDBCPreparedStatement.PreparedStatement();
		
		System.out.println("");
		
		System.out.println("STORED PROCEDURES");
		System.out.println("Incresing salaries for department!");
		InParameters.IncreaseSalariesForDepartment();
		
		System.out.println("");
		
		System.out.println("Greeting department!");
		InOutParameters.GreetDepartment();
		
		System.out.println("");
		
		System.out.println("Getting the number of departments!");
		OutParameters.GetCountForDepartment();
		
		System.out.println("");
		
		System.out.println("TRANSACTIONS");
		System.out.println("Metadata!");
		Schema.SchemaInfo();
		
		System.out.println("");
		
		TableMetadata.SchemaInfo();
		
		System.out.println("");
		
		System.out.println("Insert/Update Transaction!");
		BasicTransaction.DeleteAndUpdateTransaction();
		
		System.out.println("BLOB");
		
		System.out.println("Writing BLOB!");
		WritingBLOB.WritingBlob();
		
		System.out.println("Reading BLOB!");
		ReadingBLOB.ReadingBlob();
		
	}
}
