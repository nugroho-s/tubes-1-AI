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
			
		</style>
	</head>
	<body>
		<%
			String idmatkul = request.getParameter("IDmatkul");
			String file_name = request.getParameter("name");
			int mid = Integer.parseInt(idmatkul);
		%>
		<h1><b>Informasi dan Perubahan Jadwal</b></h1>
		<br><br>
		<table style="width:20%">
		<%
			int i=0;
			while(file.kuliah.get(i).get_id()!=mid){
				i++;
			}
			out.println("<tr>");
			out.println("<td>Mata kuliah: </td>");
			out.println("<td>"+file.kuliah.get(i).get_nama()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Jumlah SKS: </td>");
			out.println("<td>"+file.kuliah.get(i).get_sks()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Jadwal kuliah: </td>");
			out.println("<td>");
			switch (file.kuliah.get(i).get_slot_hari()){
				case 1	:	out.println("Hari Senin<br>");
							break;
				case 2	:	out.println("Hari Selasa<br>");
							break;
				case 3	:	out.println("Hari Rabu<br>");
							break;
				case 4	:	out.println("Hari Kamis<br>");
							break;
				case 5	:	out.println("Hari Jumat<br>");
							break;
			}
			out.println("Pukul "+file.kuliah.get(i).get_slot_jam()+".00 - "+(file.kuliah.get(i).get_slot_jam()+file.kuliah.get(i).get_sks())+".00</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Ruangan: </td>");
			out.println("<td>"+file.kuliah.get(i).get_ruang().get_nama()+"</td>");
			out.println("</tr>");
		%>
		</table>
		<form method="post" action="ubahjadwal.jsp">
			<%
				out.println("<input type=\"hidden\" name=\"IDmatkul\" value=\""+idmatkul+"\"> <br>");
				out.println("<input type=\"hidden\" name=\"fname\" value=\""+file_name+"\">");
				out.println("Mata Kuliah: ");
				out.println("<input type=\"text\" name=\"matkul\" value=\""+file.kuliah.get(i).get_nama()+"\" readonly><br>");
			%>
			<b>Hari:</b><br>
			<input type="radio" name="hari" value=1> Senin <br>
			<input type="radio" name="hari" value=2> Selasa <br>
			<input type="radio" name="hari" value=3> Rabu <br>
			<input type="radio" name="hari" value=4> Kamis <br>
			<input type="radio" name="hari" value=5> Jumat <br>
			<br>
			<b> Waktu mulai:</b><br>
			<input type="radio" name="jam" value=7> 07.00
			<input type="radio" name="jam" value=8> 08.00
			<input type="radio" name="jam" value=9> 09.00
			<input type="radio" name="jam" value=10> 10.00
			<input type="radio" name="jam" value=11> 11.00
			<input type="radio" name="jam" value=12> 12.00<br>
			<input type="radio" name="jam" value=13> 13.00
			<input type="radio" name="jam" value=14> 14.00
			<input type="radio" name="jam" value=15> 15.00
			<input type="radio" name="jam" value=16> 16.00
			<input type="radio" name="jam" value=17> 17.00 <br>
			<br>
			<b> Pindah ke ruang:</b><br>
			<%
				for(int j = 0; j < file.j_ruang; j++){
					if(j%5 == 0){
						out.println("<input type=\"radio\" name=\"ruang\" value=\""+file.ruang[j].get_nama()+"\"> "+file.ruang[j].get_nama()+" <br>");
					}
					else{
						out.println("<input type=\"radio\" name=\"ruang\" value=\""+file.ruang[j].get_nama()+"\"> "+file.ruang[j].get_nama());
					}
				}
			%>
			<br><br>
			<input type="submit" value="Ubah Jadwal">
		</form>
		 <button onclick="goBack()">Kembali</button> <br>
		<script>
		function goBack() {
			window.history.back();
		}
		</script>
	</body>
</html>
		
