package com.nds.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustAddSrvlt
 */
public class CustAddSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustAddSrvlt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String first 	= request.getParameter("first");
		String last 	= request.getParameter("last");
		String add1 	= request.getParameter("add1");
		String add2 	= request.getParameter("add2");
		String city 	= request.getParameter("city");
		String state 	= request.getParameter("state");
		String zip 		= request.getParameter("zip");
		String phone 	= request.getParameter("phone");
		
		if(!first.isEmpty() && !last.isEmpty() && !add1.isEmpty() && !city.isEmpty() && !state.isEmpty() && !zip.isEmpty() && !phone.isEmpty() ){
		/*	if(!add2.isEmpty())
				customer thisCustomer = new customer(first, last, phone, add1, city, state, zip);
			else
				customer thisCustomer = new customer(first, last, phone, add1, add2, city, state, zip);
		*/	
			request.setAttribute("Msg", "<p style='color:darkred'>" + first + " " + last + " was added successfully!</p>");
			request.getRequestDispatcher("/CustomerAdd.jsp").forward(request, response);
		}else{
			request.setAttribute("Msg", "<p style='color:darkred'> Customer unsuccessfully added. One or more fields was left blank.</p>");
			request.getRequestDispatcher("/CustomerAdd.jsp").forward(request, response);
		}			
	}
}
