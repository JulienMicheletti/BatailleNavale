package rmi.client;

import rmi.serveur.ServeurInterface;

import java.util.Observable;

public class Modele extends Observable {
    private int taille;
    ServeurInterface serveurInterface;
    public Modele(){

    }

    public void setSelection(int x, int y){
        serveurInterface.setSelection(x, y, taille);
    }


}
