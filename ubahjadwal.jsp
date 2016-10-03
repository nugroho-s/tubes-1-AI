<%@ page import="ls.file"%>
<%@ page import="ls.ruangan"%>
<%@ page import="ls.day"%>
<%@ page import="ls.mataKuliah"%>
<%@ page import="ls.slot"%>
<%@ page import="ls.waktu"%>
<html>
	<head>
		<title> Perubahan Jadwal </title>
	</head>
	<body>
		<center>
		<h1><b>Perubahan Jadwal</b></h1>
		<br>
		<form method="get" action="infoubahjadwal.jsp">
		<%
			//String idmatkul = request.getParameter("IDmatkul");
			String file_name = request.getParameter("fname");
			out.println("<meta http-equiv=\"refresh\" content=\"5; URL='solve.jsp?name="+file_name+"&algo=0'\">");
			int hari0 = Integer.parseInt(request.getParameter("hari"));
			int waktu0 = Integer.parseInt(request.getParameter("jam"));
			String rr = request.getParameter("ruang");
			int mid = Integer.parseInt(request.getParameter("IDmatkul"));
			
			int i=0;
			while(file.kuliah.get(i).get_id()!=mid){
				i++;
			}
			
			//ubah waktu
			waktu ww = new waktu();
			ww.set_jam(waktu0);
			ww.set_menit(0);
			waktu vv = new waktu();
			vv.set_jam(waktu0+file.kuliah.get(i).get_sks());
			vv.set_menit(0);
			file.kuliah.get(i).set_mulai(ww);
			file.kuliah.get(i).set_selesai(vv);
			
			//ubah hari
			day hh = new day();
			for (int j = 0; j < 6; j++) {
				hh.set_hari(j, false);
			}
			hh.set_hari(hari0, true);
			file.kuliah.get(i).set_hari(hh);
			
			file.kuliah.get(i).set_ruang(rr);
			/*for (int jj=0; jj<file.j_ruang; jj++){
				if (rr.equals(ruang[jj].get_nama())){
					file.kuliah.get(i).set_ruang_ref(ruang[jj]);
				}
			}*/
			file.kuliah.get(i).set_slot(hari0,waktu0);
			out.println("Jika browser tidak berjalan secara otomatis dalam beberapa detik, <a href='solve.jsp?name="+file_name+"&algo=0'\">klik di sini</a> untuk menuju halaman tujuan <br>");
		%>
		</form>
	</body>
</html>
