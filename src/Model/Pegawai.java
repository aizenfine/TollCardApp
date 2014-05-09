package Model;

/**
 *
 * @author Izzan (local)
 */
public class Pegawai {
   private int idPegawai;
   private String nama;
   private String password;

    public Pegawai(int idPegawai, String nama, String password) {
        this.idPegawai = idPegawai;
        this.nama = nama;
        this.password = password;
    }

    public Pegawai() {
    }

    public int getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(int idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
   
}
