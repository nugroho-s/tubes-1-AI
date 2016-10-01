package ls;

import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;

<<<<<<< HEAD:hill.java
class hill {
	
=======
public class hill{
>>>>>>> upstream/jsp:WEB-INF/classes/ls/hill.java
	public static int batas;
	private static Random rnd = new Random();
	
	public static void set_batas(int b){
		batas = b;
	}
	public static void start(ArrayList<mataKuliah> kuliah){
		int i = 0;
		int sama = 0;
		ruangan r;
		ruangan r_old;
		int slot_old;
		int konflik;
		
		while ((file.konflik_now != 0) || (sama < batas)){
			if (kuliah.get(i).get_ubah_ruang()){
				//ruang dapat diubah
				r_old = kuliah.get(i).get_ruang();
				slot_old = kuliah.get(i).get_slot();
				do{
                    int xi = rnd.nextInt(file.j_ruang);
                    r = file.ruang[xi];
                } while ((r.get_mulai().get_jam()>kuliah.get(i).get_selesai().get_jam())||
                (r.get_selesai().get_jam()<kuliah.get(i).get_mulai().get_jam()+kuliah.get(i).get_sks())||
                (!(day.is_intersect(r.get_hari(),kuliah.get(i).get_hari()))));
                file.add_mk_rand(kuliah.get(i),r);
				konflik = file.hitung_konflik();
				if (konflik > file.konflik_now){
					//undo
					kuliah.get(i).set_ruang_ref(r_old);
					kuliah.get(i).slot_waktu = slot_old;
					sama++;
				} else if (konflik == file.konflik_now){
					//ubah, tapi sama ditambah
					sama++;
				} else{
					//tidak diubah, konflik_now diperbarui
					file.konflik_now = konflik;
				}
			} else {
				//ruang tidak dapat diubah, hanya merandom jam
				slot_old = kuliah.get(i).get_slot();
				r = kuliah.get(i).get_ruang();
                file.add_mk_rand(kuliah.get(i),r);
				konflik = file.hitung_konflik();
				if (konflik > file.konflik_now){
					//undo
					kuliah.get(i).slot_waktu = slot_old;
					sama++;
				} else if (konflik == file.konflik_now){
					//ubah, tapi sama ditambah
					sama++;
				} else{
					//tidak diubah, konflik_now diperbarui
					file.konflik_now = konflik;
				}
			}
			i++;
			if (i>=kuliah.size()){
				//reset i
				i=0;
			}
		}
	}
}