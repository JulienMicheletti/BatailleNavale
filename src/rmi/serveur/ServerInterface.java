package rmi.serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    int[][] setSelection(int x, int y, int taille) throws RemoteException;
    int[][] validerSelection() throws RemoteException;
    void switchOrientation() throws RemoteException;
    void valider() throws RemoteException;
}
