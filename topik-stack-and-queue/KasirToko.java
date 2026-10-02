import java.util.Scanner;

// Program utama: sistem kasir toko (antrian pelanggan / Queue & riwayat transaksi / Stack)
public class KasirToko {
    private static final int MIN_ANTRIAN = 5; // minimal 5 pelanggan dalam antrian

    // Menampilkan menu utama
    private static void tampilkanMenu() {
        System.out.println("\n=== SISTEM KASIR TOKO ===");
        System.out.println("1. Tambah Antrian");
        System.out.println("2. Layani Pelanggan");
        System.out.println("3. Tampilkan Antrian");
        System.out.println("4. Lihat Riwayat Transaksi");
        System.out.println("5. Keluar");
        System.out.print("Pilih menu: ");
    }

    // Info progres jumlah antrian terhadap minimal 5 pelanggan
    private static void tampilkanInfoJumlah(Queue antrian) {
        if (antrian.getSize() < MIN_ANTRIAN) {
            System.out.println("Info: baru " + antrian.getSize() + " pelanggan, minimal " + MIN_ANTRIAN + " pelanggan.");
        } else {
            System.out.println("Antrian sudah memenuhi minimal " + MIN_ANTRIAN + " pelanggan (" + antrian.getSize() + " pelanggan).");
        }
    }

    // Menu 1: input data pelanggan lalu enqueue ke antrian
    private static void tambahAntrian(Scanner sc, Queue antrian) {
        System.out.print("Masukkan Nomor Antrian: ");
        String kode = sc.nextLine().trim();
        System.out.print("Masukkan Nama Pelanggan: ");
        String nama = sc.nextLine().trim();
        System.out.print("Masukkan Total Belanja: ");
        String teksTotal = sc.nextLine().trim();

        // validasi jika kode maupun nama pelanggan tidak di input user (kosong)
        if (kode.isEmpty() || nama.isEmpty()) {
            System.out.println("Gagal: nomor antrian dan nama tidak boleh kosong!");
            return;
        }

        long total;
        try {
            total = Long.parseLong(teksTotal);
        } catch (NumberFormatException e) {
            // validasi jika user tidak menginput angka saat mengisi total belanja
            System.out.println("Gagal: total belanja harus berupa angka!");
            return;
        }
        if (total < 0) {
            // validasi jika user menginput angka minus (misalkan: -100)
            System.out.println("Gagal: total belanja tidak boleh negatif!");
            return;
        }

        antrian.enqueue(kode, nama, total); // tambah pelanggan di belakang antrian
        System.out.println("Data pelanggan ditambahkan ke antrian!");
        tampilkanInfoJumlah(antrian);
    }

    // Menu 2: Layani pelanggan (dequeue dari antrian (queue) + push ke riwayat transaksi (stack))
    private static void layaniPelanggan(Queue antrian, Stack riwayat) {
        Node dilayani = antrian.dequeue(); // ambil pelanggan dari antrian paling depan
        if (dilayani == null) {
            // validasi jika tidak ada pelanggan di antrian saat ini
            System.out.println("Tidak ada pelanggan dalam antrian.");
        } else {
            System.out.println("Melayani pelanggan " + dilayani.kode + " (" + dilayani.nama + ")");
            riwayat.push(dilayani.kode, dilayani.nama, dilayani.total); // simpan ke riwayat (stack)
            System.out.println("Transaksi disimpan ke riwayat.");
            System.out.println("Sisa antrian: " + antrian.getSize());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue antrian = new Queue();
        Stack riwayat = new Stack();
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
                case 1: // Menu 1: Tambah pelanggan ke antrian
                    tambahAntrian(sc, antrian);
                    break;
                case 2: // Menu 2: Layani pelanggan
                    layaniPelanggan(antrian, riwayat);
                    break;
                case 3: // Menu 3: Tampilkan antrian
                    antrian.display();
                    break;
                case 4: // Menu 4: Tampilkan riwayat transaksi
                    riwayat.display();
                    break;
                case 5: // Keluar
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Menu tidak valid!");
            }
        } while (pilih != 5);

        sc.close();
    }
}