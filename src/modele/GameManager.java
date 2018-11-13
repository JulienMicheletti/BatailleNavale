package modele;

import modele.bateaux.Case;
import modele.bateaux.ShipFactory;
import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauTrois.BateauTrois;
import modele.bateaux.epoques.XXIIemeFactory;
import modele.joueurs.Joueur;
import modele.joueurs.JoueurIA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class GameManager extends Observable implements Serializable{

    public static int BATEAUDEUX = 2;
    public static int BATEAUTROIS1 = 31;
    public static int BATEAUTROIS2 = 32;
    public static int BATEAUQUATRE = 4;
    public static int BATEAUCINQ = 5;
    public static int HORIZONTAL = 10;
    public static int VERTICAL = 11;
    public boolean currentPlayer;
    private Joueur playerH;
    private JoueurIA playerIA;
    private int orientation;
    private int taille;
    private Case[] selectionBateau;
    private ArrayList<Case> casesOccIA;
    private ArrayList<Case> casesOccH;
    private Case caseTouchee;
    private ShipFactory epoque;

    public GameManager(){
        this.playerH = new Joueur();
        this.playerIA = new JoueurIA();
        this.orientation = GameManager.HORIZONTAL;
        this.taille = -1;
        currentPlayer = true;
        epoque = new XXIIemeFactory();
        this.playerH.setFactory(epoque);
        casesOccH = new ArrayList<>();
        casesOccIA = new ArrayList<>();
        casesOccIA.add(new Case(2, 2));
    }

    public void tirer(int x, int y) {
        boolean est_touche = false;
        if (currentPlayer == true) {
            for (Case c : casesOccIA) {
                if (c.getX() == x && c.getY() == y) {
                    caseTouchee = c;
                    est_touche = true;
                }
            }
        } else if (currentPlayer == false) {
            for (Case c : casesOccH) {
                if (c.getX() == x && c.getY() == y) {
                    caseTouchee = c;
                    est_touche = true;
                }
            }
        }
        if (est_touche){
            caseTouchee.setToucher();
            setChanged();
            notifyObservers();
        }
    }

    public int getToucheeX(){
        return caseTouchee.getX();
    }

    public int getToucheeY(){
        return caseTouchee.getY();
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean getCurrentPlayer(){
        return currentPlayer;
    }

    public void setOrientation(int orientation){
        this.orientation = orientation;
        this.playerH.setOrientation(orientation);
    }

    public void setTaille(int taille){
        this.taille = taille;
    }

    public void setSelection(int x, int y){
        if (taille != -1) {
            if (this.playerH.setSelection(x, y, this.taille)) {
                this.selectionBateau = this.playerH.getSelection(taille);
                setChanged();
                notifyObservers();
            }
        }
    }

    public void validerSelection() {
        taille = 0;
        setChanged();
        notifyObservers();
        taille = -1;
    }

    public int getTaille(){
        return taille;
    }

    public Case[] getSelectionBateau() {
        return selectionBateau;
    }

    public int[][] getPlayerPlateau(){
        return playerH.getPlateau();
    }
}
