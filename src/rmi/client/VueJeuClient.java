package rmi.client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VueJeuClient extends JPanel implements Observer {
    private Modele modele;
    private JButton[][] boardJoueur;
    private JButton[][] boardAdversaire;
    private JPanel contentJoueur;
    private JPanel contentAdversaire;

    public VueJeuClient(Modele mod){
        this.modele = mod;
        this.modele.addObserver(this);
        this.boardJoueur = new JButton[10][10];
        this.boardAdversaire = new JButton[10][10];
        this.contentAdversaire = new JPanel();
        this.contentJoueur = new JPanel();
        setAffichage();
    }

    public void setAffichage(){
        this.setLayout(new GridLayout(2, 1));
        this.contentJoueur.setLayout(new GridLayout(11, 11));
        this.contentAdversaire.setLayout(new GridLayout(11, 11));
        Dimension tailleCase = new Dimension(30, 30);
        for (int i = 0; i < 11; i++){
            JButton index = new JButton(i+"");
            JButton index2 = new JButton(i+"");
            index.setPreferredSize(tailleCase);
            index2.setPreferredSize(tailleCase);
            index.setEnabled(false);
            index2.setEnabled(false);
            this.contentJoueur.add(index);
            this.contentAdversaire.add(index2);
        }
        for (int i = 0; i < boardJoueur.length; i++){
            JButton index = new JButton(i+1+"");
            JButton index2 = new JButton(i+1+"");
            index.setEnabled(false);
            index2.setEnabled(false);
            index2.setPreferredSize(tailleCase);
            index.setPreferredSize(tailleCase);
            this.contentJoueur.add(index);
            this.contentAdversaire.add(index2);
            for (int j = 0; j < boardJoueur.length; j++){
                boardJoueur[i][j] = new JButton();
                boardAdversaire[i][j] = new JButton();
                boardJoueur[i][j].setPreferredSize(tailleCase);
                boardAdversaire[i][j].setPreferredSize(tailleCase);
                boardJoueur[i][j].setEnabled(false);
                contentJoueur.add(boardJoueur[i][j]);
                contentAdversaire.add(boardAdversaire[i][j]);
            }
        }
        ArrayList<CaseClient> cJ = modele.getCasesJ();
        for (CaseClient c : cJ)
            boardJoueur[c.getY()][c.getX()].setBackground(Color.GREEN);
        this.add(contentAdversaire);
        this.add(contentJoueur);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
