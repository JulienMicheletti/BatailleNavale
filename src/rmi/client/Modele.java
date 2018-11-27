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
        if (orientation == HORIZONTAL){
            orientation = VERTICAL;
        }else{
            orientation = HORIZONTAL;
        }
        try {
            serveurInterface.setOrientation(orientation);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void validerSelection(){
        try {
            selectionBateau = serveurInterface.validerSelection();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        setTaille(-1);
        setChanged();
        notifyObservers();
    }

    public void setTaille(int taille){
        this.taille = taille;
    }

    public int getTaille(){
        return taille;
    }


}
