/*Keterangan

untuk ruangan => xx.ruangke[0].nama , xx.ruangke[0],mulai , dst
untuk jadwal => zz.jadwalke[0].kuliah, zz.jadwalke[1].ruangkelas ,dst
 
  
*/




import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class waktu{
	int jam;
	int menit;
}

class day{
	int banyak;
	int harike[] = new int[10];
}

 
class ruangan {
	String nama;
	waktu mulai;
	waktu selesai;
	day hari;
}

class ruang{
	ruangan ruangke[] = new ruangan[100] ;
}



/*
Ruangan
Nama; Jam Mulai; Jam Selesai; Hari

Ruangan
7602;07.00;14.00;1,2,3,4,5
7603;07.00;14.00;1,3,5
7610;09.00;12.00;1,2,3,4,5
Labdas2;10.00;14.00;2,4
*/


class penjadwalan{
	String kuliah;
	String ruangkelas;
	waktu jammulai;
	waktu jamselesai;
	int durasi;
	day hari;
}

class jadwal{
	penjadwalan jadwalke[] = new penjadwalan[100];
}

/*
Jadwal
Nama kegiatan; Ruangan; Jam Mulai; Jam Selesai; Durasi(Jam); Hari

Hari : Senin (1), Selasa (2), Rabu (3), Kamis (4), Jumat (5)

*/

public class bacafile {


	public static int konversi(char str){
		
		int angka=0;
		
        switch (str) {
            case '0' : angka= 0; break;
            case '1' : angka= 1; break;
            case '2' : angka= 2; break;
            case '3' : angka= 3; break;
            case '4' : angka= 4; break;
            case '5' : angka= 5; break;
            case '6' : angka= 6; break;
            case '7' : angka= 7; break;
            case '8' : angka= 8; break;
            case '9' : angka= 9; break;
        }
        return angka;
	} 
	
	
	public static waktu konversikewaktu(String str){
		waktu wk = new waktu();
		wk.jam=konversi(str.charAt(0))*10+konversi(str.charAt(1));
		wk.menit=konversi(str.charAt(3))*10+konversi(str.charAt(4));
		return wk;
	}
	
    public static void main(String[] args) {
		
		String lokasi_file = "databaca.txt";


		int isjadwal = 0 ;
		int vv = 0;
		int ww = 0;
		try {
            FileReader fr = new FileReader(lokasi_file);
            BufferedReader br = new BufferedReader(fr);
            
            String xx;
			
			ruang oo = new ruang();
			jadwal uu = new jadwal();
			
			
            while ((xx = br.readLine()) != null){
				ruangan zz = new ruangan();
				penjadwalan cc = new penjadwalan();
				int awal =0;
				int i=0;
				
				if(xx.equals("Jadwal") || xx.equals("")){
					isjadwal=1;
				}
				else if(xx.equals("Ruangan")){
					isjadwal=0;
				}
				else{
					
					if (isjadwal==0){
						
						while (xx.charAt(i)!=';'){
							i++;
						}
						zz.nama=xx.substring(awal,i);
						awal=i+1;
						i++;
						
						while (xx.charAt(i)!=';'){
							i++;
						}
						
						String str=xx.substring(awal,i);
						waktu wk = new waktu();
						wk = konversikewaktu(str);
						zz.mulai=wk;
						awal=i+1;
						i++;
						
						while (xx.charAt(i)!=';'){
							i++;
						}
						str=xx.substring(awal,i);
						wk = konversikewaktu(str);
						zz.selesai=wk;
						awal=i+1;
						i++;
						
						str=xx.substring(awal,xx.length());
						
						int banyakkoma=0;
						for (i = 0 ; i <str.length() ; i++){
							if(str.charAt(i)==','){
							banyakkoma++;
							}
						}
						day dd = new day();
						dd.banyak=banyakkoma+1;
						int jp = 0;
						for (i=0 ; i<banyakkoma+1 ; i++){
							dd.harike[i]=konversi(str.charAt(jp));
							jp=jp+2;
						}
						zz.hari=dd;
							oo.ruangke[vv]=zz;
							vv++;
							
							
					}
					else{
						
						while (xx.charAt(i)!=';'){
							i++;
						}
						cc.kuliah=xx.substring(awal,i);
						awal=i+1;
						i++;
						
						while (xx.charAt(i)!=';'){
							i++;
						}
						cc.ruangkelas=xx.substring(awal,i);
						awal=i+1;
						i++;
						
						while (xx.charAt(i)!=';'){
							i++;
						}
						
						String str=xx.substring(awal,i);
						waktu wk = new waktu();
						wk = konversikewaktu(str);
						cc.jammulai=wk;
						
						awal=i+1;
						i++;
						
						while (xx.charAt(i)!=';'){
							i++;
						}
						str=xx.substring(awal,i);
						wk=konversikewaktu(str);
						cc.jamselesai=wk;
						awal=i+1;
						i++;
						
						while (xx.charAt(i)!=';'){
							i++;
						}
						str=xx.substring(awal,i);
						cc.durasi=konversi(str.charAt(0));
						awal=i+1;
						i++;
						
						str=xx.substring(awal,xx.length());
						
						int banyakkoma=0;
						for (i = 0 ; i <str.length() ; i++){
							if(str.charAt(i)==','){
							banyakkoma++;
							}
						}
						
						day dd = new day();
						dd.banyak=banyakkoma+1;
						int jp = 0;
						for (i=0 ; i<banyakkoma+1 ; i++){
							dd.harike[i]=konversi(str.charAt(jp));
							jp=jp+2;
						}
						cc.hari=dd;
						
						
							uu.jadwalke[ww]=cc;
							ww++;
						
						
						
					}
				}
				   
			}
			
			System.out.println("Untuk Ruangan ");
			System.out.println();
			for (int ii = 0 ; ii < vv ; ii++){
			
					System.out.println("Ruangan ke "+ii);
					
					System.out.println("Nama		:" + oo.ruangke[ii].nama);
					System.out.println("Mulai		:" + oo.ruangke[ii].mulai.jam+"."+oo.ruangke[ii].mulai.menit);
					System.out.println("Selesai		:" + oo.ruangke[ii].selesai.jam+"."+oo.ruangke[ii].selesai.menit);
					System.out.println("Banyak hari	:" + oo.ruangke[ii].hari.banyak);
					for(int hari : oo.ruangke[ii].hari.harike){
						System.out.print(hari+" ");
					}
					System.out.println();
			}
			System.out.println();
			System.out.println("Untuk Penjadwalan ");
			System.out.println();
			for (int ii = 0 ; ii < ww ; ii++){
			
					System.out.println("Penjadwalan ke "+ii);
					
					System.out.println("Kuliah		:" + uu.jadwalke[ii].kuliah);
					System.out.println("Ruang		:" + uu.jadwalke[ii].ruangkelas);
					System.out.println("Mulai		:" + uu.jadwalke[ii].jammulai.jam+"."+uu.jadwalke[ii].jammulai.menit);
					System.out.println("Selesai		:" + uu.jadwalke[ii].jamselesai.jam+"."+uu.jadwalke[ii].jamselesai.menit);
					System.out.println("Durasi		:" + uu.jadwalke[ii].durasi);
					System.out.println("Banyak hari	:" + uu.jadwalke[ii].hari.banyak);
					for(int hari : oo.ruangke[ii].hari.harike){
						System.out.print(hari+" ");
					}
					System.out.println();
			}
        } 
        catch (FileNotFoundException fnfe) {
            fnfe.getMessage();
        } 
        catch (IOException ioe) {
            ioe.getMessage();
        }
        
    }

}
