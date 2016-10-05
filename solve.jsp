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
			#wrap {
				float: right;
			   width:600px;
			   margin-right:0 auto;
			}
			#left_col {
			   float:left;
			   width:300px;
			}
			#right_col {
			   float:right;
			   width:300px;
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
			int konflik_now = file.hitung_konflik();
			if (!(algorithm.equals("0"))){
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
				konflik_now = file.hitung_konflik();
			}
			out.println("Konflik sebelum scheduling= "+konflik_now+"<br/>");
			out.println("<br><br>");
			
			//Algoritma
			if(algorithm.equals("1")){
				out.println("<b>Algoritma: Hill-Climbing</b><br><br/>");
				hill.set_batas(5*11*file.j_ruang*file.j_kuliah);
				hill.start(file.kuliah);
				
				//menghitung konflik saat ini
				konflik_now = file.hitung_konflik();
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
			}
			else if(algorithm.equals("3")){
				out.println("<b>Algoritma: Genetic Algorithm</b><br/>");
				genetic g = new genetic();
				file.kuliah = g.get();
				
				//menghitung konflik saat ini
				konflik_now = g.get_konflik_now();
			}
			
		%>
		<% //print jadwal %>
		<table style="width:100%">
		<%
			file.pewarnaanjadwal();
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
							//out.print("<td><b>"+(i)+".00</b></td>");
							if(i<10){
								out.print("<td><b>0"+i+".00</b></td>");
							}
							else{
								out.print("<td><b>"+i+".00</b></td>");
							}
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
											String warna = "black";
											if (file.kuliah.get(k).warna == 1){
												warna = "blue";
											} else if (file.kuliah.get(k).warna == 2){
												warna = "orange";
											} else if (file.kuliah.get(k).warna == 3){
												warna = "#5F9EAD";
											} else if (file.kuliah.get(k).warna == 4){
												warna = "#03FF0C";
											} else if (file.kuliah.get(k).warna == 5){
												warna = "#000030";
											} else if (file.kuliah.get(k).warna == 6){
												warna = "#7FFFD4";
											} else if (file.kuliah.get(k).warna == 7){
												warna = "#8A2BE2";
											} else if (file.kuliah.get(k).warna == 8){
												warna = "#884433";
											} else if (file.kuliah.get(k).warna == 9){
												warna = "#F2F200";
											} else if (file.kuliah.get(k).warna == 10){
												warna = "#907084";
											} else if (file.kuliah.get(k).warna == 11){
												warna = "#FF33DD";
											} else if (file.kuliah.get(k).warna == 12){
												warna = "#CCFFDD";
											} else if (file.kuliah.get(k).warna == 13){
												warna = "#CCE6FF";
											} else if (file.kuliah.get(k).warna == 14){
												warna = "#1A8CFF";
											} else if (file.kuliah.get(k).warna == 15){
												warna = "#80BFFF";
											} else if (file.kuliah.get(k).warna == 16){
												warna = "#999900";
											} else if (file.kuliah.get(k).warna == 17){
												warna = "#999966";
											} else if (file.kuliah.get(k).warna == 18){
												warna = "#444444";
											} 
											
											arrayRuangTerpakai.add(new String(file.kuliah.get(k).get_ruang().get_nama()));
											out.print("<a href=\"infoubahjadwal.jsp?IDmatkul="+file.kuliah.get(k).get_id()+"&name="+file_name+"\"><b style='color:"+warna+"'>"+file.kuliah.get(k).get_nama()+" - "+file.kuliah.get(k).get_ruang().get_nama()+"</b></a>");
										}
										else{
											out.print("<a href=\"infoubahjadwal.jsp?IDmatkul="+file.kuliah.get(k).get_id()+"&name="+file_name+"\"><b style=\"color:red;\">"+file.kuliah.get(k).get_nama()+" - "+file.kuliah.get(k).get_ruang().get_nama()+"</b></a>");
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
		<br/>
		<div id="wrap">
			<div id="left_col">
			<h3>persentase ruang:</h3>
				<br/>
				<%
					file.hitung_persentase();
					for (int xx=0;xx<file.j_ruang;xx++){
						out.println(file.ruang[xx].get_nama() + " = " +(double)Math.round(file.ruang[xx].persentase*100)/100+" %</br>");
					}
				%>
			</div>
			<div id="right_col">
				<h3>konflik:</h3>
				konflik sekarang <%=konflik_now%><br/>
			</div>
		</div>
		<div class="ads">
		<div>
			<%
				out.println("<br><h2 style=\"text-align:center\">Mencoba algoritma yang lain?</h2><br>");
				out.println("<button onclick=\"location.href = 'solve.jsp?name="+file_name+"&algo=1'\"; id=\"myButton\" class=\"float-left submit-button\" style=\"background-color: green; border: none; color: white; padding: 16px 56px; text-decoration: none; margin: 8px 50px;cursor: pointer;\">Hill-Climbing</button><br/>");
				out.println("<button onclick=\"location.href = 'solve.jsp?name="+file_name+"&algo=2'\"; id=\"myButton\" class=\"float-left submit-button\" style=\"background-color: green; border: none; color: white; padding: 16px 32px; text-decoration: none; margin: 8px 50px;cursor: pointer;\">Simulated Annealing</button><br/>");
				out.println("<button onclick=\"location.href = 'solve.jsp?name="+file_name+"&algo=3'\"; id=\"myButton\" class=\"float-left submit-button\" style=\"background-color: green; border: none; color: white; padding: 16px 41px; text-decoration: none; margin: 8px 50px;cursor: pointer;\">Genetic Algorithm</button>");
			%>
		</div>
	</body>
</html>
