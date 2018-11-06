package vue;

import javafx.beans.Observable;

import javax.swing.*;
import java.util.Observer;

public class VuePlateaux implements Observer {
    protected JButton boardJoueur[][] = new JButton[10][10];
    protected JButton boardAdversaire[][] = new JButton[10][10];
    int coteAAfficher;

    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}
