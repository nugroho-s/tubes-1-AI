package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ls.file;
import ls.hill;

/**
 * Servlet implementation class TableServlet
 */
@WebServlet("/TableServlet")
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TableServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// algoritma hill climbing
		hill.set_batas(5*11*file.j_ruang);
		hill.start(file.kuliah);

		/*
		// Set the response message's MIME type
	      response.setContentType("text/html;charset=UTF-8");
	 
	      // Allocate a output writer to write the response message into the network socket
	      PrintWriter out = response.getWriter();
	   
	      try {

	    	  out.println("=====================");
			
			for (int i = 0; i < file.kuliah.size(); i++) {
				out.println(file.kuliah.get(i).jadwal_html());
				out.println("<br/>");
			}
			
			int konflik_now = file.hitung_konflik();
			out.println("Konflik = "+konflik_now+"<br/>");
		    
	    } finally {
	    	out.close();
	    }
	    */
		
		String contextPath = request.getContextPath();
    	System.out.println("context: " + contextPath);
    	System.out.println("servlet: " + request.getServletContext());
		
		response.sendRedirect(contextPath + "/table.jsp");
		
	}

}
