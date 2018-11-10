package controleur;


import modele.GameManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener{

    private GameManager gameManager;
    private int x;
    private int y;
    private boolean currentplayer;

    public GameController(GameManager gm, int x, int y, boolean self){
        this.gameManager = gm;
        this.x = x;
        this.y = y;
        this.currentplayer = self;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.currentplayer) {
            System.out.println("Vous avez cliqué sur la case(" + x + ", " + y + ") de votre plateau.");
        } else {
            System.out.println("Vous avez cliqué sur la case(" + x + ", " + y + ") du plateau adverse.");
        }
    }
}
