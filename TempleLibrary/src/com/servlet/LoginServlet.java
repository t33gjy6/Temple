package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/demo_form")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// read form fields
		String phoneNumber = request.getParameter("PhoneNumber");
		String name = request.getParameter("name");

		System.out.println("username: " + phoneNumber);
		System.out.println("password: " + name);

		// do some processing here...

		// get response writer
		PrintWriter writer = response.getWriter();

		/*
		 * // build HTML code String htmlRespone = "<html>"; htmlRespone +=
		 * "<h2>Your username is: " + username + "<br/>"; htmlRespone +=
		 * "Your password is: " + password + "</h2>"; htmlRespone += "</html>";
		 */
		/*
		 * // return response writer.println(htmlRespone);
		 */

	}

}
