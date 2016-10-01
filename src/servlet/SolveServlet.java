package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ls.file;

/**
 * Servlet implementation class SolveServlet
 */
@WebServlet("/SolveServlet")
public class SolveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SolveServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			ServletContext context = getServletContext();
			String file_name = request.getParameter("name");
			String full_path = context.getInitParameter("file-upload") + file_name;
			
			
			file.set_file(full_path);
			file.baca_file();
			file.inisialisasi_random();
			
		  // Set the response message's MIME type
	      response.setContentType("text/html;charset=UTF-8");
	 
	      // Allocate a output writer to write the response message into the network socket
	      PrintWriter out = response.getWriter();
	   
	      	try {
	      		out.println(full_path);
				
	      		out.println("Jadwal<br/>");
				out.println("<hr/>");
				for (int i = 0; i < file.kuliah.size(); i++) {
					out.println(file.kuliah.get(i).jadwal_html());
					out.println("<br/>");
				}
				
				int konflik_now = file.hitung_konflik();
				out.println("Konflik = "+konflik_now+"<br/>");
				
				out.println("Start algorithm");
				out.println("<button onclick=\"location.href = 'table'\"; id=\"myButton\" class=\"float-left submit-button\" >Start algorithm</button>");
		        
				
	      	} finally {
	      		out.close();
	      	}
	}

}
