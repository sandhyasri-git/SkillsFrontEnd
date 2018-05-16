package com.niit.skillsfront.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.niit.skillmap.model.Employee;
import com.niit.skillmap.repository.EmployeeRepository;


/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/Registration")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EmployeeRepository emp;
	List<String> errorList=new ArrayList<>();
	Employee employee;
	public RegistrationController() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/Registration.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		//request.getRequestDispatcher(getServletContext().getInitParameter("views")+"Registration.jsp").forward(request, response);	
		String empId=request.getParameter("empId");
		String empName=request.getParameter("empName");
		String empAddress=request.getParameter("empAddress");
		String empPh=request.getParameter("empPh");
		
		String empEmail=request.getParameter("empEmail");
		String empQual=request.getParameter("empQual");
		String empIBU=request.getParameter("empIBU");
		String empCenter=request.getParameter("empCenter");
		String empDesig=request.getParameter("empDesig");
		String students=request.getParameter("students");
		String empPassword=request.getParameter("empPassword");
		String empCPassword=request.getParameter("empCPassword");
		String emp_role=request.getParameter("role");
		String status="Not Approved";
		
		System.out.println(empId+" "+ empName+" , "+ empAddress+", "+ empPh+ ","+ empEmail+ ", "+ empQual+ ","+ empIBU+ ","+ empCenter+","+ empDesig+","+ students +","+ empPassword+" "+emp_role+" "+status);
		int i=-1;
		try {
			employee=new Employee();
			emp=new EmployeeRepository(empId, empName, empAddress, empPh, empEmail, empQual, empIBU, empCenter, empDesig, students, empPassword,emp_role,status);
			i=emp.insertEmp();
			errorList=employee.getMap();
			if(i>0)
			{
				System.out.println("Inserted");
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/login.jsp");
				rd.include(request, response);
				
			}
			else
			{
				request.setAttribute("error", errorList);
				request.setAttribute("employee", employee);

				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/Registration.jsp");
				rd.forward(request, response);
				System.out.println("Not inserted");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
