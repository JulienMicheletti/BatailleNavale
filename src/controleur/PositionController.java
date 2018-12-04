package controleur;

import modele.GameManager;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

public class PositionController implements MouseListener, Serializable {

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
        if (SwingUtilities.isLeftMouseButton(e)) gm.validerSelection();
        if (SwingUtilities.isRightMouseButton(e)){
            gm.switchOrientation();
            gm.setSelection(x, y);
        }
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
        this.gm.caseExited();
    }
}
