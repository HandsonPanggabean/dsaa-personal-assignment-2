// Queue (FIFO): antrian pelanggan
public class Queue {
    private Node front; // pelanggan paling depan
    private Node rear; // pelanggan paling belakang
    private int size; // jumlah pelanggan dalam antrian

    // Getter untuk mendapatkan jumlah pelanggan saat ini
    public int getSize() { 
        return size; 
    }

    // Enqueue: tambah pelanggan di belakang
    public void enqueue(String kode, String nama, long total) {
        Node baru = new Node(kode, nama, total);
        // jika pelanggan antrian paling belakang tidak ada (atrian kosong)
        if (rear == null) {
            // front dan rear sama-sama node baru
            front = baru;
            rear = baru;
        } else {
            // jika pelanggan antrian paling belakang ada (ada pelanggan dalam antrian)
            rear.next = baru; // sambungkan di belakang rear lama
            rear = baru; // node baru menjadi rear
        }
        size++; // jumlah pelanggan dalam antrian = jumlah pelanggan dalam antrian + 1
    }

    // Dequeue: ambil pelanggan paling depan
    public Node dequeue() {
        // jika pelanggan paling depan tidak ada (antrian kosong)
        if (front == null) {
            return null; // antrian kosong
        }

        Node dilayani = front;
        front = front.next; // front bergeser ke pelanggan berikutnya

        // jika pelanggan berikutnya tidak ada
        if (front == null) {
            rear = null; // antrian jadi kosong -> rear ikut direset
        }

        dilayani.next = null; // putuskan dari sisa antrian
        size--; // jumlah pelanggan dalam antrian = jumlah pelanggan dalam antrian - 1
        return dilayani;
    }

    // Tampilkan list antrian
    public void display() {
        if (size == 0) {
            // validasi jika antrian kosong
            System.out.println("Antrian kosong.");
            return;
        }

        System.out.println("Antrian saat ini (terdepan di nomor 1):");
        Node cur = front; // mulai dari pelanggan terdepan
        int no = 1; // antrian counter: dimulai dari 1
        while (cur != null) {
            System.out.println(no + ". " + cur.kode + " | " + cur.nama + " | Rp" + cur.total);
            cur = cur.next; // lanjut ke pelanggan di belakangnya
            no++; // antrian counter = antrian counter + 1
        }
        System.out.println("Jumlah antrian: " + size);
    }
}