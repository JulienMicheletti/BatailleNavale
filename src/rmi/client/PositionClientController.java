package controleur;

import modele.GameManager;
import rmi.client.Modele;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PositionClientController implements MouseListener {

    private Modele modele;
    private int x;
    private int y;

    public PositionClientController(Modele modele, int x, int y){
        this.modele = modele;
        this.x = x;
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) modele.validerSelection();
        if (SwingUtilities.isRightMouseButton(e)){
            modele.switchOrientation();
            modele.setSelection(x, y);
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
        modele.setSelection(x, y);
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
