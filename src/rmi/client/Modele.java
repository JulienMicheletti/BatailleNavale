package rmi.client;

import modele.bateaux.Case;
import rmi.serveur.ServerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;

public class Modele extends Observable {
    private int ID;
    private int taille;
    private boolean lancerSelection;
    private ServerInterface serveurInterface;
    public static int HORIZONTAL = 10;
    public static int VERTICAL = 11;
    private int orientation;
    private int selectionBateau[][];
    private boolean lancerJeu;
    private boolean waitingJeu;
    private int[][] plateauJ1;
    private int[][] plateauJ2;
    private int victory;

    public Modele(ServerInterface serverInterface){
        this.serveurInterface = serverInterface;
        this.ID = 0;
        orientation = VERTICAL;
        lancerSelection = false;
        waitingJeu = false;
        plateauJ1 = new int[10][10];
        plateauJ2 = new int[10][10];
    }

    public void lancerSelection(){
        lancerSelection = true;
        setChanged();
        notifyObservers();
        lancerSelection = false;
    }

    public void lancerJeu(){
        lancerJeu = true;
        setChanged();
        notifyObservers();
        lancerJeu = false;
    }

    public void setID(int id){
        this.ID = id;
    }

    public void setSelection(int x, int y){
        try {
            selectionBateau = serveurInterface.setSelection(x, y, taille, ID);
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
            serveurInterface.switchOrientation(ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void validerSelection(){
        try {
            if (serveurInterface.isValide(ID)) {
                selectionBateau = serveurInterface.validerSelection(ID);
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
            waitingJeu = serveurInterface.valider(ID);
            setChanged();
            notifyObservers();
            waitingJeu = false;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CaseClient> getCasesJ(){
        ArrayList<CaseClient> cJ = new ArrayList<>();
        try {
            cJ = serveurInterface.getCasesJoueur(ID);
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
            victory = this.serveurInterface.getVictory();
            setChanged();
            notifyObservers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getLancerJeu(){
        return lancerJeu;
    }

    public boolean getWaitingJeu(){ return waitingJeu; }

    public void setTaille(int taille){
        this.taille = taille;
    }

    public int getTaille(){
        return taille;
    }

    public int getVictory() {
        return victory;
    }

    public int[][] getPlateauJ1(){
        return plateauJ1;
    }

    public int[][] getPlateauJ2(){
        return plateauJ2;
    }

    public int getPlayerConnected(){
        try {
            return serveurInterface.getPlayerConnected();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean getLancerSelection(){ return lancerSelection; }
}
