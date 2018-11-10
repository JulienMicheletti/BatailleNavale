package controleur;


import modele.GameManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener{

    private GameManager gameManager;
    private int x;
    private int y;

    public GameController(GameManager gm, int x, int y){
        this.gameManager = gm;
        this.x = x;
        this.y = y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Vous avez cliqué sur la case("+x+", "+y+")");
    }
}
