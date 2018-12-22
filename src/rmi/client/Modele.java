package rmi.client;

import rmi.serveur.ServerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * The type Modele.
 */
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
    private boolean leave;
    private boolean otherClose;

    /**
     * Instantiates a new Modele.
     *
     * @param serverInterface the server interface
     */
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
        leave = false;
        otherClose = false;
    }

    /**
     * Lancer choix epoque.
     */
    public void lancerChoixEpoque(){
        choixEpoque = true;
        setChanged();
        notifyObservers();
        choixEpoque = false;
    }

    /**
     * Lancer selection.
     */
    public void lancerSelection(){
        lancerSelection = true;
        setChanged();
        notifyObservers();
        lancerSelection = false;
    }

    /**
     * Lancer jeu.
     */
    public void lancerJeu(){
        lancerJeu = true;
        setChanged();
        notifyObservers();
        lancerJeu = false;
    }

    /**
     * Valider epoque.
     */
    public void validerEpoque(){
        try {
            serveurInterface.validerEpoque();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Game won.
     *
     * @param playerID the player id
     */
    public void gameWon(int playerID){
        victory = playerID;
        setChanged();
        notifyObservers();
        victory = -1;
    }

    /**
     * Set factory.
     *
     * @param index the index
     */
    public void setFactory(int index){
        try {
            serveurInterface.setFactory(index);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set id.
     *
     * @param id the id
     */
    public void setID(int id){
        this.ID = id;
    }

    /**
     * Set selection.
     *
     * @param x the x
     * @param y the y
     */
    public void setSelection(int x, int y){
        try {
            selectionBateau = serveurInterface.setSelection(x, y, taille, ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Get selection bateau int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getSelectionBateau(){
        return selectionBateau;
    }

    /**
     * Switch orientation.
     */
    public void switchOrientation(){
        try {
            serveurInterface.switchOrientation(ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Valider selection.
     */
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

    /**
     * Confirmer selection.
     */
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

    /**
     * Get cases j array list.
     *
     * @return the array list
     */
    public ArrayList<CaseClient> getCasesJ(){
        ArrayList<CaseClient> cJ = new ArrayList<>();
        try {
            cJ = serveurInterface.getCasesJoueur(ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return cJ;
    }

    /**
     * Notify shot.
     *
     * @throws RemoteException the remote exception
     */
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

    /**
     * Tirer.
     *
     * @param x the x
     * @param y the y
     */
    public void tirer(int x, int y){
        try {
            this.serveurInterface.tirer(x, y, ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set mun.
     *
     * @param mun the mun
     */
    public void setMun(int mun){
        try {
            this.serveurInterface.setMun(mun, ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get mun int.
     *
     * @param type the type
     * @return the int
     */
    public int getMun(int type){
        try {
            return serveurInterface.getMun(type, ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get lancer jeu boolean.
     *
     * @return the boolean
     */
    public boolean getLancerJeu(){
        return lancerJeu;
    }

    /**
     * Get waiting jeu boolean.
     *
     * @return the boolean
     */
    public boolean getWaitingJeu(){ return waitingJeu; }

    /**
     * Set taille.
     *
     * @param taille the taille
     */
    public void setTaille(int taille){
        this.taille = taille;
    }

    /**
     * Get taille int.
     *
     * @return the int
     */
    public int getTaille(){
        return taille;
    }

    /**
     * Gets victory.
     *
     * @return the victory
     */
    public int getVictory() {
        return victory;
    }

    /**
     * Get plateau j 1 int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getPlateauJ1(){
        return plateauJ1;
    }

    /**
     * Get plateau j 2 int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getPlateauJ2(){
        return plateauJ2;
    }

    /**
     * Get player connected int.
     *
     * @return the int
     */
    public int getPlayerConnected(){
        try {
            return serveurInterface.getPlayerConnected();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get lancer selection boolean.
     *
     * @return the boolean
     */
    public boolean getLancerSelection(){ return lancerSelection; }

    /**
     * Get choix epoque boolean.
     *
     * @return the boolean
     */
    public boolean getChoixEpoque(){ return choixEpoque; }

    /**
     * Get id int.
     *
     * @return the int
     */
    public int getID(){ return ID; }

    /**
     * My turn boolean.
     *
     * @return the boolean
     */
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

    /**
     * Is ammo game boolean.
     *
     * @return the boolean
     */
    public boolean isAmmoGame(){
        try {
            return serveurInterface.isAmmoGame();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * End game.
     */
    public void endGame(){
        leave = true;
        setChanged();
        notifyObservers();
        try {
            serveurInterface.endGame();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get leave boolean.
     *
     * @return the boolean
     */
    public boolean getLeave(){
        return leave;
    }

    /**
     * Is other close boolean.
     *
     * @return the boolean
     */
    public boolean isOtherClose(){
        return otherClose;
    }

    /**
     * Other is close.
     */
    public void otherIsClose(){
        otherClose = true;
        setChanged();
        notifyObservers();
    }

    /**
     * Client close.
     */
    public void clientClose(){
        try {
            serveurInterface.clientClose(this.ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
