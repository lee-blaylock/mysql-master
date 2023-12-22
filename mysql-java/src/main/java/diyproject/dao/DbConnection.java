package diyproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import recipes.exception.DbException;

public class DbConnection {

		private static String HOST = "Blaylockshp";
		private static String PASSWORD = "projects";
		private static int PORT = 3306;
		private static String SCHEMA = "projects";
		private static String USER = "projects";
		
	

	public static Connection getConnection()	{
		String uri = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false", HOST, 
				PORT, SCHEMA, USER, PASSWORD);
		System.out.println("Connecting with uri = " + uri);
		try {
			Connection conn = DriverManager.getConnection(uri);
			System.out.println("Successfully obtained connection to schema " + SCHEMA
					+ ".");
			return conn;
		} catch (SQLException e)	{
			System.out.println("Error getting connection to " + SCHEMA);
			throw new DbException(e);
		}
}
	}
