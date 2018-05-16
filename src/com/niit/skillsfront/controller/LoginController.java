package com.niit.skillsfront.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.niit.skillmap.model.Employee;
import com.niit.skillmap.repository.EmployeeRepository;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	public LoginController() {
		
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		rd.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String email=request.getParameter("mail");
		String password=request.getParameter("pass");
		EmployeeRepository empRepo=new EmployeeRepository();
		int counter=empRepo.validateUser(email, password);
		if(counter>0)
		{
			Employee employee=empRepo.getEmployeeData(email);
			if(employee.getEmp_role().equals("hr"))
			{	
				request.setAttribute("employee", employee);
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("/WEB-INF/views/HrUser.jsp");
				requestDispatcher.forward(request, response);
			}
			if(employee.getEmp_role().equals("employee"))
			{	
				request.setAttribute("employee", employee);
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("/WEB-INF/views/Profile.jsp");
				requestDispatcher.forward(request, response);
			}
			
		}
	
		else
		{
			out.println("Wrong Username or Password try to login again");
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(request, response);
		}
		
		
	}

}
