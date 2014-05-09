package Model;

/**
 *
 * @author Izzan (local)
 */
public class Kartu {
    private int noKartu;
    private int saldo;

    public Kartu(int noKartu, int saldo) {
        this.noKartu = noKartu;
        this.saldo = saldo;
    }

    public Kartu() {
    }

    public int getNoKartu() {
        return noKartu;
    }

    public void setNoKartu(int noKartu) {
        this.noKartu = noKartu;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    
    public void updateKartu(int noKartu, int saldo){
        this.setNoKartu(noKartu);
        this.setSaldo(saldo);
    }
}
