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
       /* if (/**gameManager.getCurrentPlayer() == true && urrent == true) {
            System.out.println("P1Vous avez cliqué sur la case(" + x + ", " + y + ") de votre plateau.");
            gameManager.tirer(x+1, y+1);
            gameManager.setCurrentPlayer(false);
        } else if (/**gameManager.getCurrentPlayer() == false && current == false){
            gameManager.setCurrentPlayer(true);
            System.out.println("Vous avez cliqué sur la case(" + x + ", " + y + ") du plateau adverse.");
        }**/
        gameManager.tirer(x, y);
    }
}
