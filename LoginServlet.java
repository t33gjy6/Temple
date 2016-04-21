package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.temple.lib.DatabaseConnection;
import com.temple.lib.*;

@WebServlet("/demo_form")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String phoneNumber = request.getParameter("PhoneNumber");
		String name = request.getParameter("name");
		phoneNumber = Temple.formatPhoneNumber(phoneNumber);
		
		String query;
		try {
			if(phoneNumber != ""){
				query = "PhoneNumber";
				DatabaseConnection.checkInfo(phoneNumber, query);
			}
			
			if(name != ""){
				query = "";	
				DatabaseConnection.checkInfo(name, query);
			}
			
			//DatabaseConnection.insertInfo(name, name, phoneNumber, 0, DatabaseConnection.preparedDatabase());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("number: " + phoneNumber);
		System.out.println("password: " + name);

	}
}
