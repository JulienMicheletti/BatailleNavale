package rmi.serveur;

import modele.bateaux.Case;

import java.rmi.Remote;
import java.util.ArrayList;

public interface ServerInterface extends Remote {

    public int[][] setSelection(int x, int y, int taille);

    public int[][] validerSelection();

    public void setOrientation(int orientation);

    public void valider();
}
