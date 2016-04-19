package com.temple.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnection {

	private Connection connect = null;
	public static Statement statement = null;
	private static ResultSet resultSet = null;

	public Statement readDatabase() throws Exception {
		try {
			// Setup the connection with the DB
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "username", "password"); //change username and passowrd 

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			return statement;
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<Patron> checkInfo(Statement statement, String userInput, int i) {

		ArrayList<Patron> listOfMatches = new ArrayList<Patron>();

		try {
			if (i == 1) {
				resultSet = statement.executeQuery("Select * from patron where PHONE_NUMBER is = '" + userInput + "'");
				String firstName, lastName, number;

				while (resultSet.next()) {
					firstName = resultSet.getString("FIRST_NAME");
					lastName = resultSet.getString("LAST_NAME");
					number = resultSet.getString("PHONE_NUMBER");

					Patron patron = new Patron(firstName, lastName, number);
					listOfMatches.add(patron);
				}

			} else {
				resultSet = statement.executeQuery("Select * from patron where LAST_NAME is = ' " + userInput + "'");
				String firstName, lastName, number;

				while (resultSet.next()) {
					firstName = resultSet.getString("FIRST_NAME");
					lastName = resultSet.getString("LAST_NAME");
					number = resultSet.getString("PHONE_NUMBER");

					Patron patron = new Patron(firstName, lastName, number);
					listOfMatches.add(patron);
				}
			}

			return listOfMatches;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
