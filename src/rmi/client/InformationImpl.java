package rmi.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InformationImpl extends UnicastRemoteObject implements Information {
    protected InformationImpl() throws RemoteException {
    }

    @Override
    public String getInformation() throws RemoteException {
        return null;
    }
}
