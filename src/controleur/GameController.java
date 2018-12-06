package controleur;


import modele.GameManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

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
        gameManager.tirer(x, y);
    }
}
