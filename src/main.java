import modele.GameManager;
import vue.VueGeneral;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * The type Main.
 */
public class main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        GameManager gm = new GameManager();
        JFrame f = new JFrame();
        f.setContentPane(new VueGeneral(f, gm));
    }
}
