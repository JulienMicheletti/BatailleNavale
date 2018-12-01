package rmi.client;

import rmi.serveur.ServerImplementation;
import rmi.serveur.ServerInterface;
import vue.VueGeneral;
import vue.VuePlateaux;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BatailleClient{
    public static void main(String[] argv){
        try {
            Registry registry = LocateRegistry.getRegistry(8081);
            System.out.println("Registry bindings :");
            String[] e = registry.list();
            for (String s : e)
                System.out.println(s);
            ServerInterface serverInterface = (ServerInterface)registry.lookup("bataille_navale");
            Modele modele = new Modele(serverInterface);
            new VueGeneralClient(modele);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        ServerInterface serveurInterface;

    }
}
