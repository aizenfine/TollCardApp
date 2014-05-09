package Model;

/**
 *
 * @author Izzan (local)
 */

import Controller.Controller;
import Gui.*;
import com.sun.media.sound.AlawCodec;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Driver {
     public static void main(String[] args) throws Exception{
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Application a=new Application();
        Controller c=new Controller();
        PilihUser PU=new PilihUser();
        Login LO=new Login();
        
        a.LoadCustomer();
        a.LoadPegawai();
        a.LoadPortal();
        a.LoadKartu();
        
        c.setModel(a);
        c.setViewPU(PU);
        c.setViewBP(new BayarPortalPanel());
        c.setViewLO(new Login());
        c.setViewHP(new HomePanel());
        c.setViewEC(new EditProfileForm());
        c.setViewEP(new EditPortalForm());
        c.setViewNC(new NewTollCard());
        c.setViewNP(new NewPortalForm());
        c.setListener();
        PU.setVisible(true);
     }
     
      
}
