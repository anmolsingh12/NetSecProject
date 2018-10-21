package com.java.classes;

	public class User {
		protected int id;
	    protected String username;
	    protected String pass;
	    protected String passHash;
	    protected String role;
	 
	    public User() {
	    }
	    public User (String username, String pass)
	    {	
	    	this.username = username;
	        this.pass = pass;
	    }
	 
	    public User(int id) {
	    	if(id == 0)
	    	{
	    		id++;
	    	}
	        this.id = id;
	    }
	    
	    public User(int id, String username, String pass, String role) {
	    	if(id == 0)
	    	{
	    		id++;
	    	}
	    	this.id = id;
	    	this.username = username;
	        this.pass = pass;
	        this.role= role;
	    }
	 
	    public User(int id, String username, String pass, String passHash, String role ) {
	        this(id, username, pass, role);
	        this.passHash = passHash;
	    }
	    
	    public int getId() {
	        return id;
	    }
	 
	    public void setId(int id) {
	        this.id = id;
	    }
	     
	 
	    public String getUsername() {
	        return username;
	    }
	 
	    public void setUsername(String username) {
	        this.username = username;
	    }
	 
	    public String getPassword() {
	        return pass;
	    }
	 
	    public void setPassword(String pass) {
	        this.pass = pass;
	    }
	 
	    public String getPassHash() {
	        return passHash;
	    }
	 
	    public void setPassHash(String passHash) {
	        this.passHash = passHash;
	    }
	 
	    public String getRole() {
	        return role;
	    }
	 
	    public void setRole(String role) {
	        this.role = role;
	    }
	}

