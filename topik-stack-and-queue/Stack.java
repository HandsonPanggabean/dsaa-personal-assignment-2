// Stack (LIFO): riwayat transaksi
public class Stack {
    private Node top; // transaksi paling atas (terbaru)
    private int size; // jumlah transaksi dalam riwayat

    // Getter untuk mendapatkan jumlah transaksi saat ini
    public int getSize() { 
        return size; 
    }

    // Push: Simpan transaksi baru di stack paling atas
    public void push(String kode, String nama, long total) {
        Node baru = new Node(kode, nama, total);
        baru.next = top; // node baru menunjuk ke transaksi yang tadinya paling atas
        top = baru; // node baru menjadi puncak (top) yang baru
        size++; // jumlah transaksi = jumlah transaksi + 1
    }

    // Tampilkan riwayat dari transaksi terbaru (DESC)
    public void display() {
        if (top == null) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }
        System.out.println("Riwayat Transaksi (terbaru di atas):");
        Node cur = top; // mulai dari puncak
        int no = 1; // transaksi counter: dimulai dari 1
        while (cur != null) {
            System.out.println(no + ". " + cur.kode + " | " + cur.nama + " | Rp" + cur.total);
            cur = cur.next; // turun ke transaksi yang lebih lama
            no++; // transaksi counter = transaksi counter + 1
        }
        System.out.println("Total transaksi: " + size);
    }
}