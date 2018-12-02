package rmi.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameClientListener implements ActionListener{
    private int x;
    private int y;
    private Modele mod;

    public GameClientListener(Modele mod, int x, int y){
        this.mod = mod;
        this.x = x;
        this.y = y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.mod.tirer(x, y);
    }
}
