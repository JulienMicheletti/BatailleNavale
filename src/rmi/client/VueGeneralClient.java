package rmi.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

public class VueGeneralClient extends JFrame implements Observer {
    private Modele mod;
    private VueMenuClient vueMenuClient;
    private VueSelectionClient vueSelectionClient;
    private VueJeuClient vueJeuClient;
    private JPanel otherIsClose;

    public VueGeneralClient(Modele modele){
        super();
        modele.addObserver(this);
        this.mod = modele;
        this.vueMenuClient = new VueMenuClient(this.mod);
        this.otherIsClose = new JPanel();
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                modele.clientClose();
                setVisible(false);
                dispose();
            }
        });
        setAffichage();
    }

    public void setAffichage(){
        this.otherIsClose.add(new JLabel("L'adversaire est partit ...", SwingConstants.CENTER));
        this.setTitle("Bataille Navale");
        this.setPreferredSize(new Dimension(500,500));
       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(vueMenuClient);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (mod.getLancerSelection()){
            this.setPreferredSize(new Dimension(800,800));
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
            this.setPreferredSize(new Dimension(1200,500));
            this.vueJeuClient = new VueJeuClient(this.mod);
            this.setContentPane(vueJeuClient);
            this.invalidate();
            this.validate();
            this.pack();
            this.setVisible(true);
        }
        if (mod.getLeave()){
            this.setVisible(false);
            dispose();
        }
        if (mod.isOtherClose()){
            this.setPreferredSize(new Dimension(500, 500));
            this.setContentPane(otherIsClose);
            this.invalidate();
            this.validate();
            this.pack();
            this.setVisible(true);
        }
    }
}
