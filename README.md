# OlshopAPP - Aplikasi Online Shop Android

Aplikasi Android sederhana untuk online shop yang dibuat menggunakan Java dan XML Layout sebagai tugas mata kuliah Pemrograman Mobile 1.

## Fitur Aplikasi

### 1. Login dan Register
- LoginActivity sebagai halaman utama aplikasi (Launcher Activity)
- RegisterActivity untuk pendaftaran akun baru dengan validasi input
- Sistem autentikasi menggunakan SharedPreferences untuk menyimpan data user
- Notifikasi Toast untuk memberikan feedback kepada user saat login atau register

### 2. Halaman Produk Utama (MainActivity)
- Menampilkan daftar produk dalam RecyclerView dengan GridLayoutManager 2 kolom
- Setiap item produk menampilkan gambar, nama produk, dan harga
- Toolbar dengan menu keranjang belanja di bagian atas
- Fungsi klik pada produk untuk membuka halaman detail

### 3. Detail Produk
- DetailProductActivity menampilkan informasi lengkap produk yang dipilih
- Deskripsi produk, harga, dan gambar dalam layout ScrollView
- Tombol "Tambah ke Keranjang" yang menyimpan data ke SharedPreferences
- Transfer data antar activity menggunakan Intent dengan putExtra

### 4. Keranjang Belanja
- CartActivity menampilkan semua produk yang sudah ditambahkan ke keranjang
- Dapat diakses melalui icon menu di toolbar MainActivity
- Menghitung dan menampilkan total harga secara otomatis
- Layout dinamis yang menyesuaikan jumlah item dalam keranjang
- Tombol checkout untuk melanjutkan ke pembayaran

### 5. Proses Checkout
- CheckoutActivity menampilkan ringkasan pesanan dan form pembayaran
- Simulasi alamat pengiriman dan pilihan metode pembayaran
- Kalkulasi total harga termasuk ongkos kirim
- Setelah checkout berhasil, keranjang akan dikosongkan otomatis
- Konfirmasi "Checkout Berhasil" melalui Toast notification

## Struktur Project

```
app/src/main/java/com/example/olshopapp/
├── LoginActivity.java          # Halaman login (Launcher)
├── RegisterActivity.java       # Halaman registrasi
├── MainActivity.java           # Halaman utama dengan toolbar & menu
├── DetailProductActivity.java  # Detail produk dengan ScrollView
├── CartActivity.java           # Keranjang belanja
├── CheckoutActivity.java       # Checkout & pembayaran
├── Product.java                # Model class produk
└── ProductAdapter.java         # Adapter RecyclerView dengan click listener

app/src/main/res/layout/
├── activity_login.xml          # Layout login dengan Material Design
├── activity_register.xml       # Layout register dengan validasi
├── activity_main.xml           # Layout main dengan Toolbar & RecyclerView
├── activity_detail_product.xml # Layout detail dengan ScrollView
├── activity_cart.xml           # Layout keranjang dengan dynamic content
├── activity_checkout.xml       # Layout checkout dengan ringkasan
└── item_product.xml            # Layout item produk dengan CardView

app/src/main/res/menu/
└── main_menu.xml               # Menu toolbar dengan icon keranjang

app/src/main/res/drawable/
└── cart_item_background.xml    # Background untuk item keranjang
```

## Teknologi yang Digunakan

- Bahasa: Java
- UI: XML Layout (LinearLayout, ConstraintLayout, ScrollView)
- Components: RecyclerView, CardView, Toolbar, Material Design
- Storage: SharedPreferences untuk user data & keranjang
- Navigation: Intent Explicit dengan putExtra
- Min SDK: API 28 (Android 9)
- Target SDK: API 35 (Android 14+)
- Dependencies: RecyclerView, CardView, AppCompat, Material Design

## Data Produk

Aplikasi menggunakan data dummy dengan 6 produk:
1. Smartphone Samsung - Rp 3.500.000
   - Smartphone terbaru dengan kamera 48MP dan baterai 4000mAh
2. Laptop Gaming - Rp 12.000.000
   - Laptop gaming dengan processor Intel i7 dan VGA RTX 3060
3. Headphone Wireless - Rp 750.000
   - Headphone wireless dengan noise cancelling dan bass yang jernih
4. Smartwatch - Rp 2.500.000
   - Smartwatch dengan fitur health monitoring dan GPS
5. Tablet Android - Rp 4.200.000
   - Tablet 10 inci dengan layar AMOLED dan stylus pen
6. Speaker Bluetooth - Rp 450.000
   - Speaker portable dengan suara stereo dan tahan air

