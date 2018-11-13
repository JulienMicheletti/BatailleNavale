package vue;

import modele.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueGeneral implements Observer {
    public VueGeneral(GameManager gm){
        JFrame f=new JFrame();
        f.setTitle("Bataille Navale");
        f.setPreferredSize(new Dimension(800,800));

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(new VuePlateaux(gm));
        f.pack();
        f.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
