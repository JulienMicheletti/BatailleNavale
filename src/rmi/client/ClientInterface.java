package rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

    void notifyConnected() throws RemoteException;
    void notifyJeu() throws RemoteException;
}