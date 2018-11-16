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
    private int caseViseeX;
    private int caseViseeY;
    private boolean est_touche;
    private boolean launchGame;
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
        this.launchGame = false;
    }

    public void tirer(int x, int y) {
        est_touche = false;
        if (currentPlayer == true) {
            for (Case c : casesOccIA) {
                if (c.getX() == x && c.getY() == y) {
                    c.setToucher();
                    est_touche = true;
                }
            }
        } else if (currentPlayer == false) {
            for (Case c : casesOccH) {
                if (c.getX() == x && c.getY() == y) {
                    c.setToucher();
                    est_touche = true;
                }
            }
        }
        caseViseeX = x;
        caseViseeY = y;
        setChanged();
        notifyObservers();
    }

    public boolean isEst_touche(){
        return est_touche;
    }

    public int getViseeX(){
        return caseViseeX;
    }

    public int getViseeY(){
        return caseViseeY;
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
        this.playerH.resetPos(this.taille);
        this.selectionBateau = new Case[1];
        this.selectionBateau[0] = new Case(-1, -1);
        setChanged();
        notifyObservers();
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
        if (this.playerH.validerCase(getSelectionBateau(), taille)) {
            taille = 0;
            setChanged();
            notifyObservers();
            taille = -1;
        }
    }

    public void confirmerSelection(){
        this.launchGame = this.playerH.plateauValide();
        setChanged();
        notifyObservers();
    }

    public int getTaille(){
        return taille;
    }

    public boolean getLaunchGame(){
        return launchGame;
    }

    public ArrayList<Case> getCasesBateauxH(){
        return this.playerH.getCaseValider(0);
    }

    public Case[] getSelectionBateau() {
        return selectionBateau;
    }

    public ArrayList<Case> getCaseValider() { return this.playerH.getCaseValider(this.taille); }

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
