package rmi.client;


import rmi.serveur.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * The type Client implementation.
 */
public class ClientImplementation extends UnicastRemoteObject implements ClientInterface {
    private Modele modele;

    /**
     * Instantiates a new Client implementation.
     *
     * @param serverInterface the server interface
     * @throws RemoteException the remote exception
     */
    public ClientImplementation(ServerInterface serverInterface) throws RemoteException{
        this.modele = new Modele(serverInterface);
        modele.setID(serverInterface.getPlayerConnected());
        new VueGeneralClient(modele);
    }

    @Override
    public void notifyShot() throws RemoteException{
        modele.notifyShot();
    }

    @Override
    public void notifySelection() throws RemoteException {
        modele.lancerSelection();
    }

    @Override
    public void notifyConnected() throws RemoteException{
        modele.lancerChoixEpoque();
    }

    @Override
    public void notifyJeu() throws RemoteException {
        modele.lancerJeu();
    }

    @Override
    public void notifyVictoryJ1() throws RemoteException {
        modele.gameWon(1);
    }

    @Override
    public void notifyVictoryJ2() throws RemoteException {
        modele.gameWon(2);
    }

    @Override
    public void notifyOtherClose() throws RemoteException {
        modele.otherIsClose();
    }
}
