package proyecto;

import Analyzers.Generate;
import Interface.Principal;
import Interface.Test;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
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
        }

        
        Generate g = new Generate();

        UIManager.put("defaultFont", new Font("Montserrat", 0, 13));
        Principal p = new Principal();
        p.setVisible(true);
//        Test T = new Test();
//        T.setVisible(true);
    }

}
