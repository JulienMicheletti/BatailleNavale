package rmi.serveur;

import rmi.client.CaseClient;
import rmi.client.ClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * The interface Server interface.
 */
public interface ServerInterface extends Remote {

    /**
     * Connexion.
     *
     * @param client the client
     * @throws RemoteException the remote exception
     */
    void connexion(ClientInterface client) throws RemoteException;

    /**
     * Valider epoque.
     *
     * @throws RemoteException the remote exception
     */
//
    void validerEpoque() throws RemoteException;

    /**
     * Set selection int [ ] [ ].
     *
     * @param x      the x
     * @param y      the y
     * @param taille the taille
     * @param player the player
     * @return the int [ ] [ ]
     * @throws RemoteException the remote exception
     */
//
    int[][] setSelection(int x, int y, int taille, int player) throws RemoteException;

    /**
     * Valider selection int [ ] [ ].
     *
     * @param player the player
     * @return the int [ ] [ ]
     * @throws RemoteException the remote exception
     */
    int[][] validerSelection(int player) throws RemoteException;

    /**
     * Switch orientation.
     *
     * @param player the player
     * @throws RemoteException the remote exception
     */
//
    void switchOrientation(int player) throws RemoteException;

    /**
     * Is valide boolean.
     *
     * @param player the player
     * @return the boolean
     * @throws RemoteException the remote exception
     */
//
    boolean isValide(int player) throws RemoteException;

    /**
     * Valider boolean.
     *
     * @param player the player
     * @return the boolean
     * @throws RemoteException the remote exception
     */
    boolean valider(int player) throws RemoteException;

    /**
     * Gets cases joueur.
     *
     * @param player the player
     * @return the cases joueur
     * @throws RemoteException the remote exception
     */
//
    ArrayList<CaseClient> getCasesJoueur(int player) throws RemoteException;

    /**
     * Sets factory.
     *
     * @param facto the facto
     * @throws RemoteException the remote exception
     */
//
    void setFactory(int facto) throws RemoteException;

    /**
     * Gets turn.
     *
     * @return the turn
     * @throws RemoteException the remote exception
     */
//
    int getTurn() throws RemoteException;

    /**
     * Is ammo game boolean.
     *
     * @return the boolean
     * @throws RemoteException the remote exception
     */
//
    boolean isAmmoGame() throws RemoteException;

    /**
     * Sets mun.
     *
     * @param ammo the ammo
     * @param ID   the id
     * @throws RemoteException the remote exception
     */
    void setMun(int ammo, int ID) throws RemoteException;

    /**
     * Gets mun.
     *
     * @param type the type
     * @param ID   the id
     * @return the mun
     * @throws RemoteException the remote exception
     */
    int getMun(int type, int ID) throws RemoteException;

    /**
     * Client close.
     *
     * @param ID the id
     * @throws RemoteException the remote exception
     */
//
    void clientClose(int ID) throws RemoteException;

    /**
     * End game.
     *
     * @throws RemoteException the remote exception
     */
    void endGame() throws RemoteException;

    /**
     * Tirer.
     *
     * @param x      the x
     * @param y      the y
     * @param player the player
     * @throws RemoteException the remote exception
     */
//
    void tirer(int x, int y, int player) throws RemoteException;

    /**
     * Get plateau j 1 int [ ] [ ].
     *
     * @return the int [ ] [ ]
     * @throws RemoteException the remote exception
     */
    int[][] getPlateauJ1() throws RemoteException;

    /**
     * Get plateau j 2 int [ ] [ ].
     *
     * @return the int [ ] [ ]
     * @throws RemoteException the remote exception
     */
    int[][] getPlateauJ2() throws RemoteException;

    /**
     * Ask connect boolean.
     *
     * @return the boolean
     * @throws RemoteException the remote exception
     */
    boolean askConnect() throws RemoteException;

    /**
     * Gets player connected.
     *
     * @return the player connected
     * @throws RemoteException the remote exception
     */
    int getPlayerConnected() throws RemoteException;

    /**
     * Gets victory.
     *
     * @return the victory
     * @throws RemoteException the remote exception
     */
    int getVictory() throws RemoteException;

    /**
     * Ask lancer jeu.
     *
     * @throws RemoteException the remote exception
     */
    void askLancerJeu() throws RemoteException;
}
