package rmi.client;

import rmi.serveur.ServeurInterface;
import vue.VuePlateaux;
import vue.VueSelectionClient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class BatailleClient{
    public static void main(String[] argv){
        ServeurInterface serveurInterface;
        Modele modele = new Modele();
        VueSelectionClient vueSelectionClient = new VueSelectionClient(modele);

    }
}
