package com.mvc.model;

public class Data {
	protected int id;
	protected String name, email, username, password, contact,  photo, file, comment;
	
	public Data() 
	{}

	public Data(int id, String username) 
	{
		super();
		this.id = id;
		this.username = username;
	}
	
	public Data(String name, String email, String username, String password, String contact, String photo, int id ) 
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.contact = contact;
		this.photo = photo;
	}

	public Data(int id, String file, String comment) 
	{
		super();
		this.id = id;
		this.file = file;
		this.comment = comment;
	}

	public Data(int id) 
	{
		super();
		this.id = id;
	}
	
	public Data(int id, String name, String email, String username) 
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
	}
	
	public Data(int id, String name, String email, String username, String password, String contact, String photo, String file, String comment) 
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.contact = contact;
		this.photo = photo;
		this.file = file;
		this.comment = comment;
	}
	
	public Data(int id, String name, String email, String username, String photo, String file) 
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.photo = photo;
		this.file = file;
	}
	
	public Data(int id, String name, String email, String username, String comment) 
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.comment = comment;
	}
	
	public Data(int id, String name, String email, String username, String photo, String file, String comment) 
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.photo = photo;
		this.file = file;
		this.comment = comment;
	}
	
	public Data(String file, String comment) 
	{
		super();
		this.file = file;
		this.comment = comment;
	}

	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getContact() 
	{
		return contact;
	}
	public void setPhone(String contact) 
	{
		this.contact = contact;
	}
	
	public String getPhoto() 
	{
		return photo;
	}
	public void setPhoto(String photo) 
	{
		this.photo = photo;
	}
	
	public String getFile() 
	{
		return file;
	}
	public void setFile(String file) 
	{
		this.file = file;
	}
	
	public String getComment() 
	{
		return comment;
	}
	public void setComment(String comment) 
	{
		this.comment = comment;
	}
	
}