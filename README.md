# tubes-1-AI

### Contoh test case

**Ruangan**<br />
7602;07.00;14.00;1,2,3,4,5<br />
7603;07.00;14.00;1,3,5<br />
7610;09.00;12.00;1,2,3,4,5<br />
Labdas2;10.00;14.00;2,4<br />

<br />

**Jadwal**<br />
IF2110;7602;07.00;12.00;4;1,2,3,4,5<br />
IF2130;-;10.00;16.00;3;3,4<br />
IF2150;-;09.00;13.00;2;1,3,5<br />
IF2170;7610;07.00;12.00;3;1,2,3,4,5<br />
IF3110;7602;07.00;09.00;2;1,2,3,4,5<br />
IF3130;-;07.00;12.00;2;3,4,5<br />
IF3170;7602;07.00;09.00;2;1,2,3,4,5<br />
IF3111;-;07.00;12.00;2;1,2,3,4,5<br />

### Classes

**ruangan.java**: class ruangan {
	// attributes
	nama : xxxxx
	jam_mulai :
	jam_akhir :
	ArrayList<slot> sl :

	// methods
	...
}

**slot.java**: class slot {
	// attributes
	key :
	nama : xxxxx
	waktu :
	
	// methods
	...
}

**daftarjadwal.java**: class daftarjadwal {
	// attributes
	ArrayList<ruangan> ruangan:
	
	// methods
	int hitungbentrok();
}

### Lain-lain

Contoh objek slot:<br />
<1 IF3170 11> = matkul IF3170 ada di day ke-1 dan jam ke-1<br />
<1 IF3170 12> = ...<br />
<2 IF3151 12> = ...<br />