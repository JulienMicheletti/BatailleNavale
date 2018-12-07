package vue;

import controleur.GameController;
import controleur.SaveController;
import modele.GameManager;
import modele.bateaux.Case;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observer;

public class VuePlateaux extends JPanel implements Observer, Serializable {

    protected JButton boardJoueur[][] = new JButton[10][10];
    protected JButton boardAdversaire[][] = new JButton[10][10];
    private GameManager gm;
    private JPanel contentJoueur;
    private JPanel contentIAdversaire;
    private JPanel afficheur;
    private JPanel selectionBateau;
    private JFrame frame;
    private boolean shown;
    private JRadioButton second;
    private JRadioButton first;
    private JButton save;
    private JRadioButton third;

    public VuePlateaux(JFrame frame,GameManager gm){
        super();
        this.frame = frame;
        frame.setPreferredSize(new Dimension(650,700));
        gm.addObserver(this);
        this.shown = false;
        this.gm = gm;
        this.save = new JButton("sauvegarder");
        this.contentIAdversaire = new JPanel();
        this.contentJoueur = new JPanel();
        this.afficheur = new JPanel();
        setAffichage();
    }

    public void setAffichage(){
        //this.setLayout(new GridLayout(2, 0));
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
                boardJoueur[i][j] = new JButton();
                boardJoueur[i][j].setEnabled(false);
                boardAdversaire[i][j].addActionListener(new GameController(gm, j, i));
                boardJoueur[i][j].addActionListener(new GameController(gm, j, i));
                contentIAdversaire.add(boardAdversaire[i][j]);
                contentJoueur.add(boardJoueur[i][j]);
            }
        }

        save.addActionListener(new SaveController(gm));
        ArrayList<Case> postionHuman = this.gm.getCasesBateauxH();
        for (Case c : postionHuman){
            boardJoueur[c.getY()][c.getX()].setBackground(Color.GREEN);
        }
        //End of initialisation, show Panels
        JPanel adv = new JPanel();
        save.setVerticalAlignment(JLabel.EAST);
        adv.setLayout(new BoxLayout(adv,BoxLayout.Y_AXIS));
        JLabel jlab1 = new JLabel("Plateau adverse : ");
        jlab1.setVerticalAlignment(JLabel.CENTER);
        jlab1.setHorizontalAlignment(JLabel.CENTER);
        adv.add(save);
        adv.add(jlab1);
        adv.add(this.contentIAdversaire);
        JPanel self = new JPanel();
        self.setLayout(new BoxLayout(self,BoxLayout.Y_AXIS));
        JLabel jlab2 = new JLabel("Votre plateau : ");
        jlab2.setVerticalAlignment(JLabel.CENTER);
        jlab2.setHorizontalAlignment(JLabel.CENTER);
        self.add(jlab2);
        self.add(this.contentJoueur);
        this.add(adv, BorderLayout.CENTER);
        this.add(self,BorderLayout.CENTER);


        //Check if munition game
        if (this.gm.isMunitionGame()){
            this.first = new JRadioButton("Tir + (3 restant)");
            this.first.addActionListener(e -> gm.setMunition(2));
            this.second = new JRadioButton("Tir X (3 restant)");
            this.second.addActionListener(e -> gm.setMunition(1));
            this.third = new JRadioButton("Tir simple (illimité)");
            this.third.addActionListener(e -> gm.setMunition(0));
            ButtonGroup bg = new ButtonGroup();
            bg.add(first);
            bg.add(second);
            bg.add(third);
            third.setSelected(true);
            this.add(Box.createHorizontalStrut(150));
            this.add(first,BorderLayout.SOUTH);
            this.add(second,BorderLayout.SOUTH);
            this.add(third,BorderLayout.SOUTH);
        }

        int[][] tab = gm.getPlayerPlateau();
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab[i].length; j++){
                if (tab[i][j] == 1){
                    boardAdversaire[i][j].setBackground(Color.red);
                    boardAdversaire[i][j].setEnabled(false);
                }else if (tab[i][j] == 2){
                    boardAdversaire[i][j].setBackground(Color.black);
                    boardAdversaire[i][j].setEnabled(false);
                }
            }
        }

        int[][] tabIA = gm.getIAPlateau();
        for (int i = 0; i < tabIA.length; i++){
            for (int j = 0; j < tabIA[i].length; j++){
                if (tabIA[i][j] == 1){
                    boardJoueur[i][j].setBackground(Color.red);
                    boardJoueur[i][j].setEnabled(false);
                }else if (tabIA[i][j] == 2){
                    boardJoueur[i][j].setBackground(Color.black);
                    boardJoueur[i][j].setEnabled(false);
                }
            }
        }
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if (this.gm.isMunitionGame()){
            this.first.setText("Tir + ("+this.gm.getCrossMunition()+" restant)");
            if (this.gm.getCrossMunition() == 0){
                this.first.setEnabled(false);
                this.third.setSelected(true);
            }
            this.second.setText("Tir X ("+this.gm.getXMunition()+" restant)");
            if (this.gm.getXMunition() == 0){
                this.second.setEnabled(false);
                this.third.setSelected(true);
            }
        }
        //IA
        Case caseJ2 = gm.getCaseViseeJ2();
        if (caseJ2.getX() >= 0 && caseJ2.getY() >= 0) {
            if (caseJ2.getToucher()) {
                boardJoueur[caseJ2.getY()][caseJ2.getX()].setBackground(Color.red);
                gm.setPlateauIA(caseJ2.getY(), caseJ2.getX(), 1);
            } else {
                boardJoueur[caseJ2.getY()][caseJ2.getX()].setBackground(Color.black);
                gm.setPlateauIA(caseJ2.getY(), caseJ2.getX(), 2);
            }
        }

        //HUMAIN
        Case caseJ1 = gm.getCaseViseeJ1();
        if (caseJ1.getX() >= 0 && caseJ1.getY() >= 0) {
            if (caseJ1.getToucher()) {
                boardAdversaire[caseJ1.getY()][caseJ1.getX()].setBackground(Color.red);
                gm.setPlateauHumain(caseJ1.getY(), caseJ1.getX(), 1);
            } else {
                boardAdversaire[caseJ1.getY()][caseJ1.getX()].setBackground(Color.black);
                gm.setPlateauHumain(caseJ1.getY(), caseJ1.getX(), 2);
            }
            boardAdversaire[caseJ1.getY()][caseJ1.getX()].setEnabled(false);
        }
        for (Case c : gm.getCaseColatJ1()){
            if (c.getX() >= 0 && c.getY() >= 0) {
                if (c.getToucher()) {
                    boardAdversaire[c.getY()][c.getX()].setBackground(Color.red);
                    gm.setPlateauHumain(c.getY(), c.getX(), 1);
                }
                else {
                    boardAdversaire[c.getY()][c.getX()].setBackground(Color.black);
                    gm.setPlateauHumain(c.getY(), c.getX(), 2);
                }

                boardAdversaire[c.getY()][c.getX()].setEnabled(false);
            }
        }
        if (this.gm.getVictory() == -1 && !this.shown){
            this.shown = true;
            int result = JOptionPane.showConfirmDialog(this, "L'ordinateur vous a battu !", "Défaite !", JOptionPane.DEFAULT_OPTION);
            if (result == JOptionPane.OK_OPTION) this.restart();
            if (result == JOptionPane.CLOSED_OPTION) System.exit(0);
        }
        if (this.gm.getVictory() == 1 && !this.shown){
            this.shown = true;
            int result = JOptionPane.showConfirmDialog(this, "Vous avez vaincu !", "Victoire !", JOptionPane.DEFAULT_OPTION);
            if (result == JOptionPane.OK_OPTION) this.restart();
            if (result == JOptionPane.CLOSED_OPTION) System.exit(1);
        }
    }


    private void restart() {
        this.gm.resetGame();
        frame.remove(this);
        frame.setPreferredSize(new Dimension(600,600));
        frame.setContentPane(new VueGeneral(frame,gm));
        frame.invalidate();
        frame.validate();
        frame.pack();
        frame.setVisible(true);
    }
}
