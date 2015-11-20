package com.nds.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PubAddSrvlt
 */
public class PubAddSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PubAddSrvlt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title 	= request.getParameter("title");
		String genre 	= request.getParameter("genre");
		String price 	= request.getParameter("price");
		String day1 , day2, day3, day4, day5, day6, day7;
		 day1 	= request.getParameter("day1");
		 day2 	= request.getParameter("day2");
		 day3 	= request.getParameter("day3");
		 day4 	= request.getParameter("day4");
		 day5 	= request.getParameter("day5");
		 day6 	= request.getParameter("day6");
		 day7 	= request.getParameter("day7");
		String freq 	= request.getParameter("freq");
		
		if(!title.isEmpty() && !price.isEmpty() && !genre.isEmpty()){
			if(freq.equals("day"))
				title = "first cond";//publication thisPub = new publication(title, genre, price);
			else{
				Boolean []schedule = new Boolean[7]; 
				
				if(day1 != null)
					schedule[6] = false;
				else
					schedule[6] = true;				
				
				if(day2 != null)
					schedule[0] = false;
				else
					schedule[0] = true;
				
				if(day3 != null)
					schedule[1] = false;
				else
					schedule[1] = true;
				
				if(day4 != null)
					schedule[2] = false;
				else
					schedule[2] = true;
				
				if(day5 != null)
					schedule[3] = false;
				else
					schedule[3] = true;
				
				if(day6 != null)
					schedule[4] = false;
				else
					schedule[4] = true;
				
				if(day7 != null)
					schedule[5] = false;
				else
					schedule[5] = true;
				
				title = "second cond";//publication thisPub = new publication(title, genre, price, freq, schedule);
			}							
			
			request.setAttribute("Msg", "<p style='color:darkred'>" + title + " was added successfully!</p>");
			request.getRequestDispatcher("/PublicationAdd.jsp").forward(request, response);
		}else{
			request.setAttribute("Msg", "<p style='color:darkred'> Publication unsuccessfully added. One or more fields was left blank.</p>");
			request.getRequestDispatcher("/PublicationAdd.jsp").forward(request, response);
		}
	}

}
