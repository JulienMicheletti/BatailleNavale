package rmi.serveur;

import java.rmi.Remote;

public interface ServerInterface extends Remote {

    public int[][] setSelection(int x, int y, int taille);

    public int[][] validerSelection();

    public void setOrientation(int orientation);

    public void valider();
}
