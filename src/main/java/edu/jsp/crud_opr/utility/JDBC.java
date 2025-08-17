package edu.jsp.crud_opr.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC {
//	static Class<?> loadPgDriver = null;
	private static String url = "jdbc:postgresql://localhost:5432/jdbc";
	static Connection connection = null;
	public static PreparedStatement preparedStatementInsertCandidate = null;
	public static Statement statement = null;
	public static PreparedStatement preparedStatementToReadCandidateById = null;
	public static PreparedStatement preparedStatementUpdateCandidateName = null, preparedStatementUpdateCandidateFee = null;
	public static PreparedStatement preparedStatementDeleteCandidateById = null;
	
	public static JDBC jdbcUtilityInit() {
		try {
			// Step 1: Register driver
			Class.forName("org.postgresql.Driver");
			System.out.println("Database connected");
			// Step 2: Establish Connection
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection(url, properties);

			// Step 3:Create Statement to insert data
			preparedStatementInsertCandidate = connection.prepareStatement("INSERT INTO candidate VALUES(?,?,?);");

			// Step 3: Create statement to fetch data by id
			preparedStatementToReadCandidateById = connection.prepareStatement("SELECT * FROM candidate WHERE id = ?;");
			
			preparedStatementUpdateCandidateName = connection.prepareStatement("update candidate set name = ? where id = ?");
			preparedStatementUpdateCandidateFee = connection.prepareStatement("update candidate set fee = ? where id = ?");
			
			preparedStatementDeleteCandidateById = connection.prepareStatement("delete from candidate where id = ?");
			
			statement = connection.createStatement();

		} catch (IOException | SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
