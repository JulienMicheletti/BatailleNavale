package controleur;


import modele.GameManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Game controller.
 */
public class GameController implements ActionListener{

    private GameManager gameManager;
    private int x;
    private int y;

    /**
     * Instantiates a new Game controller.
     *
     * @param gm the gm
     * @param x  the x
     * @param y  the y
     */
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
