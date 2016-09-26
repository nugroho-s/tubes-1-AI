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
	//ArrayList<slot> jadwal = new ArrayList<slot>();
    private static Random rnd = new Random();

    public ruangan(String n, waktu m, waktu s, day h) {
        nama = n;
        mulai = m;
        selesai = s;
        hari = h;
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
