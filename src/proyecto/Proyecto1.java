package proyecto;

import Interface.Principal;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Proyecto1 {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println(ex);
        }
        UIManager.put("defaultFont", new Font("Montserrat", 0, 13));
        Principal p = new Principal();
        p.setVisible(true);
    }

}
