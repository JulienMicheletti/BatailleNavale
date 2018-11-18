package controleur;


import modele.GameManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener{

    private GameManager gameManager;
    private int x;
    private int y;
    private boolean current;

    public GameController(GameManager gm, int x, int y, boolean current){
        this.gameManager = gm;
        this.x = x;
        this.y = y;
        this.current = current;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameManager.tirer(x, y);
    }
}
