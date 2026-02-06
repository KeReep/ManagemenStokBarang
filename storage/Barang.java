package storage;


public class Barang {
    private String nama;
    private String kode;
    private int stok;
    
    Barang(String nama, String kode, int stok){
        this.nama = nama;
        this.kode = kode;
        setStok(stok);
    }
    
    @Override
    public String toString (){
        return String.format("Nama : %-15s | Kode : %-10s | Stok : %-5s", nama, kode, stok);
    }
    
    public String getKode () {
        return kode;
    }
    
    public String getNama () {
        return nama;
    }
    
    public int getStok () {
        return stok;
    }

    public void setStok(int stok){
        if (stok < 0) this.stok = 0;
        this.stok = stok;
    }
    

}


