package ls;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
import java.util.Random;

public class file {
    private static Random rnd = new Random();
    public static String lokasi_file;
	public static int j_ruang = 0;
	public static ruangan[] ruang = new ruangan[50];
	public static int j_kuliah = 0;
	public static ArrayList<mataKuliah> kuliah = new ArrayList<mataKuliah>();
	public static int konflik_now = 0;
	public static int banyakslot = 0;
	public static int slotterpakai = 0;
	
	public static void main(String args[]) {
		
		// assign file yang berisi test case
		set_file("testcase.txt");
		
		// memulai pembacaan file test case
		baca_file();
        
		// menampilkan hasil penjadwalan secara random
		System.out.println("=======");
        System.out.println("Jadwal");
        inisialisasi_random();
        for (int i = 0; i < kuliah.size(); i++) {
            kuliah.get(i).print_jadwal();
        }
		System.out.println("=======");
		
		// menghitung konflik untuk penjadwalan awal
		konflik_now = hitung_konflik();
        System.out.println("Konflik = "+konflik_now);
		System.out.println("Selesai Inisialisasi");
		
		// memulai menjalankan algoritma
		if (args.length>=1) {
			if ((args[0]).equals("hill")) {

				// menggunakan algoritma Hill Climbing
				System.out.println();
				
				hill.set_batas(5*11*j_ruang);
				hill.start(kuliah);
				
				System.out.println("Selesai HILL CLIMBING");
				System.out.println("=======");
				for (int i = 0; i < kuliah.size(); i++) {
					kuliah.get(i).print_jadwal();
				}
				System.out.println("=======");
				
				// menghitung konflik akhir
				konflik_now = hitung_konflik();
				
				System.out.println("Konflik = "+konflik_now);
			} else if ((args[0]).equals("annealing")) {
				
				// menggunakan algoritma Simulated Annealing
				System.out.println();
				
				annealing.start(kuliah);
				
				// menghitung konflik akhir
				konflik_now = hitung_konflik();
				
				// menggunakan algoritma Hill Climbing untuk memastikan bahwa solusi didapatkan
				if (konflik_now != 0) {
					
					System.out.println("Memulai hill climbing setelah annealing");
					
					hill.set_batas(5*11*j_ruang);
					hill.start(kuliah);
					
					// menghitung konflik akhir
					konflik_now = hitung_konflik();
				}
				
				System.out.println("Selesai SIMULATED ANNEALING");
				System.out.println("=======");
				for (int i = 0; i < kuliah.size(); i++) {
					kuliah.get(i).print_jadwal();
				}
				System.out.println("=======");
				
				System.out.println("Konflik = "+konflik_now);
			}
			else if ((args[0]).equals("genetic")) {
				genetic g = new genetic();
				kuliah = g.get();
				System.out.println("Selesai GENETIC");
				System.out.println("=======");
				for (int i = 0; i < kuliah.size(); i++) {
					kuliah.get(i).print_jadwal();
				}
				System.out.println("=======");
				System.out.println("Konflik = "+g.get_konflik_now());
			}
		}
		
		pewarnaanjadwal();
		pindahmanual();
	}
	
	public static void hitung_persentase(){
		for (int i=0;i<j_ruang;i++){
			ruang[i].hitung_persentase();
		}
	}
	
	
	public void sort_by_id() {
			Collections.sort(kuliah, new Comparator<mataKuliah>(){
			@Override
			public int compare(mataKuliah o1, mataKuliah o2){
				return o1.get_id() - o2.get_id(); //sort menaik
			}
		});
	}
    
    public static void inisialisasi_random(){
        for (int i = 0; i < j_kuliah; i++) {
            ruangan r;
            if (kuliah.get(i).get_ruang()!=null){
                //semua yg memiliki kelas harus dimasukkan
                r = kuliah.get(i).get_ruang();
                add_mk_rand(kuliah.get(i),r);
            }
            else{
                //harus dicek apakah kelas bisa menampung mata kuliah
                do{
                    int xi = rnd.nextInt(j_ruang);
                    r = ruang[xi];
                } while ((r.get_mulai().get_jam()>kuliah.get(i).get_selesai().get_jam())||
                (r.get_selesai().get_jam()<kuliah.get(i).get_mulai().get_jam()+kuliah.get(i).get_sks())||
                (!(day.is_intersect(r.get_hari(),kuliah.get(i).get_hari()))));
                add_mk_rand(kuliah.get(i),r);
            }
        }
    }
	
