package rmi.serveur;

import com.sun.org.apache.regexp.internal.RE;
import modele.bateaux.Case;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote {

    int[][] setSelection(int x, int y, int taille) throws RemoteException;
    int[][] validerSelection() throws RemoteException;
    void setOrientation(int orientation) throws RemoteException;
    void valider() throws RemoteException;
}
