package rmi.serveur;


import modele.GameManager;
import modele.bateaux.Case;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerImplementation extends UnicastRemoteObject implements ServerInterface {
    private GameManager gameManager;

    protected ServerImplementation(GameManager gm) throws RemoteException {
        this.gameManager = gm;
    }

    @Override
    public int[][] setSelection(int x, int y, int taille) {
        int[][] plateau = new int[10][10];
        for (int i = 0; i < plateau.length; i++){
            for (int j = 0; j < plateau[i].length; j++){
                plateau[i][j] = 0;
            }
        }
        this.gameManager.setTaille(taille);
        this.gameManager.setSelection(x, y);
        ArrayList<Case> bateaux = this.gameManager.getCasesBateauxH();
        for (Case c : bateaux){
            if (c.getX() >= 0 && c.getY() >= 0){
                plateau[c.getY()][c.getX()] = 2;
            }
        }
        Case[] cases = this.gameManager.getSelectionBateau();
        for (Case c : cases){
            int casex=c.getX();
            int casey=c.getY();
            if (casex >= 0 && casey >= 0) {
                if (plateau[casey][casex] == 2)
                    plateau[casey][casex] = 3;
                else
                    plateau[casey][casex] = 1;
            }
        }
        return plateau;
    }

    @Override
    public int[][] validerSelection() {
        int[][] plateau = new int[10][10];
        for (int i = 0; i < plateau.length; i++){
            for (int j = 0; j < plateau[i].length; j++){
                plateau[i][j] = 0;
            }
        }
        this.gameManager.validerSelection();
        ArrayList<Case> bateaux = this.gameManager.getCasesBateauxH();
        for (Case c : bateaux)
            plateau[c.getY()][c.getX()] = 2;
        return plateau;
    }

    @Override
    public void setOrientation(int orientation) {
        this.gameManager.setOrientation(orientation);
    }

    @Override
    public void valider() {

    }

    public static void main(String[] args){
        try {
            ServerImplementation serverImplementation = new ServerImplementation(new GameManager());
            Registry registry = LocateRegistry.createRegistry(8081);
            registry.bind("bataille_navale", serverImplementation);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
