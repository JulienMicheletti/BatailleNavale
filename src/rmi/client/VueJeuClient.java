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
    private JPanel gameLayout;
    private JPanel contentJoueur;
    private JPanel contentAdversaire;
    private JLabel information;

    public VueJeuClient(Modele mod){
        this.modele = mod;
        this.modele.addObserver(this);
        this.boardJoueur = new JButton[10][10];
        this.boardAdversaire = new JButton[10][10];
        this.contentAdversaire = new JPanel();
        this.contentJoueur = new JPanel();
        this.gameLayout = new JPanel();
        this.information = new JLabel("Informations", SwingConstants.CENTER);
        setAffichage();
    }

    public void setAffichage(){
        this.setLayout(new BorderLayout());
        if (modele.myTurn())
            this.information.setText("A votre tour de jouer");
        else
            this.information.setText("A l'adversaire de jouer");
        this.add(information, BorderLayout.NORTH);
        this.gameLayout.setLayout(new GridLayout(2, 1));
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
                boardAdversaire[i][j].addActionListener(new GameClientListener(modele, j, i));
                contentAdversaire.add(boardAdversaire[i][j]);
            }
        }
        ArrayList<CaseClient> cJ = modele.getCasesJ();
        for (CaseClient c : cJ)
            boardJoueur[c.getY()][c.getX()].setBackground(Color.GREEN);
        this.gameLayout.add(contentAdversaire);
        this.gameLayout.add(contentJoueur);
        this.add(gameLayout, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg) {
        int[][] plateauJ1 = modele.getPlateauJ1();
        int[][] plateauJ2 = modele.getPlateauJ2();
        for (int i = 0; i < plateauJ1.length; i++){
            for (int j = 0; j < plateauJ1[i].length; j++){
                if (plateauJ2[i][j] == 1) {
                    boardJoueur[i][j].setBackground(Color.red);
                    boardJoueur[i][j].setEnabled(false);
                } else if (plateauJ2[i][j] == 2) {
                    boardJoueur[i][j].setBackground(Color.black);
                    boardJoueur[i][j].setEnabled(false);
                }
                if (plateauJ1[i][j] == 1) {
                    boardAdversaire[i][j].setBackground(Color.red);
                    boardAdversaire[i][j].setEnabled(false);
                } else if (plateauJ1[i][j] == 2) {
                    boardAdversaire[i][j].setBackground(Color.BLACK);
                    boardAdversaire[i][j].setEnabled(false);
                }
            }
        }
        if (this.modele.getVictory() > 0){
              for (JButton[] tab : boardAdversaire){
                  for (JButton button : tab)
                      button.setEnabled(false);
              }
            if (this.modele.getVictory() == this.modele.getID()){
                this.information.setText("Vous avez gagné la partie !");
            }  else {
                this.information.setText("Vous avez perdu la partie ...");
            }
        }
    }
}
