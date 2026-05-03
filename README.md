# ManagemenStokBarang

Dibuat oleh kelompok 5

Raditya Derry Armadani (09021282530094)

M. Nabil Imtiyaz (09021282530116)

Dhea Aurellia (09021282530075)

Muhammad Ajhar Rizqi Hidayatullah (09021282530101)

Adrina Ginata Dinda Harjanti (09021282530104)

Muhammad Rochman (09021282530079)

# Cara Menjalankan Project

1. Clone repository ini
2. Buka project di NetBeans
3. Tambahkan library secara manual:
   * Klik kanan project → Properties
   * Pilih "Libraries"
   * Klik "Add JAR/Folder"
   * Pilih semua file `.jar` di folder `lib/`
4. Klik Apply → OK
5. Buat file `db.properties` di root folder project (sejajar sama folder `src`), isi dengan:
db.url=jdbc:postgresql://aws-1-ap-southeast-1.pooler.supabase.com:5432/postgres?sslmode=require
db.user=postgres.wzpghelktyrmhhsppumv
db.password=PASSWORD MINTA_KE_KETUA
6. Jalankan project

# Catatan
- Semua dependency berada di folder `lib/`
- File `db.properties` tidak di-push ke GitHub, setiap anggota tim wajib membuat file ini sendiri
- Password database minta langsung ke ketua tim