	public static void copy(ArrayList<mataKuliah> mk){
		for (int i=kuliah.size()-1; i>=0; i--){
			mk.add(kuliah.get(i).clone());
		}
	}
	
	public static void inisialisasi_random(ArrayList<mataKuliah> k){
        for (int i = 0; i < j_kuliah; i++) {
            ruangan r;
            if (k.get(i).get_ruang()!=null){
                //semua yg memiliki kelas harus dimasukkan
                r = k.get(i).get_ruang();
                add_mk_rand(k.get(i),r);
            }
            else{
                //harus dicek apakah kelas bisa menampung mata kuliah
                do{
                    int xi = rnd.nextInt(j_ruang);
                    r = ruang[xi];
                } while ((r.get_mulai().get_jam()>k.get(i).get_selesai().get_jam())||
                (r.get_selesai().get_jam()<k.get(i).get_mulai().get_jam()+k.get(i).get_sks())||
                (!(day.is_intersect(r.get_hari(),k.get(i).get_hari()))));
                add_mk_rand(k.get(i),r);
            }
        }
    }
	
	public static void add_mk_random_r(mataKuliah k){
		ruangan r;
		if (k.get_ruang()!=null){
			//semua yg memiliki kelas harus dimasukkan
			r = k.get_ruang();
			add_mk_rand(k,r);
		}
		else{
			//harus dicek apakah kelas bisa menampung mata kuliah
			do{
				int xi = rnd.nextInt(j_ruang);
				r = ruang[xi];
			} while ((r.get_mulai().get_jam()>k.get_selesai().get_jam())||
			(r.get_selesai().get_jam()<k.get_mulai().get_jam()+k.get_sks())||
			(!(day.is_intersect(r.get_hari(),k.get_hari()))));
			add_mk_rand(k,r);
		}
    }
	//add mata kuliah ke arraylist jadwal
    //i.s. waktu sudah benar
    public static void add_mk(mataKuliah mk, int h, int j, ruangan r){
        mk.set_ruang_ref(r);
		mk.set_slot(h,j);
    }
	
	//add mata kuliah dengan random
    public static void add_mk_rand(mataKuliah mk, ruangan r){
        //random hari 1-5
        int h = rnd.nextInt(5);
        h+=1;
        int z = 0;
        int iz = 1;
        int max_ = 0;
        while (!((r.get_hari().isOnDay(h)) && (mk.get_hari().isOnDay(h)))){
			z++;
			max_ ++;
			if (z > 200 && mk.Lruang.size() > iz) 
			{
				z = 0;
				r = mk.Lruang.get(iz);
				mk.set_ruang_ref(r);
				iz++;
			}
			if (max_ >1000) {
					break;
				}
            h = rnd.nextInt(5);
            h+=1;
        }
        //random jam 7 - 17
        int range = mk.get_selesai().get_jam() - mk.get_sks();
        range = range-mk.get_mulai().get_jam()+1;
        int jam = rnd.nextInt(range);
        jam+=mk.get_mulai().get_jam();
        z = 0;
        iz = 1;
        max_ = 0;
        while ((jam+mk.get_sks()>r.get_selesai().get_jam())||(jam<r.get_mulai().get_jam())){
			z++;
			max_++;
			if (z > 200 && mk.Lruang.size() > iz) {
				z = 0;
				r = mk.Lruang.get(iz);
				mk.set_ruang_ref(r);
				iz++;
				
			}
			if (max_ >1000) {
					break;
			}
            jam = rnd.nextInt(range);
            jam+=mk.get_mulai().get_jam();
        }
        add_mk(mk,h,jam,r);
    }
	
	public static int hitung_konflik(){
		int konflik = 0;
		for (int x=0; x<kuliah.size(); x++){
			int kode = kuliah.get(x).get_slot();
			int sks = kuliah.get(x).get_sks();
			
			while (sks>0){
				for (int y=x+1; y<kuliah.size(); y++){
					if (kuliah.get(x).get_ruang().get_nama() == kuliah.get(y).get_ruang().get_nama()){
						if (Math.abs(kode-kuliah.get(y).get_slot()) < 50){
							//hari sama
							int kodey = kuliah.get(y).get_slot();
							if (kodey == kode)
								konflik++;
							else if (kodey < kode) {
								if (kodey+kuliah.get(y).get_sks() > kode)
									konflik++;
							}
							else{
								if (kode+sks > kodey)
									konflik++;
							}
						}
					}
				}
				sks--;
				kode++;
			}
		}
		System.out.println(konflik);
		return konflik;
	}
	
