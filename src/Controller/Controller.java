package Controller;

import Gui.*;
import JDBC.*;
import Model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Izzan (local)
 */
public class Controller implements ActionListener{

    private PilihUser viewPU;
    private Login viewLO;
    private HomePanel viewHP;
    private BayarPortalPanel viewBP;
    private EditProfileForm viewEC;
    private EditPortalForm viewEP;
    private NewPortalForm viewNP;
    private NewTollCard viewNC;
    private Application model;
    private int IdK = 0;
    private int IdKK = 0;
    private int IdKP = 0;
    private String noKtp;
    private int tarif=0;
    private boolean ck = false;
    private boolean cp = false;
    private boolean cs = false;
    

    public Controller() throws SQLException{
       
    }
    
    public void setListener(){
        viewPU.addListener(this);
        viewBP.addListener(this);
        viewLO.addListener(this);
        viewHP.addListener(this);
        viewEC.addListener(this);
        viewEP.addListener(this);
        viewNC.addListener(this);
        viewNP.addListener(this);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Database db=new Database();
        try{
            Object source=e.getSource();
            if(source==viewPU.getButtonCustomer()){
                viewBP.setVisible(true);
                viewPU.setVisible(false);
            }         
            else if(source==viewPU.getButtonAdmin()){
                viewLO.setVisible(true);
                viewPU.setVisible(false);
            }
            else if(source==viewLO.getButtonLogin()){
                Pegawai p = null;
                p = model.searchPegById(Integer.parseInt(viewLO.getTextId().getText()));
                if(p!=null){
                    if(p.getPassword().equals(viewLO.getTextPass().getText())){
                        viewLO.setVisible(false);
                        getViewHP().setVisible(true);
                        viewHP.getTfIdPegawai().setText(p.getIdPegawai()+"");
                        viewHP.getTfNamaPegawai().setText(p.getNama());
                    }
                    else{
                        javax.swing.JOptionPane.showMessageDialog(null, "PASSWORD SALAH");
                    }
                }
                else{
                    javax.swing.JOptionPane.showMessageDialog(null, "ID SALAH");
                }
            }
            else if(source==viewBP.getbCheckCard()){
                Kartu k = model.searchKartuById(Integer.parseInt(viewBP.getTfIdCard().getText()));
                if(k!=null){
                    javax.swing.JOptionPane.showMessageDialog(null, "ID BENAR");
                    viewBP.getTfSisaSaldo().setText(k.getSaldo()+"");
                    IdK = k.getNoKartu();
                    ck = true;
                }
                else{
                    javax.swing.JOptionPane.showMessageDialog(null, "ID SALAH");
                }
            }
            else if(source==viewBP.getbCheckPortal()){
                Portal p = model.searchPortalByNama(viewBP.getTfPortal().getText());
                if(p!=null){
                    javax.swing.JOptionPane.showMessageDialog(null, "ID BENAR");
                    viewBP.getTfTarif().setText(p.getTarif()+"");
                    tarif=p.getTarif();
                    cp = true;
                }
                else{
                    javax.swing.JOptionPane.showMessageDialog(null, "ID SALAH");
                }
            }
            else if(source==viewBP.getButtonConfirm()){
                if(ck==false){
                    javax.swing.JOptionPane.showMessageDialog(null, "Check Kartu Terlebih Dahulu");
                }
                else if(ck==false){
                    javax.swing.JOptionPane.showMessageDialog(null, "Check Portal Terlebih Dahulu");
                }
                else{
                    ck=false;
                    cp=false;
                    Kartu k;
                    k = model.searchKartuById(IdK);
                    if(k.getSaldo()>=tarif){
                        k.setSaldo(k.getSaldo()-tarif);
                        db.query("update kartu set saldo='"+k.getSaldo()+"' where idCard='"+k.getNoKartu()+"';");
                        viewBP.getTfIdCard().setText("");
                        viewBP.getTfPortal().setText("");
                        viewBP.getTfTarif().setText("");
                        viewBP.getTfSisaSaldo().setText(k.getSaldo()+"");
                        if(k.getSaldo()>10000){
                            javax.swing.JOptionPane.showMessageDialog(null, "Berhasil, saldo telah diambil sebesar "+tarif+". Terima Kasih.");
                        }
                        else{
                            javax.swing.JOptionPane.showMessageDialog(null, "Berhasil, saldo telah diambil sebesar "+tarif+". SALDO ANDA SUDAH KURANG DARI Rp. 10.000 SEGERA LAKUKAN PENGISIAN ULANG. Terima Kasih.");
                        }
                    }
                    else{
                        javax.swing.JOptionPane.showMessageDialog(null, "Saldo tidak mencukupi");
                    }
                    
                }
            }
            else if(source==viewBP.getbBack()){
                ck = false;
                cp = false;
                IdK = 0;
                tarif = 0;
                viewBP.setVisible(false);
                viewPU.setVisible(true);
            }
            else if(source==viewHP.getbBack()){
                ck = false;
                cp = false;
                cs = false;
                IdK = 0;
                IdKK = 0;
                IdKP = 0;
                noKtp = null;
                tarif = 0;
                viewHP.setVisible(false);
                viewPU.setVisible(true);
            }
            else if(source==viewHP.getbCheckSaldoCard()){
                Kartu k = model.searchKartuById(Integer.parseInt(viewHP.getTfIdCard().getText()));
                if(k!=null){
                    javax.swing.JOptionPane.showMessageDialog(null, "ID BENAR");
                    IdK = k.getNoKartu();
                    cs = true;
                }
                else{
                    javax.swing.JOptionPane.showMessageDialog(null, "ID SALAH");
                }
            }
            else if(source==viewHP.getButtonConfirmIsi()){
                if(cs==false){
                    javax.swing.JOptionPane.showMessageDialog(null, "ID SALAH");
                }else{
                    cs=false;
                    Kartu k = null;
                    k = model.searchKartuById(IdK);
                    viewHP.getTfIdCard().setText("");
                    k.setSaldo(k.getSaldo()+Integer.parseInt(viewHP.getCbVoucher().getSelectedItem().toString()));
                    db.query("update kartu set saldo='"+k.getSaldo()+"' where idCard='"+k.getNoKartu()+"';");
                    javax.swing.JOptionPane.showMessageDialog(null, "Berhasil, saldo anda sekarang sebesar "+k.getSaldo()+". Terima Kasih telah melakukan pengeisian ulang.");
                }
            }
            else if(source==viewHP.getbCheckTollCard()){
                Kartu k = model.searchKartuById(Integer.parseInt(viewHP.getTextIdCard().getText()));
                Customer c = model.getCusByCardId(k.getNoKartu());
                if(k!=null){
                    javax.swing.JOptionPane.showMessageDialog(null, "ID BENAR");
                    viewHP.getLabelNama().setText(c.getNama());
                    viewHP.getLabelAlamat().setText(c.getAlamat());
                    viewHP.getLabelHp().setText(c.getNoHp());
                    viewHP.getLabelKtp().setText(c.getNoKtp());
                    viewHP.getLabelSaldo().setText(k.getSaldo()+"");
                    noKtp = c.getNoKtp();
                    IdKK = k.getNoKartu();
                    ck = true;
                }
                else{
                    javax.swing.JOptionPane.showMessageDialog(null, "ID SALAH");
                }
            }
            else if(source==viewHP.getButtonDelete()){
                if(ck==true){
                    db.query("Delete from Customer where noKtp='"+noKtp+"'");
                    db.query("Delete from Kartu where noKtp='"+noKtp+"'");
                    Customer c = model.searchCustomerByNoKtp(noKtp);
                    c.setKartu(null);
                    model.removeCus(noKtp);
                   javax.swing.JOptionPane.showMessageDialog(null, "Customer dan Kartu dengan nomor KTP "+noKtp+" telah dihapuskan dari database.");
                   this.resetHP();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Silahkan Check ID Card terlebih dahulu");
                }
            }
            else if(source==viewHP.getButtonEdit()){
                if(ck==true){
                    ck = false;
                    cp = false;
                    cs = false;
                    IdK = 0;
                    IdKP = 0;
                    noKtp = null;
                    tarif = 0;
                    viewEC.setVisible(true);
                    viewHP.setVisible(false);
                    viewEC.getLabelIdCard().setText(IdKK+"");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Check Id telebih dahulu");
                }
            }
            else if(source==viewHP.getButtonNewCard()){
                ck = false;
                cp = false;
                cs = false;
                IdK = 0;
                IdKK = 0;
                IdKP = 0;
                noKtp = null;
                tarif = 0;
                viewHP.setVisible(false);
                viewNC.setVisible(true);
            }
            else if(source==viewHP.getbCheckPortal()){
                Portal p = model.searchPortalByNama(viewHP.getTfNamaPortal().getText());
                if(p!=null){
                    javax.swing.JOptionPane.showMessageDialog(null, "Nama Portal BENAR");
                    viewHP.getLabelIdPortal().setText(p.getIdPortal()+"");
                    viewHP.getLabelTarif().setText(p.getTarif()+"");                    
                    IdKP = p.getIdPortal();
                    cp = true;
                }
                else{
                    javax.swing.JOptionPane.showMessageDialog(null, "Nama Portal SALAH");
                }
            }
            else if(source==viewHP.getButtonEditPortal()){
                if(cp==true){
                    ck = false;
                    cp = false;
                    cs = false;
                    IdK = 0;
                    IdKK = 0;
                    noKtp = null;
                    tarif = 0;
                    viewHP.setVisible(false);
                    viewEP.setVisible(true);
                    viewEP.getLabelIdPortal().setText(IdKP+"");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Check Id telebih dahulu");
                }
            }
            else if(source==viewHP.getButtonNewPortal()){
                ck = false;
                cp = false;
                cs = false;
                IdK = 0;
                IdKK = 0;
                IdKP = 0;
                noKtp = null;
                tarif = 0;
                viewHP.setVisible(false);
                viewNP.setVisible(true);
            }
            else if(source==viewHP.getButtonDeletePortal()){
                if(cp==true){
                    db.query("Delete from Portal where idPortal='"+IdKP+"'");
                    model.removePor(IdKP);
                    javax.swing.JOptionPane.showMessageDialog(null, "Portal dengan nomor IdPortal "+IdKP+" telah dihapuskan dari database.");
                    this.resetHP();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Silahkan Check Portal terlebih dahulu");
                }
            }
            else if(source==viewEC.getButtonCancel()){
                viewHP.setVisible(true);
                viewEC.setVisible(false);
                this.resetHP();
            }
            else if(source==viewNP.getButtonCancel()){
                viewHP.setVisible(true);
                viewNP.setVisible(false);
                this.resetHP();
            }
            else if(source==viewEP.getButtonCancel()){
                viewHP.setVisible(true);
                viewEP.setVisible(false);
                this.resetHP();
            }
            else if(source==viewNC.getButtonCancel()){
                viewHP.setVisible(true);
                viewNC.setVisible(false);
                this.resetHP();
            }
            else if(source==viewEC.getButtonOk()){
               if(viewEC.getTfKtp().getText().equals("") || viewEC.getTfNama().getText().equals("") || viewEC.getTfHp().getText().equals("") || viewEC.getTfAlamat().getText().equals("")){
                   JOptionPane.showMessageDialog(null, "Masih ada yang kosong");
               }
               else{
                   Customer cl = model.getCusByCardId(Integer.parseInt(viewEC.getLabelIdCard().getText()));
                   Customer c = new Customer(viewEC.getTfNama().getText(), viewEC.getTfKtp().getText(), viewEC.getTfHp().getText(), viewEC.getTfAlamat().getText(), cl.getKartu());                   
                   db.query("update Customer Set NoKtp="+c.getNoKtp()+", nama='"+c.getNama()+"', alamat='"+c.getAlamat()+"', noHP='"+c.getNoHp()+"' where noktp="+cl.getNoKtp()+"");
                   model.UpdateCus(Integer.parseInt(viewEC.getLabelIdCard().getText()), c);
                   JOptionPane.showMessageDialog(null, "Update Berhasil");
                   viewHP.setVisible(true);
                   viewEC.setVisible(false);
                   this.resetHP();
               }
            }
            else if(source==viewEP.getButtonOk()){
                if(viewEP.getTfNamaPortal().getText().equals("") || viewEP.getTfTarif().getText().equals("")){
                   JOptionPane.showMessageDialog(null, "Masih ada yang kosong");
               }
               else{
                   Portal pl = model.searchPortalById(Integer.parseInt(viewEP.getLabelIdPortal().getText()));
                   Portal p = new Portal(Integer.parseInt(viewEP.getLabelIdPortal().getText()), Integer.parseInt(viewEP.getTfTarif().getText()), viewEP.getTfNamaPortal().getText());
                   db.query("update portal Set nama='"+p.getNama()+"', tarif="+p.getTarif()+" where idPortal="+Integer.parseInt(viewEP.getLabelIdPortal().getText())+"");
                   model.UpdatePor(p.getIdPortal(), p);
                   JOptionPane.showMessageDialog(null, "Update Berhasil");
                   viewHP.setVisible(true);
                   viewEC.setVisible(false);
                   this.resetHP();
               }
            }
            else if(source==viewNC.getButtonOk()){
                if(viewNC.getTfKtp().getText().equals("") || viewNC.getTfNama().getText().equals("") || viewNC.getTfHp().getText().equals("") || viewNC.getTfAlamat().getText().equals("") || viewNC.getTfIdCard().equals("")){
                   JOptionPane.showMessageDialog(null, "Masih ada yang kosong");
               }
               else{
                   Kartu k = new Kartu(Integer.parseInt(viewNC.getTfIdCard().getText()), 0);
                   Customer c = new Customer(viewNC.getTfNama().getText(), viewNC.getTfKtp().getText(), viewNC.getTfHp().getText(), viewEC.getTfAlamat().getText(), k);                   
                   db.query("INSERT INTO `customer`(`noKtp`, `nama`, `alamat`, `noHp`) VALUES ("+c.getNoKtp()+",'"+c.getNama()+"','"+c.getAlamat()+"','"+c.getNoHp()+"')");
                   db.query("INSERT INTO `kartu`(`idCard`, `saldo`, `noKtp`) VALUES ("+k.getNoKartu()+",0,'"+c.getNoKtp()+"')");
                   model.addCus(c);
                   
                   JOptionPane.showMessageDialog(null, "Insert Berhasil");
                   viewHP.setVisible(true);
                   viewEC.setVisible(false);
                   this.resetHP();
               }
            }
            else if(source==viewNP.getButtonOk()){
               if(viewNP.getTfNamaPortal().getText().equals("") || viewNP.getTfTarif().getText().equals("") || viewNP.getTfIdPortal().getText().equals("")){
                   JOptionPane.showMessageDialog(null, "Masih ada yang kosong");
               }
               else{
                   Portal p = new Portal(Integer.parseInt(viewNP.getTfIdPortal().getText()), Integer.parseInt(viewNP.getTfTarif().getText()), viewNP.getTfNamaPortal().getText());
                   db.query("INSERT INTO `portal`(`idPortal`, `nama`, `tarif`) VALUES ("+p.getIdPortal()+",'"+p.getNama()+"',"+p.getTarif()+")");
                   model.addPor(p);
                   JOptionPane.showMessageDialog(null, "Insert Berhasil");
                   viewHP.setVisible(true);
                   viewEC.setVisible(false);
                   this.resetHP();
               }
            }
        }catch (ArrayIndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null, "Error : Anda belum memilih pada tabel");
        }catch (NullPointerException ex){
            JOptionPane.showMessageDialog(null, "Error : Tabel Kosong");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error : "+ex.getMessage());
        }
        
    }

    public void setViewPU(PilihUser viewPU) {
        this.viewPU = viewPU;
    }

    public void setViewLO(Login viewLO) {
        this.viewLO = viewLO;
    }

    public void setViewBP(BayarPortalPanel viewBP) {
        this.viewBP = viewBP;
    }

    public void setModel(Application model) {
        this.model = model;
    }

    public HomePanel getViewHP() {
        return viewHP;
    }

    public void setViewHP(HomePanel viewHP) {
        this.viewHP = viewHP;
    }
    public void setViewEC(EditProfileForm viewEC) {
        this.viewEC = viewEC;
    }
    public void setViewEP(EditPortalForm viewEP) {
        this.viewEP = viewEP;
    }
    public void setViewNP(NewPortalForm viewNP) {
        this.viewNP = viewNP;
    }
    public void setViewNC(NewTollCard viewNC) {
        this.viewNC = viewNC;
    }
    public void resetHP(){
        viewHP.getTextIdCard().setText("");
        viewHP.getTfIdCard().setText("");
        viewHP.getTfNamaPortal().setText("");
        viewHP.getLabelAlamat().setText("-");
        viewHP.getLabelHp().setText("-");
        viewHP.getLabelIdPegawai().setText("-");
        viewHP.getLabelIdPortal().setText("-");
        viewHP.getLabelKtp().setText("-");
        viewHP.getLabelNama().setText("-");
        viewHP.getLabelNamaPegawai().setText("-");
        viewHP.getLabelSaldo().setText("-");
        viewHP.getLabelTarif().setText("-");
        IdK=0;
        IdKK=0;
        IdKP=0;
        cs=false;
        cp=false;
        ck=false;
    }
}
