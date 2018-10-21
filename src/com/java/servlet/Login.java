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
        //userDAO = new UserDAO(jdbcURL, jdbcUsername,jdbcPassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, ServletException, IOException {
    	String username = request.getParameter("uname");
        String password = request.getParameter("pass");
        String role = null;
        System.out.println(username);
        System.out.println(password);
        
        User newUser = new User(username, password);
        
        try {
			role = userDAO.login(newUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        System.out.println(role);
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
        
	}

}
