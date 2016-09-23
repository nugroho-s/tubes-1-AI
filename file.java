import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class file {

    public static String lokasi_file;
	public static int j_ruang = 0;
	public static ruangan[] ruang = new ruangan[10];
	public static int j_kuliah = 0;
	public static mataKuliah[] kuliah = new mataKuliah[10];
	public static void main(String args[]) {
		set_file("databaca.txt");
		baca_file();
		for (int i = 0; i < j_ruang; i++) {
			System.out.println(ruang[i]);
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < j_kuliah; i++) {
			System.out.println(kuliah[i]);
			System.out.println();
		}
	}

    public static void set_file(String x) {
        lokasi_file = x;
    }

    public static void set_ruangan(String x) {
        String[] temp = x.split(";");
        waktu mulai = waktu.konversikewaktu(temp[1]);
        waktu selesai = waktu.konversikewaktu(temp[2]);
        String[] daySplit = temp[3].split(",");
        day hari = new day();
        for (int i = 0; i < daySplit.length; i++) {
            hari.set_hari(Integer.parseInt(daySplit[i]), true);
        }
        ruang[j_ruang++] = new ruangan(temp[0], mulai, selesai, hari);
    }

    public static void set_kuliah(String x) {
        String[] temp = x.split(";");
        waktu mulai = waktu.konversikewaktu(temp[2]);
        waktu selesai = waktu.konversikewaktu(temp[3]);
        String[] daySplit = temp[5].split(",");
        day hari = new day();
        for (int i = 0; i < daySplit.length; i++) {
            hari.set_hari(Integer.parseInt(daySplit[i]), true);
        }
        kuliah[j_kuliah++] = new mataKuliah(temp[0], temp[1], mulai, selesai, Integer.parseInt(temp[4]), hari);
    }

    public static void baca_file() {
        try {
            FileReader fr = new FileReader(lokasi_file);
            BufferedReader br = new BufferedReader(fr);
            String xx;
            br.readLine();
            while (!((xx = br.readLine()).equals(""))) {
                set_ruangan(xx);
            }
            br.readLine();
            while ((xx = br.readLine()) != null) {
                set_kuliah(xx);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

