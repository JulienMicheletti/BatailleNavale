package rmi.serveur;


import com.sun.org.apache.regexp.internal.RE;
import modele.GameManager;
import modele.bateaux.Case;
import rmi.client.CaseClient;
import rmi.client.ClientInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerImplementation extends UnicastRemoteObject implements ServerInterface {
    private GameManager gameManager;
    private int playerConnected;
    private ClientInterface client1;
    private ClientInterface client2;
    private int rdyToPlay;

    protected ServerImplementation(GameManager gm) throws RemoteException {
        this.gameManager = gm;
        this.playerConnected = 0;
        this.rdyToPlay = 0;
    }

    public boolean askConnect() throws RemoteException{
        if (playerConnected < 2){
            playerConnected++;
            return true;
        }
        return false;
    }

    public void connexion(ClientInterface clientInterface) throws RemoteException{
        if (playerConnected == 1) {
            client1 = clientInterface;
        }
        else {
            client2 = clientInterface;
            client2.notifyConnected();
            client1.notifyConnected();
        }
    }

    public int getPlayerConnected() throws RemoteException{
        return playerConnected;
    }

    @Override
    public int[][] setSelection(int x, int y, int taille, int player) throws RemoteException {
        if (player == 1)
            return setSelection(x, y, taille);
        else
            return setSelectionJ2(x, y, taille);
    }

    public int[][] setPlateau(int player, int[][] plateau){
        ArrayList<Case> bateaux = new ArrayList<>();
        Case[] cases = new Case[0];
        if (player == 1) {
            bateaux = this.gameManager.getCaseValider();
            cases = this.gameManager.getSelectionBateau();
        } else {
            bateaux = this.gameManager.getCaseValiderJ2();
            cases = this.gameManager.getSelectionBateauJ2();
        }
        for (Case c : bateaux){
            if (c.getX() >= 0 && c.getY() >= 0){
                plateau[c.getY()][c.getX()] = 2;
            }
        }
        for (Case c : cases){
            int casex=c.getX();
            int casey=c.getY();
            if (casex >= 0 && casey >= 0) {
                if (plateau[casey][casex] == 2)
                    plateau[casey][casex] = 3;
                else
                    plateau[casey][casex] = 1;
            }
        }
        return plateau;
    }

    public int[][] initPlateau(){
        int[][] plateau = new int[10][10];
        for (int i = 0; i < plateau.length; i++){
            for (int j = 0; j < plateau[i].length; j++){
                plateau[i][j] = 0;
            }
        }
        return plateau;
    }

    public int[][] setSelection(int x, int y, int taille) {
        int[][] plateau = initPlateau();
        this.gameManager.setTaille(taille);
        this.gameManager.setSelection(x, y);
        return setPlateau(1, plateau);
    }

    public int[][] setSelectionJ2(int x, int y, int taille) throws RemoteException {
        int[][] plateau = initPlateau();
        this.gameManager.setTailleJ2(taille);
        this.gameManager.setSelectionJ2(x, y);
        return setPlateau(2, plateau);
    }

    @Override
    public boolean isValide(int player){
        if (player == 1) {
            this.gameManager.validerSelection();
            return gameManager.getTaille() == -1;
        } else {
            this.gameManager.validerSelectionJ2();
            return gameManager.getTailleJ2() == -1;
        }
    }

    @Override
    public int[][] validerSelection(int player) throws RemoteException {
        if (player == 1)
            return validerSelection();
        else
            return validerSelectionJ2();
    }

    public int[][] validerSelection() {
        int[][] plateau = initPlateau();
        ArrayList<Case> bateaux = this.gameManager.getCasesBateauxH();
        for (Case c : bateaux) {
            if (c.getX() >= 0 && c.getY() >= 0) {
                plateau[c.getY()][c.getX()] = 2;
            }
        }
        return plateau;
    }

    public int[][] validerSelectionJ2() throws RemoteException {
        int[][] plateau = initPlateau();
        ArrayList<Case> bateaux = this.gameManager.getCasesBateauxJ2();
        for (Case c : bateaux){
            if (c.getX() >= 0 && c.getY() >= 0){
                plateau[c.getY()][c.getX()] = 2;
            }
        }
        return plateau;
    }

    @Override
    public void tirer(int x, int y) {
        this.gameManager.tirer(x, y);
    }

    @Override
    public int[][] getPlateauJ1() throws RemoteException {
        int[][] plateau =  new int[10][10];
        for (int i = 0; i < plateau.length; i++){
            for (int j = 0; j < plateau[i].length; j++)
                plateau[i][j] = 0;
        }
        Case caseVise = gameManager.getCaseViseeJ1();
        if (caseVise.getToucher())
            plateau[caseVise.getY()][caseVise.getX()] = 1; //ROUGE
        else
            plateau[caseVise.getY()][caseVise.getX()] = 2; //NOIR
        return plateau;
    }

    @Override
    public int[][] getPlateauJ2() throws RemoteException {
        int[][] plateau =  new int[10][10];
        for (int i = 0; i < plateau.length; i++){
            for (int j = 0; j < plateau[i].length; j++){
                plateau[i][j] = 0;
            }
        }
        Case caseVise = gameManager.getCaseViseeJ2();
        if (caseVise.getToucher())
            plateau[caseVise.getY()][caseVise.getX()] = 1;
        else
            plateau[caseVise.getY()][caseVise.getX()] = 2;
        return plateau;
    }

    @Override
    public void switchOrientation(int player) {
        if (player == 1)
            this.gameManager.switchOrientation();
        else
            this.gameManager.switchOrientationJ2();
    }

    @Override
    public boolean valider(int player) throws RemoteException {
        boolean valide = false;
        if (player == 1) {
            this.gameManager.confirmerSelection();
            if (this.gameManager.getLaunchGame()) {
                this.gameManager.resetLaunch();
                rdyToPlay++;
                valide = true;
            }
        } else {
            this.gameManager.confirmerSelectionJ2();
            if (this.gameManager.getLaunchGame()){
                this.gameManager.resetLaunch();
                rdyToPlay++;
                valide = true;
            }
        }
        if (rdyToPlay == 2){
            client1.notifyJeu();
            client2.notifyJeu();
        }
        return valide;
    }

    @Override
    public ArrayList<CaseClient> getCasesJoueur(int player) throws RemoteException {
        if (player == 1){
            return getCasesJoueur();
        } else {
            return getCasesJoueur2();
        }
    }

    public ArrayList<CaseClient> getCasesJoueur() throws RemoteException {
        ArrayList<Case> cases = this.gameManager.getCasesBateauxH();
        ArrayList<CaseClient> casesJ = new ArrayList<CaseClient>(cases.size());
        for (Case c : cases)
            casesJ.add(new CaseClient(c.getX(),c.getY()));
        return casesJ;
    }

    public ArrayList<CaseClient> getCasesJoueur2() throws RemoteException {
        ArrayList<Case> cases = this.gameManager.getCasesBateauxJ2();
        ArrayList<CaseClient> casesJ2 = new ArrayList<CaseClient>(cases.size());
        for (Case c : cases)
            casesJ2.add(new CaseClient(c.getX(), c.getY()));
        return casesJ2;
    }


    public static void main(String[] args){
        try {
            ServerImplementation serverImplementation = new ServerImplementation(new GameManager());
            Registry registry = LocateRegistry.createRegistry(8081);
            registry.bind("bataille_navale", serverImplementation);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