## Cara Menjalankan

1. Buka project di Android Studio
2. Sync Gradle (dependencies akan terdownload otomatis)
3. Run aplikasi di emulator atau device fisik
4. Registrasi akun baru di RegisterActivity
5. Login dengan email dan password yang didaftarkan
6. Jelajahi produk dan tambahkan ke keranjang
7. Akses keranjang melalui icon di toolbar
8. Checkout untuk menyelesaikan pembelian

## Alur Aplikasi

1. Start → LoginActivity (Launcher Activity)
2. Register → Klik "Belum punya akun?" → RegisterActivity → Kembali ke Login
3. Login → Validasi SharedPreferences → MainActivity (Daftar Produk)
4. Browse Products → Grid layout dengan 6 produk dummy
5. Product Detail → Klik produk → DetailProductActivity → "Tambah ke Keranjang"
6. Shopping Cart → Icon menu toolbar → CartActivity → Lihat total harga
7. Checkout → Tombol checkout → CheckoutActivity → Ringkasan pesanan
8. Payment → "Bayar Sekarang" → Toast success → Keranjang dikosongkan

## Contoh Penggunaan Kode

### Intent antar Activity:
```java
// Dari MainActivity ke DetailProductActivity
Intent intent = new Intent(MainActivity.this, DetailProductActivity.class);
intent.putExtra("product_name", product.getName());
intent.putExtra("product_price", product.getPrice());
intent.putExtra("product_description", product.getDescription());
intent.putExtra("product_image", product.getImageResource());
startActivity(intent);
```

### SharedPreferences untuk Login:
```java
SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
SharedPreferences.Editor editor = sharedPreferences.edit();
editor.putString("name", name);
editor.putString("email", email);
editor.putString("password", password);
editor.apply();
```

### SharedPreferences untuk Keranjang:
```java
SharedPreferences cartPrefs = getSharedPreferences("CartPrefs", MODE_PRIVATE);
Set<String> cartItems = cartPrefs.getStringSet("cart_items", new HashSet<>());
Set<String> newCartItems = new HashSet<>(cartItems);
String cartItem = productName + "|" + productPrice;
newCartItems.add(cartItem);
editor.putStringSet("cart_items", newCartItems);
```

### Setup RecyclerView dengan GridLayout:
```java
recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
productAdapter = new ProductAdapter(this, productList);
recyclerView.setAdapter(productAdapter);
```

### Setup Toolbar dengan Menu:
```java
Toolbar toolbar = findViewById(R.id.toolbar);
setSupportActionBar(toolbar);
// Menu akan muncul otomatis dari onCreateOptionsMenu()
```

## Fitur Pembelajaran

Aplikasi ini cocok untuk mempelajari:
- Intent Explicit dan transfer data antar Activity
- RecyclerView dengan custom adapter dan click listener
- SharedPreferences untuk data persistence
- Material Design Components (Toolbar, CardView, TextInputLayout)
- Layout XML (LinearLayout, ConstraintLayout, ScrollView)
- Model-View pattern sederhana
- Menu dan Toolbar Android
- Toast notifications untuk user feedback
- Dynamic UI dengan programmatic layout

## Troubleshooting

### Jika ada error warna:
- Semua warna menggunakan Android default (`@android:color/`)
- Tidak menggunakan `design_default_color` yang bermasalah

### Jika icon keranjang tidak muncul:
- Pastikan `setSupportActionBar(toolbar)` dipanggil
- Pastikan `onCreateOptionsMenu()` return true

### Jika SharedPreferences tidak bekerja:
- Pastikan menggunakan nama yang sama: "UserPrefs" dan "CartPrefs"
- Pastikan `editor.apply()` dipanggil setelah edit

## Screenshot Fitur

- Login/Register dengan Material Design dan validasi
- Grid layout produk 2 kolom yang responsive
- Detail produk dengan ScrollView dan deskripsi lengkap
- Keranjang dengan dynamic item list dan total harga
- Checkout dengan ringkasan pesanan dan simulasi pembayaran
- Toolbar dengan menu icon keranjang yang fungsional
- Toast notifications untuk feedback user

## Status: COMPLETED & TESTED

Aplikasi telah selesai dibuat dan siap digunakan untuk pembelajaran Pemrograman Mobile! 

Next Steps untuk Pengembangan:
- Tambah database SQLite untuk data persistence
- Implementasi REST API untuk data produk
- Tambah fitur search dan filter produk
- Implementasi payment gateway
- Tambah fitur wishlist dan rating produk
