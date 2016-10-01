package ls;

public class slot{
	mataKuliah mk;
	int slot_waktu;
	
	public slot (mataKuliah _mk, int _h, int _j){
		mk = _mk;
		slot_waktu = _h*100+_j;
	}
	
	int get_hari(){
		return slot_waktu/100;
	}
	int get_jam(){
		return slot_waktu%100;
	}
	
	int get_slot(){
		return slot_waktu;
	}
	
	public mataKuliah get_mk(){
		return mk;
	}
	
	public String toString(){
		return (mk.get_nama()+": hari "+get_hari()+" jam "+get_jam());
	}
}
