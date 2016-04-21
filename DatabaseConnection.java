package com.temple.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.temple.lib.Patron;

public class DatabaseConnection {

	private static Connection connect = null;
	public static Statement statement = null;
	private static ResultSet resultSet = null;
	public static PreparedStatement preparedStatement = null;

	public static Connection preparedDatabase() throws Exception {

		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?", "root", "sesame"); // change
																											// username
																											// passowrd
			return connect;
		} catch (Exception e) {
			throw e;
		}
	}

	public static Statement readDatabase() throws Exception {
		try {
			connect = preparedDatabase();
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			System.out.println("Connected.");

			return statement;
		} catch (Exception e) {
			throw e;
		}
	}

	public static ArrayList<Patron> checkInfo(String userInput, String query) throws Exception {
		ArrayList<Patron> listOfMatches = new ArrayList<Patron>();
		Statement statement = readDatabase();
		try {
			String firstName = null, lastName = null, number;
			if (query != "") {
				resultSet = statement.executeQuery("Select * from patron where " + query + " = " + "'" + userInput + "';");
			} else {
				if (userInput.contains(" ")) {
					firstName = userInput.substring(0, userInput.indexOf(" "));
					lastName = userInput.substring(userInput.indexOf(" "), userInput.length());
				} else {
					firstName = userInput;
					lastName = userInput;
				}
				listOfMatches.addAll(checkInfo(firstName, "FirstName"));
				listOfMatches.addAll(checkInfo(lastName, "LastName"));
			}
			while (resultSet.next()) {
				firstName = resultSet.getString("FirstName");
				lastName = resultSet.getString("LastName");
				number = resultSet.getString("PhoneNumber");
				Patron patron = new Patron(firstName, lastName, number);
				listOfMatches.add(patron);
			}
			return listOfMatches;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean insertInfo(String firstName, String lastName, String phoneNumber, int isMember,
			Connection connect) {
		String preparedQuery = "INSERT INTO patron (FirstName, LastName, PhoneNumber, Member)" + "Values(?, ?, ?, ?)";

		boolean isComplete = false;
		try {

			preparedStatement = connect.prepareStatement(preparedQuery);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, phoneNumber);
			preparedStatement.setInt(4, isMember);
			preparedStatement.executeUpdate();
			isComplete = true;
			return isComplete;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return isComplete;
	}

}
