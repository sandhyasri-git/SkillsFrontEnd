package com.niit.skillsfront.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/**
 * Servlet implementation class Home
 */
@WebServlet("/")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
PrintWriter out=response.getWriter();
				
	//request.getRequestDispatcher(getServletContext().getInitParameter("views")+"Home.jsp").forward(request, response);
	
	RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/Home.jsp");
	rd.forward(request, response);
	}

}
