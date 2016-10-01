package mock;

import java.util.ArrayList;
import java.util.List;

import ls.file;
import ls.ruangan;
import ls.mataKuliah;

public class tableResult {

	public class tableCell {
		int num_matkul = 0;
		ArrayList<mataKuliah> mk = new ArrayList<mataKuliah>();
	
		public int get_num_matkul() {
			return num_matkul;
		}
		
		public void add_num_matkul() {
			num_matkul++;
		}
		
		public ArrayList<mataKuliah> get_matkul() {
			return mk;
		}
		
		public void add_matkul(mataKuliah matkul) {
			mk.add(matkul);
		}
	}
	
	public int row;
	public int column;
	public tableCell table[][] = new tableCell[11][5];
	
	public tableResult() {
		
		initTable();
		
		// initial room is the first room
		setTableForRoom(file.ruang[0]);
		
	}
	
	public void initTable() {
		
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 5;j ++) {
				table[i][j] = new tableCell();
			}
		}
	}
	
	public void setTableForRoom(ruangan r) {
		// get the kuliah occupying the room r
		for (int i = 0; i < file.kuliah.size(); i++) {
			
			if (file.kuliah.get(i).get_ruang() == r){
				
				if (!file.kuliah.get(i).get_is_allocated()) {
					file.kuliah.get(i).set_is_allocated(true);
					
					int hari = file.kuliah.get(i).get_slot_hari();
					int waktu = file.kuliah.get(i).get_slot_jam();
					
					for (int j = 0; j < file.kuliah.get(i).get_sks(); j++) {
						// each cell tells how many mata kuliah residing within it
						table[waktu+j-7][hari-1].add_num_matkul();
						
						// add mata kuliah into the table
						table[waktu+j-7][hari-1].add_matkul(file.kuliah.get(i));
					}
					
					// set table model attributes for this mata kuliah
					file.kuliah.get(i).set_row(waktu-7);
					file.kuliah.get(i).set_column(hari-1);
				}
				
			}
		}
		
		
	}
	
}
