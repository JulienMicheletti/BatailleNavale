package rmi.serveur;

import rmi.client.CaseClient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote {

    int[][] setSelection(int x, int y, int taille) throws RemoteException;
    int[][] validerSelection() throws RemoteException;
    void switchOrientation() throws RemoteException;
    boolean isValide() throws RemoteException;
    boolean valider() throws RemoteException;
    ArrayList<CaseClient> getCasesJoueur() throws RemoteException;
    void tirer(int x, int y) throws RemoteException;
    int[][] getPlateauJ1() throws RemoteException;
    int[][] getPlateauJ2() throws RemoteException;
}
