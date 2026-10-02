// LinkedList: Single Linked List untuk menyimpan daftar buku
public class LinkedList {
    private Node head; // node pertama
    private int size;  // jumlah buku yang ada di daftar buku saat ini

    // Getter untuk mendapatkan jumlah buku saat ini
    public int getSize() {
        return size;
    }

    // Validasi tambahan: cek apakah kodeBuku sudah dipakai (unique code)
    public boolean kodeSudahAda(String kode) {
        Node cur = head;
        while (cur != null) {
            if (cur.kodeBuku.equalsIgnoreCase(kode)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // Tambah buku di akhir daftar
    public void tambahDiAkhir(String kode, String judul, String penulis) {
        Node baru = new Node(kode, judul, penulis);

        // jika daftar kosong
        if (head == null) {
            // assign node baru jadi head
            head = baru;
        } else {
            // jika daftar tidak kosong
            Node cur = head;
            while (cur.next != null) { // telusuri sampai node terakhir
                cur = cur.next;
            }
            cur.next = baru; // sambungkan node baru di belakang node terakhir
        }
        size++; // size = current size + 1
    }

    // Hapus buku terakhir
    public Node hapusTerakhir() {
        if (head == null) {
            return null; // daftar kosong? return null
        }
 
        Node dihapus;
        // jika hanya ada 1 buku ?
        if (head.next == null) {
            dihapus = head; // simpan head untuk dihapus
            head = null; // nullkan head
        } else {
            Node cur = head;

            // jika cur 2 langkah didepan tidak sama dengan null (berisi) ?
            while (cur.next.next != null) {
                // maka cur bukanlah node terakhir
                cur = cur.next; // set cur menjadi next cur 
                // perulangan kembali dilakukan sampai kondisi dari perulangan bernilai TRUE
            }

            dihapus = cur.next; // simpan node yang ingin dihapus
            cur.next = null; // putuskan node terakhir dari daftar
        }
        size--; // size = current size - 1
        return dihapus;
    }

    // Cari buku
    public Node cari(String kode) {
        Node cur = head;
        // apakah cur ada / daftar buku !== kosong ?
        while (cur != null) {
            // jika cur.kodeBuku = params kode
            if (cur.kodeBuku.equalsIgnoreCase(kode)) {
                return cur;
            }
            // jika tidak
            cur = cur.next; // lanjut perulangan ke node berikutnya
        }
        return null; // tidak ketemu
    }

    // Tampilkan semua buku
    public void tampilSemua() {
        if (head == null) {
            // validasi jika daftar buku kosong
            System.out.println("Daftar buku kosong.");
        } else {
            System.out.println("Daftar Buku:");
            Node cur = head; // mulai dari buku pertama
            while (cur != null) {
                System.out.println("Kode: " + cur.kodeBuku + " | Judul: " + cur.judul + " | Penulis: " + cur.penulis);
                cur = cur.next; // lanjut ke buku berikutnya
            }
        }
        System.out.println("Total Buku: " + size);
    }
}