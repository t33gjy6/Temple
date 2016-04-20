package com.temple.lib;

import java.sql.Statement;
import java.util.ArrayList;

public class Temple {

	static DatabaseConnection sql = new DatabaseConnection();

	public static void main(String[] args) throws Exception {

		Statement statement = sql.readDatabase();

		String in = "3136005177";

	}

	public static void checkPhone(String in, Statement statement) {
		int i = 1;
		ArrayList<Patron> check = sql.checkInfo(statement, in, i);

	}

	public static void checkName(String in, Statement statement) {
		String firstName = null, lastName = null;

		int i = 2;
		if (in.contains(" ")) {
			firstName = in.substring(0, in.indexOf(" "));
			lastName = in.substring(in.indexOf(" "), in.length());
		}

		ArrayList<Patron> check = sql.checkInfo(statement, lastName, i);

		for (Patron a : check) {
			if (firstName.equalsIgnoreCase(a.getFirstName())) {
				// return object patron
			}
		}
	}
}
