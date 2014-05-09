package Model;

/**
 *
 * @author Izzan (local)
 */
public class Customer {
    private String nama;
    private String noKtp;
    private String noHp;
    private String alamat;
    private Kartu kartu;

    public Customer(String nama, String noKtp, String noHp, String alamat, Kartu kartu) {
        this.nama = nama;
        this.noKtp = noKtp;
        this.noHp = noHp;
        this.alamat = alamat;
        this.kartu = kartu;
    }

    public Customer() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Kartu getKartu() {
        return kartu;
    }

    public void setKartu(Kartu kartu) {
        this.kartu = kartu;
    }
    
    public void updateCustomer(String nama, String noKtp, String noHp, String alamat){
        this.setNama(nama);
        this.setNoKtp(noKtp);
        this.setNoHp(noHp);
        this.setAlamat(alamat);
    }
    
}
