package rmi.client;

import vue.VueGeneral;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueGeneralClient extends JFrame implements Observer {
    private Modele mod;
    private VueMenuClient vueMenuClient;
    private VueSelectionClient vueSelectionClient;
    private VueJeuClient vueJeuClient;

    public VueGeneralClient(Modele modele){
        super();
        modele.addObserver(this);
        this.mod = modele;
        this.vueMenuClient = new VueMenuClient(this.mod);
        setAffichage();
    }

    public void setAffichage(){
        this.setTitle("Bataille Navale");
        this.setPreferredSize(new Dimension(800,800));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(vueMenuClient);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (mod.getLancerSelection()){
            this.vueSelectionClient = new VueSelectionClient(this.mod);
            this.setContentPane(vueSelectionClient);
            this.invalidate();
            this.validate();
            this.pack();
            this.setVisible(true);
        }
        if (mod.getWaitingJeu()){
            this.setContentPane(vueMenuClient);
            this.invalidate();
            this.validate();
            this.pack();
            this.setVisible(true);
        }
        if (mod.getLancerJeu()){
            this.vueJeuClient = new VueJeuClient(this.mod);
            this.setContentPane(vueJeuClient);
            this.invalidate();
            this.validate();
            this.pack();
            this.setVisible(true);
        }
    }
}
