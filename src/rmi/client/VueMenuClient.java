package rmi.client;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueMenuClient extends JPanel implements Observer {
    private Modele modele;
    private JLabel waiting;
    private JPanel monBoard;
    private JButton[][] mesCases;

    public VueMenuClient(Modele mod){
        super();
        this.setLayout(new BorderLayout());
        this.modele = mod;
        this.modele.addObserver(this);
        this.waiting = new JLabel("En attente de l'autre joueur ...");
        this.monBoard = new JPanel();
        this.mesCases = new JButton[10][10];
        setAfficher();
    }

    public void setAfficher(){
        if (modele.getPlayerConnected() < 2) {
            this.add(this.waiting, BorderLayout.CENTER);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (modele.getWaitingJeu()){
            this.waiting.setText("Attente de la pose des bateaux du J2 ...");
            this.remove(waiting);
            this.add(waiting, BorderLayout.NORTH);
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
