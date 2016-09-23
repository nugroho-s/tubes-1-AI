public class mataKuliah {

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
