package ls;

import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;

public class annealing {
	
	// attributes
	private static Random rnd = new Random();
	
	// methods
	private static double hitung_peluang(int current_conflict, int new_conflict, double temperature) {
		
		// jika solusi yang baru lebih baik, maka diterima
		if (new_conflict < current_conflict) {
			return 1.0;
		}
		
		// jika solusi yang baru tidak lebih baik, hitung peluang nya (acceptance probability)
		return Math.exp((current_conflict - new_conflict) / temperature);
	}
	
	public static void start(ArrayList<mataKuliah> kuliah) {
		
		// nilai suhu awal
		double T = 1.0;
		
		// nilai suhu akhir
		double T_min = 0.00001;

		// nilai pengali untuk mendapatkan suhu berikutnya
		double alpha = 0.9;
		
		// nilai acceptance probability
		double peluang;
		
		int i;
		int xi;
		int slot_old;
		int konflik;
		
		ruangan r;
		ruangan r_old;
		
		while (T > T_min) {
			
			i = 0;
			
			while (i < kuliah.size()) {
			
				if (kuliah.get(i).get_ubah_ruang()) {
					// ruang dapat diubah
					
					r_old = kuliah.get(i).get_ruang();
					slot_old = kuliah.get(i).get_slot();
					
					// random ruangan
					do {
						xi = rnd.nextInt(file.j_ruang);
						r = file.ruang[xi];
					} while (
						(r.get_mulai().get_jam()>kuliah.get(i).get_selesai().get_jam()) ||
						(r.get_selesai().get_jam()<kuliah.get(i).get_mulai().get_jam()+kuliah.get(i).get_sks()) ||
						(!(day.is_intersect(r.get_hari(),kuliah.get(i).get_hari())))
					);
					
					// random hari dan waktu
					file.add_mk_rand(kuliah.get(i),r);
						
					// menghitung konflik saat ini
					konflik = file.hitung_konflik();
					
					// menghitung nilai peluang (acceptance probability)
					peluang = hitung_peluang(file.konflik_now, konflik, T);
				
					if (peluang > Math.random()) {
						// jika peluang lebih besar dari angka random (0 <= random < 1), maka solusi diterima
						file.konflik_now = konflik;
					} else {
						kuliah.get(i).set_ruang_ref(r_old);
						kuliah.get(i).slot_waktu = slot_old;
					}
					
				} else {

					// ruang tidak dapat diubah, hanya merandom hari dan waktu
					
					r_old = kuliah.get(i).get_ruang();
					slot_old = kuliah.get(i).get_slot();
					
					r = kuliah.get(i).get_ruang();
					
					// random hari dan waktu
					file.add_mk_rand(kuliah.get(i),r);
					
					// menghitung konflik saat ini
					konflik = file.hitung_konflik();

					// menghitung nilai peluang (acceptance probability)
					peluang = hitung_peluang(file.konflik_now, konflik, T);
				
						if (peluang > Math.random()) {
							// jika peluang lebih besar dari angka random (0 <= random < 1), maka solusi diterima
							file.konflik_now = konflik;
						} else {
							kuliah.get(i).set_ruang_ref(r_old);
							kuliah.get(i).slot_waktu = slot_old;
						}
				
				}
				
				// lanjut ke kuliah berikutnya
				i++;
				
			}
			
			// mengurangi temperature
			T = T * alpha;
			
		}
		
	}
	
}
