import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class waktu {

    int jam;
    int menit;

    public int get_jam() {
        return jam;
    }

    public int get_menit() {
        return menit;
    }

    public void set_jam(int j) {
        jam = j;
    }

    public void set_menit(int m) {
        menit = m;
    }

    public static waktu konversikewaktu(String str) {
        String[] temp = str.split("\\.");
        waktu wk = new waktu();
        wk.jam = Integer.parseInt(temp[0]);
        wk.menit = Integer.parseInt(temp[1]);
        return wk;
    }

    public String toString() {
        return ((String.format("%02d", get_jam())) + "." + (String.format("%02d", get_menit())));
    }
}

class day {

    boolean hari[] = new boolean[6]; //hari dienumerasi sebagai indeks 1-5

    public String toString() {
        String os = "";
        boolean ada = false;
        for (int i = 1; i < 6; i++) {
            if (hari[i] == true) {
                if (ada) {
                    os = os.concat(",");
                }
                os = os.concat(Integer.toString(i));
                ada = true;
            }
        }
        return os;
    }

    public boolean isOnDay(int x) {
        return (hari[x]);
    }

    public day() {
        for (int i = 0; i < 6; i++) {
            hari[i] = false;
        }
    }

    public boolean get_hari_ke(int x) {
        return hari[x];
    }

    public void set_hari(int i, boolean s) {
        hari[i] = s;
    }
}

class ruangan {

    String nama;
    waktu mulai;
    waktu selesai;
    day hari;

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

class mataKuliah {

    String nama;
    String ruang;
    waktu mulai;
    waktu selesai;
    int sks;
    day hari;

    public mataKuliah(String n, String r, waktu m, waktu s, int sk, day h) {
        nama = n;
        ruang = r;
        mulai = m;
        selesai = s;
        hari = h;
        sks = sk;
    }

    public String toString() {
        return ("Nama: " + get_nama() + "\nRuangan: " + get_ruang() + "\nWaktu mulai: " + get_mulai()
                + "\nWaktu selesai: " + get_selesai() + "\nJumlah SKS: " + get_sks() + "\nhari: " + get_hari());
    }

    public String get_nama() {
        return nama;
    }

    public void set_nama(String n) {
        nama = n;
    }

    public String get_ruang() {
        return ruang;
    }

    public void set_ruang(String x) {
        ruang = x;
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

    public int get_sks() {
        return sks;
    }

    public day get_hari() {
        return hari;
    }

    public void set_hari(day h) {
        hari = h;
    }
}

class file {

    public static String lokasi_file;

    public static void set_file(String x) {
        lokasi_file = x;
    }

    public static void set_ruangan(String x) {
        String[] temp = x.split(";");
        waktu mulai = waktu.konversikewaktu(temp[1]);
        waktu selesai = waktu.konversikewaktu(temp[2]);
        String[] day = temp[3].split(",");
        day hari = new day();
        for (int i = 0; i < day.length; i++) {
            hari.hari[Integer.parseInt(day[i])] = true;
        }
        main.ruang[main.j_ruang++] = new ruangan(temp[0], mulai, selesai, hari);
    }

    public static void set_kuliah(String x) {
        String[] temp = x.split(";");
        waktu mulai = waktu.konversikewaktu(temp[2]);
        waktu selesai = waktu.konversikewaktu(temp[3]);
        String[] day = temp[5].split(",");
        day hari = new day();
        for (String item : day) {
            hari.hari[Integer.parseInt(item)] = true;
        }
        main.kuliah[main.j_kuliah++] = new mataKuliah(temp[0], temp[1], mulai, selesai, Integer.parseInt(temp[4]), hari);
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

class main {

    public static int j_ruang = 0;
    public static ruangan[] ruang = new ruangan[10];
    public static int j_kuliah = 0;
    public static mataKuliah[] kuliah = new mataKuliah[10];

    public static void main(String[] args) {
        file.set_file("databaca.txt");
        file.baca_file();
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
}
