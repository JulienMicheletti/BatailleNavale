package rmi.client;

import modele.bateaux.Case;
import rmi.serveur.ServerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;

public class Modele extends Observable {
    private int taille;
    private ServerInterface serveurInterface;
    public static int HORIZONTAL = 10;
    public static int VERTICAL = 11;
    private int orientation;
    private int selectionBateau[][];
    private boolean lancerJeu;
    private int[][] plateauJ1;
    private int[][] plateauJ2;

    public Modele(ServerInterface serverInterface){
        this.serveurInterface = serverInterface;
        orientation = VERTICAL;
    }

    public void setSelection(int x, int y){
        try {
            selectionBateau = serveurInterface.setSelection(x, y, taille);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers();
    }

    public int[][] getSelectionBateau(){
        return selectionBateau;
    }

    public void switchOrientation(){
        try {
            serveurInterface.switchOrientation();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void validerSelection(){
        try {
            if (serveurInterface.isValide()) {
                selectionBateau = serveurInterface.validerSelection();
                setTaille(-1);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers();
    }

    public void confirmerSelection(){
        try {
            lancerJeu = serveurInterface.valider();
            setChanged();
            notifyObservers();
            lancerJeu = false;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CaseClient> getCasesJ(){
        ArrayList<CaseClient> cJ = new ArrayList<>();
        try {
            cJ = serveurInterface.getCasesJoueur();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return cJ;
    }

    public void tirer(int x, int y){
        try {
            this.serveurInterface.tirer(x, y);
            plateauJ1 = serveurInterface.getPlateauJ1();
            plateauJ2 = serveurInterface.getPlateauJ2();
            setChanged();
            notifyObservers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getLancerJeu(){
        return lancerJeu;
    }

    public void setTaille(int taille){
        this.taille = taille;
    }

    public int getTaille(){
        return taille;
    }

    public int[][] getPlateauJ1(){
        return plateauJ1;
    }

    public int[][] getPlateauJ2(){
        return plateauJ2;
    }
}
