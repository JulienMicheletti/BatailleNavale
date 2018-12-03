package rmi.serveur;

import rmi.client.CaseClient;
import rmi.client.ClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote {

    void connexion(ClientInterface client) throws RemoteException;
    //
    int[][] setSelection(int x, int y, int taille, int player) throws RemoteException;
    //
    int[][] validerSelection(int player) throws RemoteException;
    //
    void switchOrientation(int player) throws RemoteException;
    //
    boolean isValide(int player) throws RemoteException;
    //
    boolean valider(int player) throws RemoteException;
    //
    ArrayList<CaseClient> getCasesJoueur(int player) throws RemoteException;
    //
    void tirer(int x, int y, int player) throws RemoteException;
    int[][] getPlateauJ1() throws RemoteException;
    int[][] getPlateauJ2() throws RemoteException;
    boolean askConnect() throws RemoteException;
    int getPlayerConnected() throws RemoteException;
    int getVictory() throws RemoteException;;
    void askLancerJeu() throws RemoteException;
}
