import modele.GameManager;
import vue.VueGeneral;

import javax.swing.*;

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
        GameManager gm = new GameManager();
        JFrame f = new JFrame();
        f.setContentPane(new VueGeneral(f, gm));
    }
}
