package com.temple.lib;


public class Temple {

	static DatabaseConnection sql = new DatabaseConnection();

	public static String formatPhoneNumber(String phoneNumber) {
		String formattedNumber = phoneNumber.replaceAll("[()-]", "").replaceAll(" ", "");
		if (formattedNumber.length() == 11) {
			return formattedNumber.substring(1);
		} else {
			return formattedNumber;
		}
	}

}
