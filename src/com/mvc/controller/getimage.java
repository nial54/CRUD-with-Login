package com.mvc.controller;

import java.io.IOException;
import java.io.OutputStream;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.util.DBConnection;

@WebServlet("/getimage")
public class getimage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id =(int)session.getAttribute("id");
        System.out.println("Id in imageretrieve="+id);
	    try 
	    {
			Connection conn = DBConnection.createConnection();
	        PreparedStatement ps = conn.prepareStatement("select * from pro2 where id=?");
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            Blob blob = rs.getBlob("photo");
	            byte byteArray[] = blob.getBytes(1, (int) blob.length());
	            response.setContentType("image/gif");
	            OutputStream os = response.getOutputStream();
	            os.write(byteArray);
	            os.flush();
	            os.close();
	        } 
	        else 
	        {
	            System.out.println("No image found with this id.");
	        }
	    } 
	    catch (Exception e) 
	    {
	    	e.printStackTrace();
	    }
	}

}