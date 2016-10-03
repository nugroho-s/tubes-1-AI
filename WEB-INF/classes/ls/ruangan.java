package ls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
import java.util.Random;

public class ruangan {

    String nama;
    waktu mulai;
    waktu selesai;
    day hari;
	public double persentase;
	//ArrayList<slot> jadwal = new ArrayList<slot>();
    private static Random rnd = new Random();

    public ruangan(String n, waktu m, waktu s, day h) {
        nama = n;
        mulai = m;
        selesai = s;
        hari = h;
    }

	public void hitung_persentase(){
		double slot_tersedia = (double)(get_selesai().get_jam() - get_mulai().get_jam())*5;
		double slot_terpakai = 0.0;
		for (int i=0;i<file.kuliah.size();i++){
			if (file.kuliah.get(i).get_ruang() == this){
				slot_terpakai+=file.kuliah.get(i).get_sks();
			}
		}
		persentase = (double)(slot_terpakai/slot_tersedia);
		persentase *= 100;
	}
	
    public String toString() {
        return ("Nama: " + get_nama() + "\n" + "Waktu mulai: " + get_mulai() + "\n"
                + "Waktu selesai: " + get_selesai() + "\nhari: " + get_hari().toString());
    }

    public String get_nama() {
        return nama;
    }

    public void set_nama(String n) {
        nama = n;
    }

    public waktu get_mulai() {
        return mulai;
    }

    public void set_mulai(waktu m) {
        mulai = m;
    }

    public waktu get_selesai() {
        return selesai;
    }

    public void set_selesai(waktu m) {
        selesai = m;
    }

    public day get_hari() {
        return hari;
    }

    public void set_hari(day h) {
        hari = h;
    }
}
