package vue;

import controleur.GameController;
import modele.GameManager;
import modele.bateaux.Case;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observer;

public class VuePlateaux extends JPanel implements Observer {

    protected JButton boardJoueur[][] = new JButton[10][10];
    protected JButton boardAdversaire[][] = new JButton[10][10];
    private GameManager gm;
    private JPanel contentJoueur;
    private JPanel contentIAdversaire;
    private JPanel afficheur;
    private JPanel selectionBateau;

    public VuePlateaux(GameManager gm){
        super();
        gm.addObserver(this);
        this.gm = gm;
        this.contentIAdversaire = new JPanel();
        this.contentJoueur = new JPanel();
        this.afficheur = new JPanel();
       // this.selectionBateau = new JPanel();
        setAffichage();
    }

    public void setAffichage(){
        this.setLayout(new GridLayout(2, 0));

        //Board parts for self and CPU
        contentIAdversaire.setLayout(new GridLayout(11, 11));
        contentJoueur.setLayout(new GridLayout(11, 11));
        for (int i = 0; i < 11; i++){
            JButton caseindexJ = new JButton(i+"");
            JButton caseindexA = new JButton(i+"");
            caseindexJ.setEnabled(false);
            caseindexA.setEnabled(false);
            contentJoueur.add(caseindexJ);
            contentIAdversaire.add(caseindexA);
        }

        for (int i = 0; i < boardAdversaire.length; i++){
            JButton caseindexJ = new JButton((i+1)+"");
            JButton caseindexA = new JButton((i+1)+"");
            caseindexJ.setEnabled(false);
            caseindexA.setEnabled(false);
            contentJoueur.add(caseindexJ);
            contentIAdversaire.add(caseindexA);
            for (int j = 0; j < boardJoueur.length; j++){
                boardAdversaire[i][j] = new JButton();
                boardAdversaire[i][j].setEnabled(false);
                boardJoueur[i][j] = new JButton();
                boardAdversaire[i][j].addActionListener(new GameController(gm, j, i, false));
                boardJoueur[i][j].addActionListener(new GameController(gm, j, i, true));
                contentIAdversaire.add(boardAdversaire[i][j]);
                contentJoueur.add(boardJoueur[i][j]);
            }
        }

        ArrayList<Case> postionHuman = this.gm.getCasesBateauxH();
        for (Case c : postionHuman){
            boardJoueur[c.getY()][c.getX()].setBackground(Color.GREEN);
        }

        //End of initialisation, show Panels
        this.add(contentIAdversaire);
        this.add(contentJoueur);

    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if (gm.isEst_touche()) {
            boardJoueur[gm.getViseeX() - 1][gm.getViseeY() - 1].setBackground(Color.red);
        }else{
            boardJoueur[gm.getViseeX() - 1][gm.getViseeY() - 1].setBackground(Color.black);
        }
    }
}