	public static int hitung_konflik(ArrayList<mataKuliah> k){
		int konflik = 0;
		for (int x=0; x<k.size(); x++){
			int kode = k.get(x).get_slot();
			int sks = k.get(x).get_sks();
			
			for (int y=x+1; y<k.size(); y++){
				if (k.get(x).get_ruang() == k.get(y).get_ruang()){
					if (Math.abs(kode-k.get(y).get_slot()) < 50){
						//hari sama
						int kodey = k.get(y).get_slot();
						if (kodey == kode)
							konflik++;
						else if (kodey < kode) {
							if (kodey+k.get(y).get_sks() > kode)
								konflik++;
						}
						else{
							if (kode+sks > kodey)
								konflik++;
						}
					}
				}
			}
		}
		return konflik;
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
        file.banyakslot = file.banyakslot + (selesai.jam - mulai.jam) * hari.jumlah_hari();
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
        kuliah.add(new mataKuliah(j_kuliah++,temp[0], temp[1], mulai, selesai, Integer.parseInt(temp[4]), hari));
    }

    public static void baca_file() {
		j_kuliah = 0;
		j_ruang = 0;
		kuliah.clear();
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
    
    
    public static void pindahmanual(){
		Scanner input = new Scanner(System.in);
		
		System.out.print("Apkakah ingin dilakukan pindah manual jadwal (Y/N) : ");
		String cr = input.nextLine();
		
		if (cr.equals("Y")){
			
			System.out.println();
			System.out.println();

			System.out.print("Mata kuliah yang akan dipindahkan : ");
			String str = input.nextLine();
			System.out.print("Akan dipindahkan ke ruangan: ");
			String str2 = input.nextLine();
			System.out.print("Akan dipindahkan ke hari : ");
			int hari0 = input.nextInt();
			System.out.print("Akan dipindahkan ke jam  : ");
			int waktu0 = input.nextInt();
			
			
			
			for (int x=0; x<kuliah.size(); x++){
					if (str.equals(kuliah.get(x).get_nama())){
						
						waktu ww = new waktu();
						ww.set_jam(waktu0);
						ww.set_menit(0);
						waktu vv = new waktu();
						vv.set_jam(waktu0+kuliah.get(x).sks);
						vv.set_menit(0);
						
						kuliah.get(x).mulai = ww;
						kuliah.get(x).selesai = vv;
						
						for (int i = 0; i < 6; i++) {
							kuliah.get(x).hari.hari[i] = false;
						}
						
						
						
						kuliah.get(x).hari.hari[hari0] = true;
						
						for (int jj=0; jj<j_ruang; jj++){
							if (str2.equals(ruang[jj].nama)){
								kuliah.get(x).ruang=ruang[jj];
							}
						}
						kuliah.get(x).set_slot(hari0,waktu0);	
						for (int i = 0; i < kuliah.size(); i++) {
							kuliah.get(i).print_jadwal();
						}
					}
			}
		}
	}
    
    // Inisialisasi warna
    public static void setwarna(){
		for (int i=0; i<kuliah.size(); i++){
			kuliah.get(i).warna=0;	// 0 berarti tidak berwarna
		}
	}
    
    public static boolean warnasama(mataKuliah mk1){
		
		int i = 0;
		boolean bool1=false;
		
		while((i<kuliah.size()) && (!bool1)){
			if ((mk1!=kuliah.get(i)) && (jadwalbersinggungan(mk1,kuliah.get(i))) && (mk1.warna==kuliah.get(i).warna) ){
				bool1=true;
			}
			i++;
		}
		
	
		
		return bool1;
	}
    
    public static boolean jadwalbersinggungan(mataKuliah mk1,mataKuliah mk2){
		boolean ada;
		
		ada=false;
		
		int i = mk1.slot_waktu;
		int mm = mk1.slot_waktu;
		int j = mk2.slot_waktu;
		int nn = mk2.slot_waktu;
		
		
		while ((i<=mm+mk1.sks) && !(ada)){
			while ((j<=nn+mk2.sks) && !(ada)){
				if((i+1==j) || (i==j+1) || (i+100==j) || (i==100+j) || (i==j)){
					//System.out.println("Ada sama");
					ada = true;
				}
				j++;
			}
			i++;
		}
		
		
		return (ada);
	}
	
	public static void pewarnaanjadwal(){
		
		setwarna();
		int a=1;
		for (int i=0; i<kuliah.size(); i++){
			kuliah.get(i).warna=a;
				a++;
		}		
	}
}


