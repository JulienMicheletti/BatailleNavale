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
    private JFrame frame;
    private boolean shown;

    public VuePlateaux(JFrame frame,GameManager gm){
        super();
        this.frame = frame;
        gm.addObserver(this);
        this.shown = false;
        this.gm = gm;
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

        ArrayList<Case> postionHuman = this.gm.getCasesBateauxH();
        for (Case c : postionHuman){
            boardJoueur[c.getY()][c.getX()].setBackground(Color.GREEN);
        }
        //End of initialisation, show Panels
        JPanel adv = new JPanel();
        adv.setLayout(new BoxLayout(adv,BoxLayout.Y_AXIS));
        JLabel jlab1 = new JLabel("Plateau adverse : ");
        jlab1.setVerticalAlignment(JLabel.CENTER);
        jlab1.setHorizontalAlignment(JLabel.CENTER);
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
            JRadioButton first = new JRadioButton("Tir -|- (3 max)");
            JRadioButton second = new JRadioButton("Tir X (2 max)");
            JRadioButton third = new JRadioButton("Tir simple");
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
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
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
        if(!gm.getCurrentPlayer()) {
            //IA
            if (gm.isEst_touche()) {
                boardJoueur[gm.getViseeX()-1][gm.getViseeY()-1].setBackground(Color.red);
            } else {
                boardJoueur[gm.getViseeX()-1][gm.getViseeY()-1].setBackground(Color.black);
            }
        } else {
            //HUMAIN
            if (gm.isEst_touche()) {
                boardAdversaire[gm.getViseeX() - 1][gm.getViseeY() - 1].setBackground(Color.red);
            } else {
                boardAdversaire[gm.getViseeX() - 1][gm.getViseeY() - 1].setBackground(Color.black);
            }
            boardAdversaire[gm.getViseeX()-1][gm.getViseeY()-1].setEnabled(false);
        }
    }

    private void restart() {
        frame.remove(this);
        frame.setPreferredSize(new Dimension(600,600));
        frame.setContentPane(new VueGeneral(frame,gm));
        frame.invalidate();
        frame.validate();
        frame.pack();
        frame.setVisible(true);
    }
}
