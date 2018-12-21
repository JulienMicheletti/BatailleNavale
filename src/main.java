import modele.GameManager;
import vue.VueGeneral;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        GameManager gm = new GameManager();
        JFrame f = new JFrame();
        f.setContentPane(new VueGeneral(f, gm));
    }
}
