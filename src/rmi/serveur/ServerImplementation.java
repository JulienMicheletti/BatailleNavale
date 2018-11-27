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
    private int victory;

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
        ArrayList<Case> bateaux = this.gameManager.getCaseValider();
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
    public boolean isValide(){
        this.gameManager.validerSelection();
        return gameManager.getTaille() == -1;
    }

    @Override
    public int[][] validerSelection() {
        int[][] plateau = new int[10][10];
        for (int i = 0; i < plateau.length; i++){
            for (int j = 0; j < plateau[i].length; j++){
                plateau[i][j] = 0;
            }
        }
        ArrayList<Case> bateaux = this.gameManager.getCasesBateauxH();
        for (Case c : bateaux) {
            if (c.getX() >= 0 && c.getY() >= 0) {
                plateau[c.getY()][c.getX()] = 2;
                System.out.println(plateau[c.getY()][c.getX()]);
            }
        }
        return plateau;
    }

    @Override
    public void tirer(int x, int y) {
        this.gameManager.setEst_touche(false);
        this.gameManager.setCurrentPlayer(true);
        for (Case c : this.gameManager.getCasesBateauxIA()) {
            if (c.getX() == y && c.getY() == x) {
                c.setToucher();
                this.gameManager.setEst_touche(true);
            }
        }
        this.gameManager.setCaseViseeX(x+1);
        this.gameManager.setCaseViseeY(y+1);
        if (this.gameManager.isHVictory()) this.victory = 1;
        this.gameManager.notifierIA();
        if (this.gameManager.isIAVictory()) this.victory = -1;
    }

    @Override
    public void switchOrientation() {
        this.gameManager.switchOrientation();
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
