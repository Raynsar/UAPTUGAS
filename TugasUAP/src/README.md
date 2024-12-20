# Sistem Absensi Mahasiswa

## Deskripsi Proyek
Sistem Absensi Mahasiswa adalah aplikasi berbasis Java Swing yang memungkinkan pengguna untuk melakukan login dan mengelola data absensi mahasiswa. Aplikasi ini memiliki fitur login yang memastikan hanya pengguna yang berwenang yang dapat mengakses sistem absensi.

## Fitur Utama
1. **Login Sistem**: Pengguna harus login menggunakan username dan password.
2. **Pengelolaan Data Absensi**:
    - Tambah Data Absensi (dengan validasi login)
    - Edit Data Absensi
    - Hapus Data Absensi
    - Cari Data Absensi berdasarkan NIM
3. **Form Input Data**: Form input mencakup NIM, Nama, dan Foto.
4. **Tabel Absensi**: Menampilkan daftar absensi mahasiswa yang berisi NIM, Nama, Waktu Absensi, dan Foto.

## Cara Menjalankan Aplikasi
1. Pastikan Anda memiliki **Java Development Kit (JDK)** yang terinstal di komputer Anda.
2. Kompilasi dan jalankan file **SistemAbsensiMahasiswa.java** menggunakan perintah berikut:
3. Login dengan username dan password berikut:
- **Username**: admin
- **Password**: 12345

4. Setelah berhasil login, Anda dapat mulai menambah, mengedit, menghapus, dan mencari data absensi.

## Penjelasan Kelas

### 1. **LoginFrame.java**
Kelas ini bertanggung jawab untuk menampilkan form login dan memverifikasi kredensial pengguna.

#### Komponen Utama
- **JTextField txtUsername**: Input untuk username.
- **JPasswordField txtPassword**: Input untuk password.
- **JButton btnLogin**: Tombol login untuk memverifikasi username dan password.

#### Proses Login
- Jika username adalah "admin" dan password adalah "12345", maka akses diberikan ke SistemAbsensiMahasiswa.
- Jika login gagal, muncul pesan kesalahan.

### 2. **SistemAbsensiMahasiswa.java**
Kelas ini bertanggung jawab atas pengelolaan data absensi mahasiswa.

#### Komponen Utama
- **JTextField txtNIM, txtNama**: Input untuk data NIM dan Nama.
- **JButton btnTambah, btnEdit, btnHapus, btnCari, btnReset**: Tombol untuk mengelola data absensi.
- **JTable table**: Tabel untuk menampilkan daftar absensi.

#### Fitur Utama
1. **Tambah Data**
- Memeriksa apakah NIM dan Nama diisi.
- Menyimpan data ke dalam **absensiData**.
- Menampilkan pesan berhasil jika data berhasil ditambahkan.

2. **Edit Data**
- Memilih data dari tabel, mengisi formulir dengan data tersebut, dan mengizinkan pengeditan.
- Setelah pengeditan, data yang lama dihapus dari **absensiData** dan diganti dengan data yang baru.

3. **Hapus Data**
- Memilih baris dari tabel dan menghapus data yang sesuai dari **absensiData**.
- Menampilkan pesan berhasil setelah penghapusan.

4. **Cari Data**
- Mencari data berdasarkan NIM yang dimasukkan oleh pengguna.
- Jika ditemukan, menampilkan informasi mahasiswa.
- Jika tidak ditemukan, muncul pesan bahwa data tidak ditemukan.

5. **Reset Form**
- Mengosongkan semua inputan di formulir.

## Dependensi
- **Java Swing**: Untuk membuat antarmuka pengguna (GUI).
- **JFileChooser**: Untuk memilih file gambar untuk diunggah.

## Validasi dan Keamanan
- **Login**: Hanya pengguna yang memiliki username dan password yang benar yang dapat mengakses sistem absensi.
- **Validasi Input**: NIM dan Nama tidak boleh kosong saat menambahkan data absensi.

## Perbaikan dan Pengembangan
1. **Keamanan**:
- Ganti hardcode username dan password dengan metode yang lebih aman, seperti autentikasi berbasis database.
2. **Penyimpanan Data**:
- Tambahkan fitur penyimpanan data ke file atau database agar data tidak hilang saat aplikasi ditutup.
3. **Validasi Lebih Ketat**:
- Validasi format NIM agar hanya menerima angka.
- Validasi panjang NIM dan Nama.

## Kontributor
- **Pengembang**: Nama Anda (Opsional, jika Anda ingin menuliskan nama Anda).

## Lisensi
Proyek ini dilisensikan di bawah lisensi MIT. Anda bebas menggunakan, mengubah, dan mendistribusikan proyek ini dengan atau tanpa modifikasi.

---

Jika Anda memiliki pertanyaan atau saran, jangan ragu untuk menghubungi kami.

