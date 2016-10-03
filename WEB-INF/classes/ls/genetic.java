package ls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
public class genetic{
	private int N_POPULASI = 100;
	private int N_GENERASI = 5;
	ArrayList<mataKuliah> reference;
	ArrayList<ArrayList<mataKuliah>> populasi;
	
	public genetic() {
		populasi = new ArrayList<ArrayList<mataKuliah>>();
		reference = new ArrayList<mataKuliah>();
		file.copy(reference);
	}
	public ArrayList<mataKuliah> get() {
		init_populasi();
		//Loop sebanyak generasi
		int n=0;
		
		while (n<N_GENERASI && get_konflik_now()>0) {
			System.out.println("GENERASI " + n);
			//diseleksi, hanya diambil 100 terbaik
			seleksi();
			
			for(int i=0; i<populasi.size(); i++) {
				System.out.print(file.hitung_konflik(populasi.get(i)) + " ");
			}
			System.out.println();
			ArrayList<ArrayList<mataKuliah>> temp = new ArrayList<ArrayList<mataKuliah>>();
			
			//Proses kawin silang
			for(int i=0; i<populasi.size()-1; i++) {
				for(int j=i+1; j<populasi.size(); j++) {
					//Peluang terjadinya kawin silang
					if(Math.random() <  0.1) {
						temp.add(cross(populasi.get(i), populasi.get(j)));
					}
				}
			}
			
			//Generasi baru mengisi populasi
			populasi = temp;
			sort();
			n++;
		}
		return populasi.get(0);
	}
	
	private void init_populasi() {
		for(int i=0; i<N_POPULASI; i++) {
			ArrayList<mataKuliah> temp = new ArrayList<mataKuliah>();
			for(int j=reference.size()-1; j>=0; j--) {
				mataKuliah temp2 = reference.get(j).clone();
				temp.add(temp2);
			}
			file.inisialisasi_random(temp);
			populasi.add(temp);
			sort();
		}	
	}
	private void sort() {
			Collections.sort(populasi, new Comparator<ArrayList<mataKuliah>>(){
			@Override
			public int compare(ArrayList<mataKuliah> o1, ArrayList<mataKuliah> o2){
				return file.hitung_konflik(o1) - file.hitung_konflik(o2); //sort menaik
			}
		});
	}
	private ArrayList<mataKuliah> cross (ArrayList<mataKuliah> a1, ArrayList<mataKuliah> a2) {
		ArrayList<mataKuliah> temp = new ArrayList<mataKuliah>();
		for(int i=a1.size()-1; i>=0; i--) {
			//Peluang pertukaran kromosom
			mataKuliah mktemp1 = new mataKuliah();
			mktemp1 = a1.get(i).clone();
			mataKuliah mktemp2 = new mataKuliah();
			mktemp2 = a2.get(i).clone();
			if(Math.random() <  0.3) {
				file.add_mk_random_r(mktemp1);
				temp.add(mktemp1);
			}
			else if (Math.random() < 0.6) {
				temp.add(mktemp1);
			}
			else {
				temp.add(mktemp2);
			}
		}
		return temp;
	}
	
	private void seleksi() {
		if(populasi.size() > N_POPULASI) {
			populasi.subList(N_POPULASI-1, populasi.size()-1).clear();
		}
	}
	
	public int get_konflik_now() {
		return file.hitung_konflik(populasi.get(0));
	}
}
