package vue;

import controleur.PositionController;
import javafx.beans.Observable;
import modele.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;

public class VueSelection extends JPanel implements Observer {
    private GameManager gameManager;
    protected JButton boardJoueur[][] = new JButton[10][10];

    public VueSelection(GameManager gm){
        super();
        gm.addObserver(this);
        this.gameManager = gm;
        setAffichage();
    }

    public void setAffichage(){
        this.setLayout(new GridLayout(11, 11));

        for (int i = 0; i < 11; i++){
            JButton caseindexJ = new JButton(i+"");
            caseindexJ.setEnabled(false);
            this.add(caseindexJ);
        }


        for (int i = 0; i < boardJoueur.length; i++){
            JButton caseindexJ = new JButton((i+1)+"");
            caseindexJ.setEnabled(false);
            this.add(caseindexJ);
            for (int j = 0; j < boardJoueur.length; j++){
                boardJoueur[i][j] = new JButton();
                boardJoueur[i][j].addMouseListener(new PositionController(this.gameManager, j, i));
                this.add(boardJoueur[i][j]);
            }
        }
    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}
