<%@ page import="ls.file"%>
<%@ page import="ls.mataKuliah"%>
<%@ page import="ls.ruangan"%>
<%@ page import="ls.waktu"%>
<%@ page import="ls.day"%>
<%@ page import="ls.hill"%>
<%@ page import="ls.annealing"%>
<%@ page import="ls.genetic"%>
<%@ page import="java.util.ArrayList" %>

<html>
	<head>
		<title> Penjadwalan </title>
		<style>
			ul {
				list-style-type: none;
				margin: 0;
				padding: 0;
				overflow: hidden;
				background-color: #333;
			}

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
			div.ads {
				background-color: lightgrey;
				width: 300px;
				border: 25px solid green;
				padding: 25px;
				margin: 25px;
			}
			table, th, td {
				border: 1px solid black;
				border-collapse: collapse;
			}
		</style>
	</head>
	<body>
		<ul>
		  <li><a href="index.html">Unggah Berkas</a></li>
		  <li><a href="upload.jsp">Pilih Algoritma</a></li>
		  <li><a class="active">Penjadwalan</a></li>
		</ul>
		
		<h1><b>Jadwal</b></h1>
		<br/>
		<%
			ServletContext context = pageContext.getServletContext();
			String file_name = request.getParameter("name");
			String full_path = request.getServletContext().getRealPath("")+context.getInitParameter("file-upload") + file_name;
			String algorithm = request.getParameter("algo");
			
			//membaca file
			file.set_file(full_path);
			file.baca_file();
			
			//inisialisasi random
			out.println("Setelah diinisialisasi secara random<br>");
			file.inisialisasi_random();
			/*for (int i = 0; i < file.kuliah.size(); i++) {
				out.println(file.kuliah.get(i).jadwal_html());
				out.println("<br/>");
			}*/
			
			
			//menghitung konflik saat ini
			int konflik_now = file.hitung_konflik();
			out.println("Konflik sebelum scheduling= "+konflik_now+"<br/>");
			out.println("<br><br>");
			
			//Algoritma
			if(algorithm.equals("1")){
				out.println("<b>Algoritma: Hill-Climbing</b><br><br/>");
				hill.set_batas(5*11*file.j_ruang*file.j_kuliah);
				hill.start(file.kuliah);
				
				//menghitung konflik saat ini
				konflik_now = file.hitung_konflik();
				out.println("Konflik saat ini= "+konflik_now+"<br/>");
				out.println("<br><br><br>");
			}
			else if(algorithm.equals("2")){
				out.println("<b>Algoritma: Simulated Annealing</b><br/>");
				annealing.start(file.kuliah);
				konflik_now=file.hitung_konflik();
				if(konflik_now!=0){
					hill.set_batas(5*11*file.j_ruang*file.j_kuliah);
					hill.start(file.kuliah);
				}
				
				//menghitung konflik saat ini
				konflik_now = file.hitung_konflik();
				out.println("Konflik saat ini= "+konflik_now+"<br/>");
				out.println("<br><br><br>");
			}
			else if(algorithm.equals("3")){
				out.println("<b>Algoritma: Genetic Algorithm</b><br/>");
				genetic g = new genetic();
				file.kuliah = g.get();
				
				//menghitung konflik saat ini
				konflik_now = g.get_konflik_now();
				out.println("Konflik saat ini= "+konflik_now+"<br/>");
				out.println("<br><br>");
			}
			
		%>
		<% //print jadwal %>
		<table style="width:100%">
		<%
			int i, j;
			int totaljdwlbentrok=0;
			for (i = 6; i <= 17; i++){
				out.println("<tr>");
				if(i == 6){
					out.println("<td></td><td><b>Senin</b></td><td><b>Selasa</b></td><td><b>Rabu</b></td><td><b>Kamis</b></td><td><b>Jumat</b></td>");
				}
				else{
					for(j = 0; j <= 5; j++){
						if(j == 0){
							out.print("<td><b>"+(7+i-1)+".00</b></td>");
						}
						else{
							out.print("<td>");
							boolean found = false;
							boolean bentrok;
							ArrayList<String> arrayRuangTerpakai = new ArrayList<String>();
							int idxarr = 0;
							for(int k = 0; k < file.kuliah.size(); k++){
								bentrok = false;
								if(file.kuliah.get(k).get_slot_hari() == j){
									if((i >= (file.kuliah.get(k).get_slot_jam())) && (i < (file.kuliah.get(k).get_slot_jam()) + file.kuliah.get(k).get_sks())){
										if(found){
											out.print("<br>");
										}
										for(int l=0; l<arrayRuangTerpakai.size(); l++){
											if(file.kuliah.get(k).get_ruang().get_nama().equals(arrayRuangTerpakai.get(l))){
												bentrok = true;
												break;
											}
										}
										if(!bentrok){
											arrayRuangTerpakai.add(new String(file.kuliah.get(k).get_ruang().get_nama()));
											out.print("<a href=\"infoubahjadwal.jsp?IDmatkul="+file.kuliah.get(k).get_id()+"\">"+file.kuliah.get(k).get_nama()+" - "+file.kuliah.get(k).get_ruang().get_nama()+"</a>");
										}
										else{
											out.print("<a href=\"infoubahjadwal.jsp?IDmatkul="+file.kuliah.get(k).get_id()+"\"><b style=\"color:red;\">"+file.kuliah.get(k).get_nama()+" - "+file.kuliah.get(k).get_ruang().get_nama()+"</b></a>");
										}
										found = true;
									}
								}
							}
							out.print("</td>");
						}
					}
					out.println("</tr>");
				}
			}
		%>
		</table>
		<div class="ads">
			<%
				out.println("<br><h2 style=\"text-align:center\">Mencoba algoritma yang lain?</h2><br>");
				out.println("<button onclick=\"location.href = 'solve.jsp?name="+file_name+"&algo=1'\"; id=\"myButton\" class=\"float-left submit-button\" style=\"background-color: green; border: none; color: white; padding: 16px 56px; text-decoration: none; margin: 8px 50px;cursor: pointer;\">Hill-Climbing</button><br/>");
				out.println("<button onclick=\"location.href = 'solve.jsp?name="+file_name+"&algo=2'\"; id=\"myButton\" class=\"float-left submit-button\" style=\"background-color: green; border: none; color: white; padding: 16px 32px; text-decoration: none; margin: 8px 50px;cursor: pointer;\">Simulated Annealing</button><br/>");
				out.println("<button onclick=\"location.href = 'solve.jsp?name="+file_name+"&algo=3'\"; id=\"myButton\" class=\"float-left submit-button\" style=\"background-color: green; border: none; color: white; padding: 16px 41px; text-decoration: none; margin: 8px 50px;cursor: pointer;\">Genetic Algorithm</button>");
			%>
		</div>
	</body>
</html>
