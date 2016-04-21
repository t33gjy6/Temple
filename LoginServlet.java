package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DatabaseConnection;
import com.util.Temple;

@WebServlet("/demo_form")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String phoneNumber = request.getParameter("PhoneNumber");
		String name = request.getParameter("name");
		phoneNumber = Temple.formatPhoneNumber(phoneNumber);
		try {
			DatabaseConnection.insertInfo(name, name, phoneNumber, 0, DatabaseConnection.preparedDatabase());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("number: " + phoneNumber);
		System.out.println("password: " + name);

	}
}
