package com.java.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.classes.User;
import com.java.classes.UserDAO;
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final String jdbcURL = "jdbc:mysql://localhost:3306/NetSec";
	private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "";
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
    
    
    public Register() {
        super();
        userDAO = new UserDAO(jdbcURL, jdbcUsername,jdbcPassword);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("userId"));
        String uname = request.getParameter("username");
        String pass = request.getParameter("password");
        String role = request.getParameter("role");
        int status = 0;
        
        User newUser = new User(id, uname, pass, role);
        try {
        	System.out.println("Inside Try Block!!");
        	userDAO.insertUser(newUser);
        	
        }catch(SQLException sql)
        {
        	
        }
        if(status > 0)
        	response.sendRedirect("success.jsp");
        else
        	response.sendRedirect("error.jsp");
    }

}
