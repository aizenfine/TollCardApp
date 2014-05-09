package Model;

/**
 *
 * @author Izzan (local)
 */
public class Portal {
    private int idPortal;
    private int tarif;
    private String nama;

    public Portal(int idPortal, int tarif, String nama) {
        this.idPortal = idPortal;
        this.tarif = tarif;
        this.nama = nama;
    }
    
    public Portal(){}

    public int getIdPortal() {
        return idPortal;
    }

    public void setIdPortal(int idPortal) {
        this.idPortal = idPortal;
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void updatePortal(int idPortal, int tarif, String nama){
        this.setIdPortal(idPortal);
        this.setTarif(tarif);
        this.setNama(nama);
    }
    
}
