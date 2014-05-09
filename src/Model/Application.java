package Model;


import JDBC.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Izzan (local)
 */
public class Application {
    private Customer[] cus = new Customer[100];
    private Pegawai[] peg = new Pegawai[20];
    private Portal[] por = new Portal[20];
    private int jumCus = 0;
    private int jumPeg = 0;
    private int jumPor = 0;
    
    public void removeCus(String ktp){
        int i=0;
        int f=0;
        while(i<jumCus){
            if(cus[i].getNoKtp().equals(ktp)){
                f=1;
                break;
            };
            i++;
        }
        if(f==1){
            cus[i]=null;
            while(i<jumCus){
                cus[i]=cus[i+1];
                i++;
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Hapus Gagal");
        }
    }
    public void removePor(int id){
        int i=0;
        int f=0;
        while(i<jumPor){
            if(por[i].getIdPortal()==id){
                f=1;
                break;
            };
            i++;
        }
        if(f==1){
            por[i]=null;
            while(i<jumPor){
                por[i]=por[i+1];
                i++;
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Hapus Gagal");
        }
    }
    public Customer getCusByCardId(int idCard) {
        int f=0;
        int i=0;
        while(i<jumCus){
            if(cus[i].getKartu().getNoKartu()==idCard){
                f=1;
                break;
            };
            i++;
        }
        if(f==1){
            return cus[i];
        }
        else{
            return null;
        }
    }
    public int getIndexCusByCardId(int idCard) {
        int f=0;
        int i=0;
        while(i<jumCus){
            if(cus[i].getKartu().getNoKartu()==idCard){
                f=1;
                break;
            };
            i++;
        }
        if(f==1){
            return i;
        }
        else{
            return -1;
        }
    }
    public void addCus(Customer cus) {
        this.cus[jumCus] = cus;
        jumCus++;
    }
    public void UpdateCus(int id, Customer c){
        int i = this.getIndexCusByCardId(id);
        if(i!=-1){
            cus[i]=null;
            cus[i]=c;
        }
        else{
            JOptionPane.showMessageDialog(null, "idCard Salah");
        }
    }
    public Customer searchCustomerByNoKtp(String NoKtp){
        int f=0;
        int i=0;
        while(i<jumCus){
            if(cus[i].getNoKtp().equals(NoKtp)){
                f=1;
                break;
            };
            i++;
        }
        if(f==1){
            return cus[i];
        }
        else{
            return null;
        }
    }
    
    public Kartu searchKartuById(int idCard){
        int f=0;
        int i=0;
        while(i<jumCus){
            if(cus[i].getKartu().getNoKartu()==idCard){
                f=1;
                break;
            };
            i++;
        }
        if(f==1){
            return cus[i].getKartu();
        }
        else{
            return null;
        }
    }
    

    public Pegawai getPegById(int id) {
        int f=0;
        int i=0;
        while(i<jumPeg){
            if(peg[i].getIdPegawai()==id){
                f=1;
                break;
            };
            i++;
        }
        if(f==1){
            return peg[i];
        }
        else{
            return null;
        }
    }

    public void addPeg(Pegawai peg) {
        this.peg[jumPeg] = peg;
        jumPeg++;
    }
    
    public Pegawai searchPegById(int id){
        int f=0;
        int i=0;
        while(i<jumPeg){
            if(peg[i].getIdPegawai()==id){
                f=1;
                break;
            };
            i++;
        }
        if(f==1){
            return peg[i];
        }
        else{
            return null;
        }
    }

    public Portal getPorByNama(String nama) {
        int f=0;
        int i=0;
        while(i<jumPor){
            if(por[i].getNama()==nama){
                f=1;
                break;
            };
            i++;
        }
        if(f==1){
            return por[i];
        }
        else{
            return null;
        }
    }

    public void addPor(Portal por) {
        this.por[jumPor] = por;
        jumPor++;
    }
    
    public Portal searchPortalByNama(String n){
        int f=0;
        int i=0;
        while(i<jumPor){
            if(por[i].getNama().equals(n)){
                f=1;
                break;
            };
            i++;
        }
        if(f==1){
            return por[i];
        }
        else{
            return null;
        }
    }
    public Portal searchPortalById(int n){
        int f=0;
        int i=0;
        while(i<jumPor){
            if(por[i].getIdPortal()==n){
                f=1;
                break;
            };
            i++;
        }
        if(f==1){
            return por[i];
        }
        else{
            return null;
        }
    }
    public int searchIndexPortalById(int n){
        int f=0;
        int i=0;
        while(i<jumPor){
            if(por[i].getIdPortal()==n){
                f=1;
                break;
            };
            i++;
        }
        if(f==1){
            return i;
        }
        else{
            return -1;
        }
    }
    public void UpdatePor(int id, Portal p){
        int i = this.searchIndexPortalById(id);
        if(i!=-1){
            por[i]=null;
            por[i]=p;
        }
        else{
            JOptionPane.showMessageDialog(null, "idCard Salah");
        }
    }
    
    public void LoadCustomer(){
         Database db = new Database();
         ResultSet rsc = null;
         try {
             rsc = db.getData("select * from Customer;");
             while(rsc.next()){
                 Customer c = new Customer(rsc.getString("Customer.nama"), rsc.getString("Customer.noKtp"), rsc.getString("Customer.noHp"), rsc.getString("Customer.alamat"), null);
                 this.addCus(c);
             }
             rsc.close();
             rsc = db.getData("select * from Kartu;");
             while(rsc.next()){
                 Kartu k = new Kartu(rsc.getInt("Kartu.idCard"),rsc.getInt("Kartu.saldo"));
                 Customer c = null;
                 c = searchCustomerByNoKtp(rsc.getString("noKtp"));
                 c.setKartu(k);
             }
         } catch (SQLException ex) {
             Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
    public void LoadKartu(){
    
    }
    public void LoadPegawai(){
         Database db = new Database();
         ResultSet rs = null;
         rs = db.getData("select * from Pegawai;");
         try {
             while(rs.next()){
                 Pegawai p = new Pegawai(rs.getInt("idPegawai"), rs.getString("nama"), rs.getString("password"));
                 this.addPeg(p);
             }
         } catch (SQLException ex) {
             Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
    public void LoadPortal(){
         Database db = new Database();
         ResultSet rs = null;
         rs = db.getData("select * from Portal;");
         try {
             while(rs.next()){
                 Portal p = new Portal(rs.getInt("idPortal"), rs.getInt("tarif"), rs.getString("nama"));
                 this.addPor(p);
             }
         } catch (SQLException ex) {
             Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
}
