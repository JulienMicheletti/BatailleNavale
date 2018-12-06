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
    private boolean choixEpoque;
    private int[][] plateauJ1;
    private int[][] plateauJ2;
    private int victory;

    public Modele(ServerInterface serverInterface){
        this.serveurInterface = serverInterface;
        this.ID = 0;
        orientation = VERTICAL;
        lancerSelection = false;
        waitingJeu = false;
        choixEpoque = false;
        plateauJ1 = new int[10][10];
        plateauJ2 = new int[10][10];
        victory = -1;
    }

    public void lancerChoixEpoque(){
        choixEpoque = true;
        setChanged();
        notifyObservers();
        choixEpoque = false;
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

    public void validerEpoque(){
        try {
            serveurInterface.validerEpoque();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void gameWon(int playerID){
        victory = playerID;
        setChanged();
        notifyObservers();
        victory = -1;
    }

    public void setFactory(int index){
        try {
            serveurInterface.setFactory(index);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
            if (waitingJeu) {
                setChanged();
                notifyObservers();
            }
            waitingJeu = false;
            serveurInterface.askLancerJeu();
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

    public void notifyShot() throws RemoteException{
        if (ID == 1) {
            plateauJ1 = serveurInterface.getPlateauJ1();
            plateauJ2 = serveurInterface.getPlateauJ2();
        } else {
            plateauJ1 = serveurInterface.getPlateauJ2();
            plateauJ2 = serveurInterface.getPlateauJ1();
        }
        victory = this.serveurInterface.getVictory();
        setChanged();
        notifyObservers();
    }

    public void tirer(int x, int y){
        try {
            this.serveurInterface.tirer(x, y, ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void leavingGame(){
        System.out.println("rip :(");
    }

    public void setMun(int mun){
        try {
            this.serveurInterface.setMun(mun, ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getMun(int type){
        try {
            return serveurInterface.getMun(type, ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
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

    public boolean getChoixEpoque(){ return choixEpoque; }

    public int getID(){ return ID; }

    public boolean myTurn(){
        try {
            if (serveurInterface.getTurn() == getID()){
                return true;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAmmoGame(){
        try {
            return serveurInterface.isAmmoGame();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}
