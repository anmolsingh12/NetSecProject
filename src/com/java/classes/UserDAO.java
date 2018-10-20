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
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (id, username, password, role) VALUES (?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, user.getId());
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getRole());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
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
         
        return listUser;
    }
     
    public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM Users where Id = ?";
         
        connect();
         
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
    	String result = null;
        String sql = "SELECT * FROM Users WHERE username = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, username);
         
        ResultSet resultSet = statement.executeQuery();
         
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
