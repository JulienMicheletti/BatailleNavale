package rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface Client interface.
 */
public interface ClientInterface extends Remote {

    /**
     * Notify connected.
     *
     * @throws RemoteException the remote exception
     */
    void notifyConnected() throws RemoteException;

    /**
     * Notify selection.
     *
     * @throws RemoteException the remote exception
     */
    void notifySelection() throws RemoteException;

    /**
     * Notify jeu.
     *
     * @throws RemoteException the remote exception
     */
    void notifyJeu() throws RemoteException;

    /**
     * Notify shot.
     *
     * @throws RemoteException the remote exception
     */
    void notifyShot() throws RemoteException;

    /**
     * Notify victory j 1.
     *
     * @throws RemoteException the remote exception
     */
    void notifyVictoryJ1() throws RemoteException;

    /**
     * Notify victory j 2.
     *
     * @throws RemoteException the remote exception
     */
    void notifyVictoryJ2() throws RemoteException;

    /**
     * Notify other close.
     *
     * @throws RemoteException the remote exception
     */
    void notifyOtherClose() throws RemoteException;
}
