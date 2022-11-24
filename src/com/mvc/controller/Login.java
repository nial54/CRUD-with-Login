package com.mvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.model.Data;
import com.mvc.util.DBConnection;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Fuser = request.getParameter("user");
		String Fpass = request.getParameter("pass");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		@SuppressWarnings("unused")
		Data d = null;
		try 
		{
			Connection conn = DBConnection.createConnection();
			PreparedStatement ps = conn.prepareStatement("select * from pro2 where username = ? and password = ?");
			ps.setString(1, Fuser);
			ps.setString(2, Fpass);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
			{
				session.setAttribute("id", rs.getInt("id"));
				session.setAttribute("user", rs.getString("username"));
				session.setAttribute("Admin", rs.getString("admin"));
				dispatcher = request.getRequestDispatcher("list");
			}
			else 
			{
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}