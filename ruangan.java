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
	ArrayList<slot> jadwal = new ArrayList<slot>();
    private static Random rnd = new Random();

    public ruangan(String n, waktu m, waktu s, day h) {
        nama = n;
        mulai = m;
        selesai = s;
        hari = h;
    }

	public void sortJadwal() {
			Collections.sort(jadwal, new Comparator<slot>(){
			@Override
			public int compare(slot o1, slot o2){
				return o1.get_slot() - o2.get_slot(); //sort menaik
			}
		});
	}
    public String toString() {
        return ("Nama: " + get_nama() + "\n" + "Waktu mulai: " + get_mulai() + "\n"
                + "Waktu selesai: " + get_selesai() + "\nhari: " + get_hari().toString());
    }
    
    //add mata kuliah ke arraylist jadwal
    //i.s. waktu sudah benar
    public void add_mk(mataKuliah mk,int h, int j){
        jadwal.add(new slot(mk,h,j));
    }
    
    //add mata kuliah dengan random
    public void add_mk_rand(mataKuliah mk){
        //random hari 1-5
        int h = rnd.nextInt(5);
        h+=1;
        while (!((get_hari().isOnDay(h)) && (mk.get_hari().isOnDay(h)))){
            h = rnd.nextInt(5);
            h+=1;
        }
        //random jam 7 - 17
        int range = mk.get_selesai().get_jam() - mk.get_sks();
        range = range-mk.get_mulai().get_jam()+1;
        int jam = rnd.nextInt(range);
        jam+=mk.get_mulai().get_jam();
        while ((jam+mk.get_sks()>get_selesai().get_jam())||(jam<get_mulai().get_jam())){
            jam = rnd.nextInt(range);
            jam+=mk.get_mulai().get_jam();
        }
        add_mk(mk,h,jam);
    }
    
    public void print_jadwal(){
        System.out.println(get_nama());
        for (int x=0; x<jadwal.size(); x++){
            System.out.println(jadwal.get(x));
        }
    }
	
	//hitung konflik dari jadwal ruangan, harus sort terlebih dulu
	public int hitung_konflik(){
		sortJadwal();
		System.out.println("sesudah sort: ");
		print_jadwal();
		System.out.println("=========");
		int konflik = 0;
		for (int x=0; x<jadwal.size(); x++){
			int kode = jadwal.get(x).get_slot();
			int sks = jadwal.get(x).get_mk().get_sks();
			boolean debug = (jadwal.size() == 2);
			int y = x+1;
			int sama = 0;
			if (y >= jadwal.size())
				continue;
			while ((jadwal.get(y).get_slot() < kode+sks) && (Math.abs(kode-jadwal.get(y).get_slot()) < 50)) {
				sama++;
				y++;
				if (y >= jadwal.size())
					break;
			}
			konflik+=sama;
		}
		return konflik;
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
    
    public ArrayList<slot> get_jadwal(){
        return jadwal;
    }
}
