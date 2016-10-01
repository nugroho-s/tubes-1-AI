<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.io.IOException" %>

<%@ page import="ls.file" %>
<%@ page import="mock.tableResult" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Table page</title>
</head>
<body>
		
	<!-- TABEL RUANG 7602 -->
	
	<% 
		
		tableResult model;
	
		
		out.println("Selesai HILL CLIMBING\n");
		out.println("=====================\n");
		
		for (int i = 0; i < file.kuliah.size(); i++) {
			out.println(file.kuliah.get(i).jadwal_html());
			out.println("<br/>");
		}
		
		int konflik_now = file.hitung_konflik();
		out.println("Konflik = "+konflik_now+"<br/>");
		
		// membentuk model tabel
		model = new tableResult();
		
		for (int idx = 0; idx < file.j_ruang; idx++) {
		
			out.println("Ruangan: " + file.ruang[idx]);
			out.println("<table border=1>");
		
			for(int i = 0; i < 11; i++) {
				
				out.println("<tr>");
				
				for (int j = 0; j < 5;j++) {
					out.println("<td width=70px height=15px>");
					
					if (model.table[i][j].get_num_matkul() > 0) {
						out.println(model.table[i][j].get_matkul().get(0).get_nama());
					}
					
					out.println("</td>");
				}
				
				out.println("</tr>");
				
			}
			
			out.println("</table>");
			out.println("<br />");
			
			model.initTable();
			if (idx + 1 < file.j_ruang) {
				model.setTableForRoom(file.ruang[idx+1]);
			}
		}
			
	%>
	
</body>
</html>