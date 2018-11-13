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
import java.util.Observable;

public class GameManager extends Observable implements Serializable{

    public static int BATEAUDEUX = 2;
    public static int BATEAUTROIS1 = 31;
    public static int BATEAUTROIS2 = 32;
    public static int BATEAUQUATRE = 4;
    public static int BATEAUCINQ = 5;
    public static int HORIZONTAL = 10;
    public static int VERTICAL = 11;
    private Joueur playerH;
    private JoueurIA playerIA;
    private int orientation;
    private int taille;
    private Case[] selectionBateau;
    private ShipFactory epoque;

    public GameManager(){
        this.playerH = new Joueur();
        this.playerIA = new JoueurIA();
        this.orientation = GameManager.HORIZONTAL;
        this.taille = -1;
        epoque = new XXIIemeFactory();
        this.playerH.setFactory(epoque);
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

    public void switchOrientation() {
        if (this.orientation == GameManager.HORIZONTAL){
            this.orientation = GameManager.VERTICAL;
            this.playerH.setOrientation(GameManager.VERTICAL);
        }else{
            this.orientation = GameManager.HORIZONTAL;
            this.playerH.setOrientation(GameManager.HORIZONTAL);
        }
    }
}
