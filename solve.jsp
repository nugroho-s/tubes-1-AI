<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%@ page import="ls.file"%>
<%@ page import="ls.mataKuliah"%>
<%@ page import="ls.ruangan"%>
<%@ page import="ls.waktu"%>
<%@ page import="ls.day"%>

<%
	ServletContext context = pageContext.getServletContext();
	String file_name = request.getParameter("name");
	String full_path = context.getInitParameter("file-upload") + file_name;
	file.set_file(full_path);
	file.baca_file();
	file.inisialisasi_random();
	out.println("Jadwal<br/>");
	out.println("<hr/>");
	for (int i = 0; i < file.kuliah.size(); i++) {
		out.println(file.kuliah.get(i).jadwal_html());
		out.println("<br/>");
	}
	System.out.println("=======<br/>");
	int konflik_now = file.hitung_konflik();
	out.println("Konflik = "+konflik_now+"<br/>");
	
	
	
	
%>

</body>
</html>