package modele;

import modele.bateaux.Case;
import modele.bateaux.ShipFactory;
import modele.bateaux.epoques.XVIemeFactory;
import modele.bateaux.epoques.XXemeFactory;
import modele.bots.BotChasseur;
import modele.bots.BotFullRandom;
import modele.bots.BotStrategie;
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
    private int caseViseeX;
    private int caseViseeY;
    private int victory; // -1 IA victory, 0 none, 1 H victory
    private boolean est_touche;
    private boolean launchGame;
    private ShipFactory epoque;

    public GameManager(){
        this.orientation = GameManager.HORIZONTAL;
        this.taille = -1;
        currentPlayer = true;
        this.playerH = new Joueur();
        this.playerIA = new JoueurIA();
        this.epoque = new XVIemeFactory();
        this.playerH.setFactory(epoque);
        this.playerIA.setFactory(epoque);
        this.launchGame = false;
    }

    public void tirer(int x, int y) {
        est_touche = false;
        currentPlayer = true;
        for (Case c : getCasesBateauxIA()) {
            if (c.getX() == y && c.getY() == x) {
                c.setToucher();
                est_touche = true;
            }
        }
        caseViseeX = y+1;
        caseViseeY = x+1;
        setChanged();
        notifyObservers();
        if(this.isHVictory()) this.victory = 1;
        notifierIA();
        if(this.isIAVictory()) this.victory = -1;
    }

    public int getVictory(){
        return this.victory;
    }

    public void notifierIA(){
        currentPlayer = false;
        est_touche = false;
        int coord[];
        coord = playerIA.viser();
        for (Case c : getCasesBateauxH()){
            if (coord[1]+1 == c.getY()+1 && coord[0]+1 == c.getX()+1){
                c.setToucher();
                est_touche = true;
                this.playerIA.notifierToucher();
            }
        }
        caseViseeY = coord[0]+1;
        caseViseeX = coord[1]+1;
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
        this.launchGame = false;
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

    public ArrayList<Case> getCasesBateauxIA(){
        return this.playerIA.getCaseValider(0);
    }

    public boolean isIAVictory(){
        return this.playerH.isOver();
    }

    public boolean isHVictory(){
        return this.playerIA.isOver();
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

    public void caseExited(){
        this.playerH.resetPos(this.taille);
    }

    public void setFactory(int selectedIndex) {
        if (selectedIndex == 0){
            this.epoque = new XVIemeFactory();
            this.playerH.setFactory(epoque);
            this.playerIA.setFactory(epoque);
        }
        if (selectedIndex == 1){
            this.epoque = new XXemeFactory();
            this.playerH.setFactory(epoque);
            this.playerIA.setFactory(epoque);
        }
    }

    public void initIA() {
        this.playerIA.poserBateaux();
        this.victory = 0;
        this.setDifficulty(0);
    }

    public void setDifficulty(int selectedIndex) {
        if (selectedIndex == 0) {
            this.playerIA.setDifficulty(new BotFullRandom());
        }
        if (selectedIndex == 1) {
            this.playerIA.setDifficulty(new BotChasseur());
        }
    }
}
