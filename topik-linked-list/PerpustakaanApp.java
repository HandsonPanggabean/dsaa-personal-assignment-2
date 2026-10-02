import java.util.Scanner;

// Menu utama pengelolaan daftar buku perpustakaan
public class PerpustakaanApp {
    private static final int MAX_KODE = 5;  // kodeBuku maksimal 5 karakter: BK001 = valid & BUKU0001 = invalid
    private static final int MIN_BUKU = 5;  // jumlah data minimal 5 buku

    // Menampilkan list menu
    private static void tampilkanMenu() {
        System.out.println("\n===== SISTEM DATA BUKU =====");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Hapus Buku");
        System.out.println("3. Cari Buku");
        System.out.println("4. Lihat Semua Buku");
        System.out.println("5. Keluar");
        System.out.print("Pilih menu: ");
    }

    // Validasi kode buku yang di input user
    private static String bacaKodeBuku(Scanner sc, LinkedList daftar) {
        System.out.print("Masukkan Kode Buku: ");
        String kode = sc.nextLine().trim();
 
        // Validasi: tidak kosong & maksimal 5 karakter
        if (kode.isEmpty() || kode.length() > MAX_KODE) {
            System.out.println("Gagal: kode buku max " + MAX_KODE + " karakter!");
            return null;
        }
        // Validasi (tambahan): kode tidak boleh sama dengan kode buku lain (dari class LinkedList)
        if (daftar.kodeSudahAda(kode)) {
            System.out.println("Gagal: kode buku sudah digunakan!");
            return null;
        }
        return kode;
    }

    // Validasi jumlah data minimal 5 buku: tampilkan info progres
    private static void tampilkanInfoJumlah(LinkedList daftar) {
        if (daftar.getSize() < MIN_BUKU) {
            System.out.println("Info: baru " + daftar.getSize() + " buku, minimal " + MIN_BUKU + " buku.");
        } else {
            System.out.println("Jumlah data sudah memenuhi minimal " + MIN_BUKU + " buku (" + daftar.getSize() + " buku).");
        }
    }

    // Menu 1: Tambah buku
    private static void tambahBuku(Scanner sc, LinkedList daftar) {
        String kode = bacaKodeBuku(sc, daftar); // method helper untuk menampilkan input dan memvalidasi kode buku yang di input user
        if (kode == null) {
            // kode tidak valid, batalkan penambahan
            return;
        }
 
        // Menampilkan input dan memvalidasi judul buku yang di input user
        System.out.print("Masukkan Judul: ");
        String judul = sc.nextLine().trim();
        if (judul.isEmpty()) {
            System.out.println("Gagal: judul tidak boleh kosong!");
            return;
        }

        // Menampilkan input dan memvalidasi penulis buku yang di input user
        System.out.print("Masukkan Penulis: ");
        String penulis = sc.nextLine().trim();
        if (penulis.isEmpty()) {
            System.out.println("Gagal: penulis tidak boleh kosong!");
            return;
        }
 
        daftar.tambahDiAkhir(kode, judul, penulis); // tambah buku kedalam daftar (tambah di akhir)
        System.out.println("Data berhasil ditambahkan!");
        tampilkanInfoJumlah(daftar);
    }

    // Menu 2: Hapus buku
    private static void hapusBuku(LinkedList daftar) {
        Node dihapus = daftar.hapusTerakhir();
        if (dihapus == null) {
            // validasi jika tidak ada buku yang bisa dihapus
            System.out.println("Tidak ada data untuk dihapus.");
        } else {
            System.out.println("Buku terakhir berhasil dihapus: " + dihapus.kodeBuku + " | " + dihapus.judul);
            System.out.println("Sisa buku: " + daftar.getSize());
        }
    }

    // Menu 3: Cari buku
    private static void cariBuku(Scanner sc, LinkedList daftar) {
        System.out.print("Masukkan Kode Buku: ");
        String kode = sc.nextLine().trim(); // simpan kode buku yang diinput user
        
        // cari buku berdasarkan kode buku yang telah tersimpan
        Node hasil = daftar.cari(kode);
        if (hasil == null) {
            // validasi jika buku tidak ditemukan
            System.out.println("Buku tidak ditemukan.");
        } else {
            System.out.println("Buku ditemukan!");
            System.out.println("Kode: " + hasil.kodeBuku + " | Judul: " + hasil.judul + " | Penulis: " + hasil.penulis);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList daftar = new LinkedList();
        int pilih = 0;

        do {
            // Tampilkan list menu
            tampilkanMenu();

            // Validasi jika user menginput selain angka (huruf, etc) (expected input = angka)
            try {
                pilih = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                pilih = 0; // input bukan angka -> menu tidak valid
            }

            switch (pilih) {
                case 1: // Menu 1: tambah buku
                    tambahBuku(sc, daftar);
                    break;
                case 2:
                    hapusBuku(daftar);
                    break;
                case 3:
                    cariBuku(sc, daftar);
                    break;
                case 4:
                    daftar.tampilSemua();
                    break;
                case 5: // Menu 5: keluar dari program
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Menu tidak valid!");
            }
        } while (pilih != 5);

        sc.close();
    }
}