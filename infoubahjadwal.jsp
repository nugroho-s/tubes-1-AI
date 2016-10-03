<%@ page import="ls.file"%>
<%@ page import="ls.ruangan"%>
<%@ page import="ls.day"%>
<%@ page import="ls.mataKuliah"%>
<%@ page import="ls.slot"%>
<%@ page import="ls.waktu"%>
<html>
	<head>
		<title>Info dan Perubahan Jadwal</title>
		<style>
			li {
				float: left;
			}

			li a {
				display: block;
				color: white;
				text-align: center;
				padding: 14px 16px;
				text-decoration: none;
			}

			li a:hover:not(.active) {
				background-color: #111;
			}

			.active {
				background-color: rgb(11, 158, 255);
			}
		</style>
	</head>
	<body>
		<%
			String id = request.getParameter("IDmatkul");
			String file_name = request.getParameter("name");
		%>
		<h1><b>Informasi dan Perubahan Jadwal</b></h1>
		<br><br>
		<table style="width:100%">
		<%
			out.println("<tr>");
			out.println("<td>Mata kuliah: </td>");
			int i=0;
			while(file.kuliah.get(i).get_id())
			out.println("<td>"+
		%>
		</table>
	</body>
</html>
		
