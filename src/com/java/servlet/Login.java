package com.java.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.java.classes.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final String jdbcURL = "jdbc:mysql://localhost:3306/NetSec";
	private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "";
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
    public Login() {
        super();
        userDAO = new UserDAO(jdbcURL, jdbcUsername,jdbcPassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String username = request.getParameter("uname");
        String password = request.getParameter("pass");
        String role = "";
        
        try {
        role = userDAO.login(username, password);
        
        
        if(role == "admin") 
        {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage.jsp");
        	dispatcher.forward(request, response);
        }
        
        else if(role == "user")
        {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("customerPage.jsp");
        	dispatcher.forward(request, response);
        }
        
        else
        {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
        	dispatcher.forward(request, response);
        }
        }catch (Exception ex) {}
	}

}
