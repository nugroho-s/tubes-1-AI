import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class file {
    private static Random rnd = new Random();
    public static String lokasi_file;
	public static int j_ruang = 0;
	public static ruangan[] ruang = new ruangan[10];
	public static int j_kuliah = 0;
	public static mataKuliah[] kuliah = new mataKuliah[10];
	public static void main(String args[]) {
		set_file("databaca.txt");
		baca_file();
		/* Validasi baca file
        for (int i = 0; i < j_ruang; i++) {
			System.out.println(ruang[i]);
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < j_kuliah; i++) {
			System.out.println(kuliah[i]);
			System.out.println();
		}
        System.out.println();*/
        System.out.println("=======");
        System.out.println("Jadwal");
        inisialisasi_random();
        for (int i = 0; i < j_ruang; i++) {
            ruang[i].print_jadwal();
            System.out.println();
        }
		System.out.println("=======");
        System.out.println("Konflik = "+hitung_konflik());
	}
	
	public static int hitung_konflik(){
		int konflik = 0;
		for (int i=0;i<j_ruang;i++){
			ruangan r = ruang[i];
			konflik+=r.hitung_konflik();
		}
		return konflik;
	}
    
    public static void inisialisasi_random(){
        for (int i = 0; i < j_kuliah; i++) {
            ruangan r;
            if (kuliah[i].get_ruang()!=null){
                //semua yg memiliki kelas harus dimasukkan
                r = kuliah[i].get_ruang();
                r.add_mk_rand(kuliah[i]);
            }
            else{
                //harus dicek apakah kelas bisa menampung mata kuliah
                do{
                    int xi = rnd.nextInt(j_ruang);
                    r = ruang[xi];
                } while ((r.get_mulai().get_jam()>kuliah[i].get_selesai().get_jam())||
                (r.get_selesai().get_jam()<kuliah[i].get_mulai().get_jam()+kuliah[i].get_sks())||
                (!(day.is_intersect(r.get_hari(),kuliah[i].get_hari()))));
                r.add_mk_rand(kuliah[i]);
            }
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

