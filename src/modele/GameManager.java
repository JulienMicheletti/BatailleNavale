package modele;

import modele.bateaux.Case;
import modele.bateaux.ShipFactory;
import modele.bateaux.epoques.XVIemeFactory;
import modele.bateaux.epoques.XXIIemeFactory;
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
    private Case caseViseeJ1;
    private Case caseViseeJ2;
    private int victory; // -1 IA victory, 0 none, 1 H victory
    private boolean est_touche;
    private boolean launchGame;
    private ShipFactory epoque;
    private int munition;

    public GameManager(){
        this.orientation = GameManager.HORIZONTAL;
        this.taille = -1;
        currentPlayer = true;
        caseViseeJ1 = new Case(0, 0);
        caseViseeJ2 = new Case(0, 0);
        this.playerH = new Joueur();
        this.playerIA = new JoueurIA();
        this.epoque = new XVIemeFactory();
        this.playerH.setFactory(epoque);
        this.playerIA.setFactory(epoque);
        this.launchGame = false;
        this.munition = 0;
    }

    public void tirer(int x, int y, boolean collateral) {
        caseViseeJ1.setToucher(false);
        currentPlayer = true;
        for (Case c : getCasesBateauxIA()) {
            if (c.getX() == y && c.getY() == x) {
                caseViseeJ1.setToucher(true);
            }
        }
        caseViseeJ1.setX(y+1);
        caseViseeJ1.setY(x+1);
        setChanged();
        notifyObservers();
        if (!collateral) {
            if (this.munition == 1 && this.getXMunition() != 0){
                this.playerH.removeMunition(1);
                if (x != 0 && y != 0) this.tirer(x-1, y-1, true);
                if (x != 9 && y != 9) this.tirer(x+1, y+1, true);
                if (x != 0 && y != 9) this.tirer(x-1, y+1, true);
                if (x != 9 && y != 0) this.tirer(x+1, y-1, true);
            }
            if (this.munition == 2 && this.getCrossMunition() != 0){
                this.playerH.removeMunition(2);
                if (y != 0) this.tirer(x, y-1, true);
                if (y != 9) this.tirer(x, y+1, true);
                if (x != 0) this.tirer(x-1, y, true);
                if (x != 9) this.tirer(x+1, y, true);
            }
            if (this.isHVictory()) this.victory = 1;
            notifierIA();
            if (this.isIAVictory()) this.victory = -1;
        }
    }

    public int getVictory(){
        return this.victory;
    }

    public void notifierIA(){
        currentPlayer = false;
        caseViseeJ2.setToucher(false);
        int coord[];
        coord = playerIA.viser();
        for (Case c : getCasesBateauxH()){
            if (coord[1]+1 == c.getY()+1 && coord[0]+1 == c.getX()+1){
                caseViseeJ2.setToucher(true);
                this.playerIA.notifierToucher();
            }
        }
        caseViseeJ2.setY(coord[0]+1);
        caseViseeJ2.setX(coord[1]+1);
        setChanged();
        notifyObservers();
    }

    public boolean isJ1Est_touche(){
        return caseViseeJ1.getToucher();
    }

    public boolean isJ2Est_touche(){
        return caseViseeJ2.getToucher();
    }

    public int getJ1ViseeX(){
        return caseViseeJ1.getX();
    }

    public int getJ1ViseeY(){
        return caseViseeJ1.getY();
    }

    public int getJ2ViseeX(){
        return caseViseeJ2.getX();
    }

    public int getJ2ViseeY(){
        return caseViseeJ2.getY();
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


    public void resetLaunch(){
        launchGame = false;
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
        if (selectedIndex == 2){
            this.epoque = new XXIIemeFactory();
            this.playerH.setFactory(epoque);
            this.playerIA.setFactory(epoque);
        }
    }

    public void initIA() {
        this.playerIA.poserBateaux();
        this.victory = 0;
    }

    public void setDifficulty(int selectedIndex) {
        if (selectedIndex == 0) {
            this.playerIA.setDifficulty(new BotFullRandom());
        }
        if (selectedIndex == 1) {
            this.playerIA.setDifficulty(new BotChasseur());
        }
    }

    public boolean isMunitionGame(){
        return this.epoque.isMunition();
    }

    public void setMunition(int munition) {
        this.munition = munition;
    }

    public int getMunition() {
        return munition;
    }

    public int getCrossMunition(){return this.playerH.getCrossMunition();}

    public int getXMunition(){return this.playerH.getXMunition();}


    public void setEst_touche(boolean est_touche) {
        this.est_touche = est_touche;
    }
}
