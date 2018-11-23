import modele.GameManager;
import rmi.client.Modele;
import rmi.client.VueSelectionClient;
import vue.VueGeneral;
import vue.VuePlateaux;

import javax.swing.*;

public class main {
    public static void main(String[] args){
        GameManager gm = new GameManager();
        JFrame f = new JFrame();
        f.setContentPane(new VueGeneral(f,gm));
    }
}
