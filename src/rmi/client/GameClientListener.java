package rmi.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Game client listener.
 */
public class GameClientListener implements ActionListener{
    private int x;
    private int y;
    private Modele mod;

    /**
     * Instantiates a new Game client listener.
     *
     * @param mod the mod
     * @param x   the x
     * @param y   the y
     */
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
