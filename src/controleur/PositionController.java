package controleur;

import modele.GameManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PositionController implements MouseListener {

    private GameManager gm;
    private int x;
    private int y;

    public PositionController(GameManager gameManager, int x, int y){
        this.gm = gameManager;
        this.x = x;
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gm.validerSelection();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        gm.setSelection(x, y);
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
