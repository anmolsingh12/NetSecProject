package com.java.servlet;

import com.java.classes.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ControllerServlet")

public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String LOGIN = "/login.jsp";
    private static String REGISTER = "/register.jsp";
	private UserDAO userDAO;
    
    public ControllerServlet() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
    	String jdbcURL = "jdbc:mysql://localhost:3306/NetSec";
        String jdbcUsername = "root";
        String jdbcPassword = "";
 
        userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    		int id = Integer.parseInt(request.getParameter("userId"));
            String uname = request.getParameter("username");
            String pass = request.getParameter("password");
            String role = request.getParameter("role");

            User newUser = new User(id, uname, pass, role);
            try {
            	userDAO.insertUser(newUser);
            }catch(SQLException sql)
            {
            	
            }
            
            response.sendRedirect("success.jsp");
    	doGet(request, response);
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String forward="";
		String action = request.getParameter("action");
		 
		try
		{
			
			if (action.equalsIgnoreCase("login")){
	                login(request, response);
	        }
            /*case "/delete":
                deleteUser(request, response);
                break;
            
            case "/edit":
                editUser(request, response);
                break;
                
            case "/update":
                updateUser(request, response);
                break;
                
            default:
                listUser(request, response);
                break;
                */
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
//    private void listUser(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        List<User> listUser = userDAO.listAllUsers();
//        request.setAttribute("listUser", listUser);
////        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
////        dispatcher.forward(request, response);
//    }
 
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SQLException, IOException {
    	String username = request.getParameter("uname");
        String password = request.getParameter("pass");
        String role = userDAO.login(username, password);
        
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
    
    
 
    /*private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.getBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
 
    }
 
    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));
 
        Book book = new Book(id, title, author, price);
        bookDAO.updateBook(book);
        response.sendRedirect("list");
    }
 
    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Book book = new Book(id);
        bookDAO.deleteBook(book);
        response.sendRedirect("list");
 
    }*/
}
