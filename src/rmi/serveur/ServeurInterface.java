package rmi.serveur;

import modele.Plateau;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServeurInterface extends Remote {
    public Plateau getInformation() throws RemoteException;
}
