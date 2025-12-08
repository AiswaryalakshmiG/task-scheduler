package com.example.tasks.servlet;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.example.tasks.dao.UserDAO;
import com.example.tasks.dao.impl.UserDaoImpl;
import com.example.tasks.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDaoImpl();
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	
		        req.getRequestDispatcher("/login.jsp").forward(req, resp);
		    }

			/*
			 * resp.setContentType("text/html;charset=UTF-8");
			 * 
			 * PrintWriter out = resp.getWriter(); out.println("<html><body>");
			 * out.println("<h2>Login Page</h2>");
			 * out.println("<form action='login' method='post'>");
			 * out.println("Username: <input type='text' name='username'/><br/>");
			 * out.println("Password: <input type='password' name='password'/><br/>");
			 * out.println("<input type='submit' value='Login'/>"); out.println("</form>");
			 * out.println("</body></html>");
			 */
	 
	 
	 protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        String username = req.getParameter("username");
	        String password = req.getParameter("password");

	        
	        User user = userDAO.findByUsername(username);
	        if (user != null) {
	            String storedHash = user.getPassword(); 
	            if (storedHash != null && BCrypt.checkpw(password, storedHash)) {
	                
	                HttpSession session = req.getSession(true);
	                session.invalidate(); 
	                session = req.getSession(true);
	                session.setMaxInactiveInterval(30 * 60); 
	                session.setAttribute("authUser", user);
	                resp.sendRedirect(req.getContextPath() + "/home.jsp");
	                return;
	            }
	 }
	        resp.sendRedirect(req.getContextPath() + "/login-error.jsp");
	 }
}
