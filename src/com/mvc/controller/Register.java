package com.mvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		String repass = request.getParameter("re_pass");
		String contact = request.getParameter("contact");
		String photo = request.getParameter("photo");
		
		
		RequestDispatcher dispatcher = null;
		Connection conn = null;
		if (password.equals(repass)) 
		{
			try 
			{
				conn = DBConnection.createConnection();
				PreparedStatement ps = conn.prepareStatement("insert into pro2 (name, email, username, password, contact, photo) values(?,?,?,?,?,?)");
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, username);
				ps.setString(4, password);
				ps.setString(5, contact);
				File file = new File (photo);
				FileInputStream fis = new FileInputStream(file);
				ps.setBlob(6, fis);
				
				int rowCount = ps.executeUpdate();
				dispatcher = request.getRequestDispatcher("registration.jsp");
				
				if (rowCount > 0) 
				{
					request.setAttribute("status", "success");
				}
				else 
				{
					request.setAttribute("status", "failed");
				}
				dispatcher.forward(request, response);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			finally 
			{
				try 
				{
					conn.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		else 
		{	
			dispatcher = request.getRequestDispatcher("registration.jsp");
			request.setAttribute("status", "notsame");
			dispatcher.forward(request, response);
		}
		
	}

}