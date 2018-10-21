package com.java.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = "jdbc:mysql://localhost:3306/NetSec";
        this.jdbcUsername = "root";
        this.jdbcPassword = "";
    }
     
    protected Connection connect() throws SQLException {
    	if (jdbcConnection != null)
            return jdbcConnection;
        else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return jdbcConnection;
        }
    }
     
    
    protected void disconnect() throws SQLException {
            jdbcConnection.close();
    }
     
    public int insertUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (id, username, password, role) VALUES (?, ?, ?, ?);";
        //connect();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, user.getId());
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getRole());
        
        
        System.out.println(statement);
        int i = statement.executeUpdate();
        System.out.println(i);
        statement.close();
        disconnect();
        return i;
    }
     
    public List<User> listAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();
         
        String sql = "SELECT * FROM Users";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int id = resultSet.getInt("Id"); 
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String passHash = resultSet.getString("passHash");
            String role = resultSet.getString("role");
             
            User user = new User(id, username, password, passHash, role);
            listUser.add(user);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
        System.out.println("Disconnected!!");
         
        return listUser;
    }
     
    public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM Users where Id = ?";
         
        connect();
        System.out.println("Connected to Database");
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, user.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE Users SET username = ?, role = ?";
        sql += " WHERE Id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getRole());
        statement.setInt(4, user.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public User getUser(String username) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM Users WHERE username = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, username);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	int id = resultSet.getInt("Id");
        	String uname = resultSet.getString("username");
            String password = resultSet.getString("password");
            String passHash = resultSet.getString("passHash");
            String role = resultSet.getString("role");
             
            user = new User(id, uname, password, passHash, role);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public String login(String username, String password) throws SQLException
    {
    	Connection connection = connect();
    	System.out.println("Inside DAO");
    	
    	String result = null;
        String sql = "SELECT * FROM Users WHERE username = ?";
         
        
        System.out.println("Connected!!");
         
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        System.out.println(statement); 
        ResultSet resultSet = statement.executeQuery();
        System.out.println(resultSet);
        if (resultSet.next()) {
        	String uname = resultSet.getString("username");
            String pass = resultSet.getString("password");
            String role = resultSet.getString("role");
        
            if(username == uname  && password == pass)
               	result = role;
            
        	else
        		result = "error";       	
        }
    
        return result;
    }
}
