ALUR INISIALISASI
-----------------
From: file.java
-----------------

## GLOBAL STEP 0: Program membaca file (panggil prosedur **baca_file()**)

### prosedur baca_file()
01. Membaca isi file test case (berisi ruangan dan jadwal)

02. Untuk setiap baris file yang termasuk dalam kelompok **Ruangan**, maka panggil prosedur **set_ruangan()**<br />
> **prosedur set_ruangan(**String** baris_file_yang_dibaca)**<br /><br />
> 01. Menerima argumen bertipe String dengan format: nama_ruang; waktu_mulai; waktu_selesai; hari<br />
> 02. Parsing argumen dan didapatkan array of String yang berisi nama_ruang, waktu_mulai, waktu_selesai, dan hari<br />
> 03. Konversi **waktu_mulai** dan **waktu_selesai** dari tipe **String** ke tipe **waktu**<br />
> 04. Parsing hari dan didapatkan array of String (misal nama nya **arrHari**) yang berisi indeks dari hari (1: senin, 2: selasa, dst)<br />
> 05. Membentuk objek **hari** dari class **day**<br />
> 06. Iterasi **arrHari**<br />
> 07. Memasukkan setiap elemen iterasi **arrHari** sebagai argumen method **set_hari** milik objek **hari**<br />
> 08. Atribut dari objek **hari** adalah sebuah array of boolean sebanyak 5 elemen, dimana setiap elemen menandakan indeks dari hari, contoh: [true, true, false, true, false] berarti ruangan yang bersangkutan bersifat _available_ pada hari Senin, Selasa, dan Kamis<br />
> 09. Membuat objek untuk ruang ke-i dengan argumen konstruktor nya: nama_ruang, waktu_mulai, waktu_selesai, hari<br />

03. Untuk setiap baris file yang termasuk dalam kelompok **Jadwal**, maka panggil prosedur **set_kuliah()**<br />
> **prosedur set_kuliag(**String** baris_file_yang_dibaca)**<br /><br />
> 01. Menerima argumen bertipe String dengan format: nama_matkul; nama_ruang; waktu_mulai; waktu_selesai; sks; hari<br />
> 02. Parsing argumen dan didapatkan array of String yang berisi nama_matkul, waktu_mulai, waktu_selesai, sks, dan hari<br />
> 03. Konversi **waktu_mulai** dan **waktu_selesai** dari tipe **String** ke tipe **waktu**<br />
> 04. Parsing hari dan didapatkan array of String (misal nama nya **arrHari**) yang berisi indeks dari hari (1: senin, 2: selasa, dst)<br />
> 05. Membentuk objek **hari** dari class **day**<br />
> 06. Iterasi **arrHari**<br />
> 07. Memasukkan setiap elemen iterasi **arrHari** sebagai argumen method **set_hari** milik objek **hari**<br />
> 08. Atribut dari objek **hari** adalah sebuah array of boolean sebanyak 5 elemen, dimana setiap elemen menandakan indeks dari hari, contoh: [true, true, false, true, false] berarti ruangan yang bersangkutan bersifat _available_ pada hari Senin, Selasa, dan Kamis <br />
> 09. Membuat objek dari class **mataKuliah** dengan argumen konstruktor nya: id_matkul, nama_matkul, nama_ruang, waktu_mulai, waktu_selesai, sks, dan hari<br />
> 10. Menambahkan objek yang terlah dibuat tersebut ke dalam ArrayList milik **kuliah** (ArrayList<mataKuliah> kuliah = new ArrayList<mataKuliah>())<br />

## GLOBAL STEP 1: Program melakukan inisialisasi secara random (panggil prosedur **inisialisasi_random()**)<br />

01. Misalkan jumlah mata kuliah dalam test case adalah **j_kuliah**
02. Iterasi setiap mata kuliah (for (int i = 0; i < j_kuliah; i++))
03. **Untuk setiap mata kuliah**, deklarasikan sebuah objek **r** dari class **ruangan**
04. Lakukan pengecekan kondisi (IF-ELSE)
05. **IF** mata kuliah sudah ditentukan ruangan nya (ruangan tidak sama dengan -), maka:<br />
> 01. Assign objek **r** dengan objek ruang milik mata kuliah tersebut (r = kuliah.get(i).get_ruang())<br />
> 02. Panggil prosedur **add_mk_rand** dengan argumen objek **mata kuliah** dan **ruangan** (add_mk_rand(kuliah.get(i),r))<br />
> 03. Prosedur **add_mk_rand** ada di bawah<br />
06. **ELSE** mata kuliah belum ditentukan ruangan nya (ruangan sama dengan -), maka:<br />
> 01. Melakukan pengulangan untuk menentukan ruangan secara random<br />
> 02. Jika sudah keluar dari pengulangan, memanggil prosedur **add_mk_rand** dengan argumen objek **mata kuliah** dan **ruangan hasil random sebelumnya**<br />

### prosedur add_mk_rand(mata kuliah, ruangan)
01. Mendapatkan nilai random untuk hari (antara 1 - 5) dan disimpan dalam variabel misalnya **h**
02. Melakukan pengulangan dimana akan berhenti jika syarat berikut terpenuhi:<br />
> mata kuliah dan ruangan sama-sama memiliki hari **h**<br />
> misalnya mata kuliah memiliki hari: 1,2,3 dan ruangan memiliki hari: 1,3; serta nilai **h** adalah 1, maka mereka sama-sama memiliki nilai hari 1<br />
> nilai **h** yang dimiliki oleh mata kuliah dan ruangan tersebut bisa disebut sebagai **intersection_day**<br />
03. Mendapatkan **jam** dimulainya mata kuliah secara _real_ (kuliah benar2 diadakan, bukan hanya rentang waktu saja)
04. Melakukan pengulangan, dimana akan berhenti jika kedua syarat ini tidak terpenuhi:<br />
> 01. jumlah **jam** dengan sks matkul lebih besar dari waktu selesainya ruangan<br />
> 02. nilai **jam** lebih kecil daripada waktu mulai ruangan<br />
05. Jika sudah keluar dari pengulangan, memanggil prosedur **add_mk** dengan argumen **mata kuliah**, **h**, **jam**, dan **ruangan**
06. Prosedur **add_mk** ada di bawah

### prosedur add_mk(mata kuliah, h, j, ruangan)
01. Set ruangan untuk mata kuliah ybs dengan **ruangan** (mk.set_ruang_ref(r))
02. Set slot waktu untuk mata kuliah ybs dengan **h** dan **s** (mk.set_slot(h,j))

## GLOBAL STEP 2: Program melakukan hitung konflik terhadap inisiasi random awal
01. Dengan algoritma hitung konflik
