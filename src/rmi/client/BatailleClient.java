package rmi.client;

import rmi.serveur.ServerInterface;
import vue.VuePlateaux;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class BatailleClient{
    public static void main(String[] argv){
        ServerInterface serveurInterface;
        Modele modele = new Modele();
        VueSelectionClient vueSelectionClient = new VueSelectionClient(modele);

    }
}
