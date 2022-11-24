package com.mvc.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.mvc.model.Data;
import com.mvc.util.DBConnection;
  
public class DataDao {
	private static final String SELECT_REGI_BY_ID = "select id, name, email, username from pro2 where id=?; ";
	private static final String DELETE_REGI_SQL = "delete from pro2 where id = ?;";
	private static final String UPDATE_FILE = "update pro2 set file =?, comment =? where id = ?;";
	private static final String UPDATE_ACC = "update pro2 set name =?, email =?, username =?, password =?, contact =?, photo =? where id = ?;";
	private static final String VIEW_DATA = "select id, name, email, username, password, contact, photo, file, comment from pro2 where id=?;";
	private static final String DATA_PEEK = "select id, username from pro2;";
	
	public DataDao() 
	{}
	
	// connection
	protected Connection getConnection() {
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DBConnection.createConnection();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return conn;
		}
		
	// delete
	public boolean deleteRegi (int id) throws SQLException {
		boolean rowDeleted;
		try
		{
			Connection conn = DBConnection.createConnection();
			PreparedStatement ps = conn.prepareStatement(DELETE_REGI_SQL);
				ps.setInt(1, id);
				rowDeleted = ps.executeUpdate() > 0;
		}
		finally
		{}
			return rowDeleted;
		}
		
		//View record
	public List<Data> PeekRecord() {
			List<Data> d = new ArrayList<>();
			try
			{
				Connection conn = DBConnection.createConnection();
				PreparedStatement ps = conn.prepareStatement(DATA_PEEK);
					System.out.println(ps);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) 
					{
						int id = rs.getInt("id");
						String username = rs.getString("username");
						d.add(new Data(id, username));
					}	
			}
			catch (SQLException e) 
			{
				printSQLException(e);
			}
			return d;
		}
		
	public Data viewRd (int id) {
		Data d = null;
		try 
		{
			Connection conn = DBConnection.createConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT_REGI_BY_ID);
				ps.setInt(1, id);
				System.out.println(ps);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) 
				{
					String name = rs.getString("name");
					String email = rs.getString("email");
					String username = rs.getString("username");
					d = new Data(id, name, email, username);
				}	
		}
		catch (SQLException e) 
		{
			printSQLException(e);
		}
		return d;
		}
		
	public boolean updatefile (Data d) throws SQLException, FileNotFoundException {
			boolean rowUpdate;
			try 
			{
				Connection conn = DBConnection.createConnection();
				PreparedStatement ps = conn.prepareStatement(UPDATE_FILE);
					System.out.println("Update Record "+ps);
					String t = d.getFile();
					File f = new File (t);
					FileReader fr = new FileReader(f);
					ps.setCharacterStream(1, fr, (int)f.length()); 
					ps.setString(2, d.getComment());
					ps.setInt(3, d.getId());
					rowUpdate = ps.executeUpdate() > 0;
			}
			finally
			{}
			return rowUpdate;
		}
		
	public boolean updateacc (Data d) throws SQLException, FileNotFoundException {
			boolean rowUpdate;
			try 
			{
				Connection conn = DBConnection.createConnection();
				PreparedStatement ps = conn.prepareStatement(UPDATE_ACC);
					System.out.println("Update Account "+ps); 
					System.out.println(d.getId()+d.getName()+d.getEmail()+ d.getUsername()+d.getContact()+d.getPhoto());
					ps.setString(1, d.getName()); 
					ps.setString(2, d.getEmail());
					ps.setString(3, d.getUsername());
					ps.setString(4, d.getPassword());
					ps.setString(5, d.getContact());
					File file = new File (d.getPhoto());
					FileInputStream fis = new FileInputStream(file);
					ps.setBlob(6, fis);
					ps.setInt(7, d.getId());;				
					
					rowUpdate = ps.executeUpdate() > 0;
			}
			finally
			{}
			return rowUpdate;
		}
	
	public Data viewall (int id) {
		Data d = null;
		try 
		{
			Connection conn = DBConnection.createConnection();
			PreparedStatement ps = conn.prepareStatement(VIEW_DATA);
				ps.setInt(1, id);
				System.out.println(ps);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					String username = rs.getString("username");
					String password = rs.getString("password");
					String contact = rs.getString("contact");
					String photo = rs.getString("photo");
					String file = rs.getString("file");
					String comment = rs.getString("comment");
					d = new Data(id, name, email, username, password, contact, photo, file, comment);
				}
		}
		catch (SQLException e) 
		{
			printSQLException(e);
		}
		return d;	
	}
	
	private void printSQLException(SQLException ex) {
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
			
		
}