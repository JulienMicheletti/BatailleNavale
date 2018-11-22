package rmi.client;

import modele.bateaux.Case;
import rmi.serveur.ServerInterface;

import java.util.ArrayList;
import java.util.Observable;

public class Modele extends Observable {
    private int taille;
    ServerInterface serveurInterface;
    public static int HORIZONTAL = 10;
    public static int VERTICAL = 11;
    private int orientation;

    public Modele(){
        orientation = VERTICAL;
    }

    public void setSelection(int x, int y){
        serveurInterface.setSelection(x, y, taille);
    }
    public void validerSelection(){
        serveurInterface.validerSelection();
    }


    public Case[] getSelectionBateau() {
        return serveurInterface.getSelectionBateau();
    }
    public ArrayList<Case> getCaseValider() { return serveurInterface.getCaseValider(this.taille); }

    public void switchOrientation(){
        if (orientation == HORIZONTAL){
            orientation = VERTICAL;
        }else{
            orientation = HORIZONTAL;
        }
        serveurInterface.setOrientation(orientation);
    }

    public void setTaille(int taille){
        this.taille = taille;
    }

    public int getTaille(){
        return taille;
    }


}
