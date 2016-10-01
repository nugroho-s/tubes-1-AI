package ls;

public class mataKuliah {

	int id;
    String nama;
    ruangan ruang;
    waktu mulai;
    waktu selesai;
    boolean ubah_ruang;
    int sks;
    day hari;
	public int slot_waktu; // 3 digit, digit 1 hari, digit 2-3 jam

	// table model attributes
	boolean is_allocated_on_table = false;
	int row;
	int column;
	
    public mataKuliah(int id, String n, String r, waktu m, waktu s, int sk, day h) {
		this.id = id;
        nama = n;
        ruang = search_ruang(r);
        if (r.equals("-"))
            ubah_ruang = true;
        else
            ubah_ruang = false;
        mulai = m;
        selesai = s;
        hari = h;
        sks = sk;
    }

    public String toString() {
        if (ruang == null){
            return ("Nama: " + get_nama() + "\nRuangan: -" + "\nWaktu mulai: " + get_mulai()
                + "\nWaktu selesai: " + get_selesai() + "\nJumlah SKS: " + get_sks() + "\nhari: " + get_hari());
        }
        return ("Nama: " + get_nama() + "\nRuangan: " + get_ruang().get_nama() + "\nWaktu mulai: " + get_mulai()
                + "\nWaktu selesai: " + get_selesai() + "\nJumlah SKS: " + get_sks() + "\nhari: " + get_hari());
    }
    
    public ruangan search_ruang(String r){
        if (r.equals("-")){
            return null;
        }
        for (int i = 0; i < file.j_ruang; i++) {
			if (r.equals((file.ruang[i]).nama)){
                return file.ruang[i];
            }
        }
        return null;
    }
	
	public void print_jadwal(){
		System.out.println("Nama: "+get_nama()+"\nRuangan: "+get_ruang().get_nama()
			+"\nHari: "+get_slot_hari() +"\nJam: "+get_slot_jam()+"\n");
	}
	
	public String jadwal_html(){
		return ("Nama: "+get_nama()+"<br/>\nRuangan: "+get_ruang().get_nama()
			+"<br/>\nHari: "+get_slot_hari() +"<br/>\nJam: "+get_slot_jam()+"<br/>\n");
	}
    
	
	
	// table model GETTER and SETTER
	public boolean get_is_allocated() {
		return is_allocated_on_table;
	}
	
	public void set_is_allocated(boolean is_allocated) {
		is_allocated_on_table = is_allocated;
	}
	
	public int get_row() {
		return row;
	}
	
	public void set_row(int val) {
		row = val;
	}
	
	public int get_column() {
		return column;
	}
	
	public void set_column(int val) {
		column = val;
	}
	
    public boolean get_ubah_ruang(){
        return ubah_ruang;
    }
	
	public int get_slot_hari(){
		return slot_waktu/100;
	}
	
	public int get_slot_jam(){
		return slot_waktu%100;
	}
	
	public int get_id(){
		return id;
	}

    public String get_nama() {
        return nama;
    }
	
	public int get_slot(){
		return slot_waktu;
	}
	
	public void set_slot(int _h, int _j){
		slot_waktu = _h*100+_j;
	}

    public void set_nama(String n) {
        nama = n;
    }
    
    public ruangan get_ruang(){
        return ruang;
    }

    public void set_ruang(String x) {
        ruang = search_ruang(x);
    }
	
	public void set_ruang_ref(ruangan r){
		ruang = r;
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
