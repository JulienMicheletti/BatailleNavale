package vue;

import controleur.GameController;
import modele.GameManager;
import javax.swing.*;
import java.awt.*;
import java.util.Observer;

public class VuePlateaux extends JPanel implements Observer {

    protected JButton boardJoueur[][] = new JButton[10][10];
    protected JButton boardAdversaire[][] = new JButton[10][10];
    private GameManager gm;
    private JPanel contentJoueur;
    private JPanel contentIAdversaire;
    int coteAAfficher;

    public VuePlateaux(GameManager gm){
        super();
        gm.addObserver(this);
        this.gm = gm;
        contentIAdversaire = new JPanel();
        contentJoueur = new JPanel();
        setAffichage();
    }

    public void setAffichage(){

        this.setLayout(new GridLayout(2, 0));
        this.add(contentIAdversaire);
        this.add(contentJoueur);

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
                boardJoueur[i][j] = new JButton();
                boardAdversaire[i][j].addActionListener(new GameController(gm, j, i));
                boardJoueur[i][j].addActionListener(new GameController(gm, j, i));
                contentIAdversaire.add(boardAdversaire[i][j]);
                contentJoueur.add(boardJoueur[i][j]);
            }
        }
    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}
