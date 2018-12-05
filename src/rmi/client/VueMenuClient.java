package rmi.client;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueMenuClient extends JPanel implements Observer {
    private Modele modele;
    private JPanel waitingBoard;
    private JLabel waiting;
    private JPanel monBoard;
    private JButton[][] mesCases;
    private JComboBox<String> comboBox;
    private JButton validerEpoque;

    public VueMenuClient(Modele mod){
        super();
        this.setLayout(new BorderLayout());
        this.modele = mod;
        this.modele.addObserver(this);
        this.waitingBoard = new JPanel();
        this.waiting = new JLabel("En attente de l'autre joueur ...", SwingConstants.CENTER);
        this.monBoard = new JPanel();
        this.mesCases = new JButton[10][10];
        this.validerEpoque = new JButton("Valider");
        setAfficher();
    }

    public void setAfficher(){
        String[] choices = {"XVI Siecle", "XX Siecle", "XXII Siecle"};
        this.comboBox = new JComboBox(choices);
        comboBox.setToolTipText("XVI : Bataille navale normale \n XX : Bateaux ont 1 pv \n XXII : Munitions");
        comboBox.addActionListener(e -> this.modele.setFactory(comboBox.getSelectedIndex()));
        this.validerEpoque.addActionListener(e -> modele.validerEpoque());
        this.add(waitingBoard, BorderLayout.CENTER);
        this.waitingBoard.add(this.waiting, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (modele.getChoixEpoque()){
            if (modele.getID() == 1) {
                this.waiting.setText("Choisissez une époque :");
                this.waitingBoard.add(this.comboBox, BorderLayout.CENTER);
                this.waitingBoard.add(this.validerEpoque, BorderLayout.CENTER);
            } else {
                this.waiting.setText("Le joueur 1 choisit l'époque ...");
            }
        }
        if (modele.getWaitingJeu()){
            this.waitingBoard.remove(this.validerEpoque);
            this.waitingBoard.remove(this.comboBox);
            this.waiting.setText("Attente de la pose des bateaux du J2 ...");
            this.remove(waitingBoard);
            this.add(waitingBoard, BorderLayout.NORTH);
            this.add(monBoard, BorderLayout.CENTER);
            monBoard.setLayout(new GridLayout(11, 11));
            for (int i = 0; i < 11; i++){
                JButton caseindexJ = new JButton(i+"");
                caseindexJ.setEnabled(false);
                monBoard.add(caseindexJ);
            }
            for (int i = 0; i < mesCases.length; i++){
                JButton caseindexJ = new JButton((i+1)+"");
                caseindexJ.setEnabled(false);
                monBoard.add(caseindexJ);
                for (int j = 0; j < mesCases.length; j++){
                    mesCases[i][j] = new JButton();
                    mesCases[i][j].setBackground(Color.CYAN);
                    monBoard.add(mesCases[i][j]);
                }
            }
            for (CaseClient c : modele.getCasesJ()){
                mesCases[c.getY()][c.getX()].setBackground(Color.GREEN);
            }
        }
    }
}
