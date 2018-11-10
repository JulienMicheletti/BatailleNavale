package vue;

import javafx.beans.Observable;
import modele.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;

public class VuePlateaux implements Observer {
    protected JButton boardJoueur[][] = new JButton[10][10];
    protected JButton boardAdversaire[][] = new JButton[10][10];
    private JPanel contentPane;
    private JPanel contentJoueur;
    private JPanel contentIAdversaire;
    int coteAAfficher;

    public VuePlateaux(GameManager gm){
        gm.addObserver(this);
        contentPane = new JPanel();
        contentIAdversaire = new JPanel();
        contentJoueur = new JPanel();
        setAffichage();
    }

    public void setAffichage(){
        JFrame f=new JFrame();
        f.setTitle("Bataille Navale");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setContentPane(contentPane);

        contentPane.setLayout(new GridLayout(2, 0));

        contentPane.add(contentIAdversaire);
        contentPane.add(contentJoueur);

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
                contentIAdversaire.add(boardAdversaire[i][j]);
                contentJoueur.add(boardJoueur[i][j]);
            }
        }

        f.pack();
        f.setVisible(true);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}
