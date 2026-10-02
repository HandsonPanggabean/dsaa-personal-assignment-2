// Node: satu elemen yang menyimpan data pelanggan
public class Node {
    String kode;
    String nama;
    long total;
    Node next; // penunjuk ke node berikutnya (null jika node terakhir)
 
    public Node(String kode, String nama, long total) {
        this.kode = kode;
        this.nama = nama;
        this.total = total;
        this.next = null;
    }
}