package modele;

import modele.bateaux.Case;
import modele.bateaux.ShipFactory;
import modele.bateaux.epoques.XVIemeFactory;
import modele.bateaux.epoques.XXIIemeFactory;
import modele.bateaux.epoques.XXemeFactory;
import modele.bots.BotChasseur;
import modele.bots.BotChasseurFort;
import modele.bots.BotFullRandom;
import modele.joueurs.Joueur;
import modele.joueurs.JoueurIA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * The type Game manager.
 */
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
    private Joueur playerH2;
    private JoueurIA playerIA;
    private int orientation;
    private int orientationJ2;
    private int taille;
    private int tailleJ2;
    private Case[] selectionBateau;
    private Case[] selectionBateauJ2;
    private Case caseViseeJ1;
    private Case[] caseColatJ1;
    private Case[] caseColatJ2;
    private Case caseViseeJ2;
    private int victory; // -1 IA victory, 0 none, 1 H victory
    private boolean est_touche;
    private boolean launchGame;
    private ShipFactory epoque;
    private int munition;
    private int munitionJ2;
    private boolean IAgame;
    private int turn; //1 = J1, 2 = J2

    /**
     * Instantiates a new Game manager.
     */
    public GameManager(){
        this.orientation = GameManager.HORIZONTAL;
        this.orientationJ2 = GameManager.HORIZONTAL;
        this.taille = -1;
        this.tailleJ2 = -1;
        currentPlayer = true;
        caseViseeJ1 = new Case(-1, -1);
        caseViseeJ2 = new Case(-1, -1);
        caseColatJ1 = new Case[4];
        caseColatJ2 = new Case[4];
        for (int i = 0; i < caseColatJ1.length; i++) {
            caseColatJ1[i] = new Case(-1, -1);
            caseColatJ2[i] = new Case(-1, -1);
        }
        this.playerH = new Joueur();
        this.playerH2 = new Joueur();
        this.playerIA = new JoueurIA();
        this.epoque = new XVIemeFactory();
        this.playerH.setFactory(epoque);
        this.playerIA.setFactory(epoque);
        this.playerH2.setFactory(epoque);
        this.launchGame = false;
        this.munition = 0;
        this.munitionJ2 = 0;
        this.IAgame = false;
        turn = 1;
    }

    /**
     * Reset game.
     */
    public void resetGame(){
        this.playerH.resetAllPos();
        this.playerH2.resetAllPos();
        this.playerIA.resetAllPos();
        this.orientation = GameManager.HORIZONTAL;
        this.orientationJ2 = GameManager.HORIZONTAL;
        this.taille = -1;
        this.tailleJ2 = -1;
        currentPlayer = true;
        caseViseeJ1 = new Case(-1, -1);
        caseViseeJ2 = new Case(-1, -1);
        caseColatJ1 = new Case[4];
        caseColatJ2 = new Case[4];
        for (int i = 0; i < caseColatJ1.length; i++) {
            caseColatJ1[i] = new Case(-1, -1);
            caseColatJ2[i] = new Case(-1, -1);
        }
        this.launchGame = false;
        this.munition = 0;
        this.munitionJ2 = 0;
        this.IAgame = false;
        turn = 1;
        this.epoque = new XVIemeFactory();
        this.playerH.setFactory(epoque);
        this.playerIA.setFactory(epoque);
        this.playerH2.setFactory(epoque);
    }

    /**
     * Tirer special.
     *
     * @param x       the x
     * @param y       the y
     * @param case_id the case id
     */
    public void tirer_special(int x, int y, int case_id){
        caseColatJ1[case_id].setToucher(false);
        ArrayList<Case> adversaire;
        if (IAgame)
            adversaire = getCasesBateauxIA();
        else
            adversaire = getCasesBateauxJ2();
        for (Case c : adversaire){
            if (c.getX() == x && c.getY() == y){
                c.setToucher(true);
                caseColatJ1[case_id].setToucher(true);
            }
        }
        caseColatJ1[case_id].setX(x);
        caseColatJ1[case_id].setY(y);
    }

    /**
     * Munition special.
     *
     * @param x the x
     * @param y the y
     */
    public void munition_special(int x, int y){
        if (this.munition == 1 && this.getXMunition() != 0){
            this.playerH.removeMunition(1);
            if (x != 0 && y != 0) this.tirer_special(x-1, y-1, 0);
            if (x != 9 && y != 9) this.tirer_special(x+1, y+1, 1);
            if (x != 0 && y != 9) this.tirer_special(x-1, y+1, 2);
            if (x != 9 && y != 0) this.tirer_special(x+1, y-1, 3);
        }
        if (this.munition == 2 && this.getCrossMunition() != 0){
            this.playerH.removeMunition(2);
            if (y != 0) this.tirer_special(x, y-1, 0);
            if (y != 9) this.tirer_special(x, y+1, 1);
            if (x != 0) this.tirer_special(x-1, y, 2);
            if (x != 9) this.tirer_special(x+1, y, 3);
        }
    }

    /**
     * Tirer special j 2.
     *
     * @param x       the x
     * @param y       the y
     * @param case_id the case id
     */
    public void tirer_specialJ2(int x, int y, int case_id){
        caseColatJ2[case_id].setToucher(false);
        for(Case c : getCasesBateauxH()){
            if (c.getX() == x && c.getY() == y){
                c.setToucher(true);
                caseColatJ2[case_id].setToucher(true);
            }
        }
        caseColatJ2[case_id].setX(x);
        caseColatJ2[case_id].setY(y);
    }

    /**
     * Munition special j 2.
     *
     * @param x the x
     * @param y the y
     */
    public void munition_specialJ2(int x, int y){
        if (this.munitionJ2 == 1 && this.getXMunitionJ2() != 0){
            this.playerH2.removeMunition(1);
            if (x != 0 && y != 0) this.tirer_specialJ2(x-1, y-1, 0);
            if (x != 9 && y != 9) this.tirer_specialJ2(x+1, y+1, 1);
            if (x != 0 && y != 9) this.tirer_specialJ2(x-1, y+1, 2);
            if (x != 9 && y != 0) this.tirer_specialJ2(x+1, y-1, 3);
        }
        if (this.munitionJ2 == 2 && this.getCrossMunitionJ2() != 0){
            this.playerH2.removeMunition(2);
            if (y != 0) this.tirer_specialJ2(x, y-1, 0);
            if (y != 9) this.tirer_specialJ2(x, y+1, 1);
            if (x != 0) this.tirer_specialJ2(x-1, y, 2);
            if (x != 9) this.tirer_specialJ2(x+1, y, 3);
        }
    }

    /**
     * Tirer.
     *
     * @param x the x
     * @param y the y
     */
    public void tirer(int x, int y) {
        caseViseeJ1.setToucher(false);
        currentPlayer = true;
        ArrayList<Case> adversaire;
        if (IAgame)
            adversaire = getCasesBateauxIA();
        else
            adversaire = getCasesBateauxJ2();
        for (Case c : adversaire) {
                if (c.getX() == x && c.getY() == y) {
                    c.setToucher(true);
                    caseViseeJ1.setToucher(true);
                }
        }
        caseViseeJ1.setX(x);
        caseViseeJ1.setY(y);
        munition_special(x, y);
        if (this.isHVictory()) this.victory = 1;
        if (IAgame) {
            notifierIA();
            if (this.isIAVictory()) this.victory = -1;
        } else {
            turn = 2;
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Tirer j 2.
     *
     * @param x the x
     * @param y the y
     */
    public void tirerJ2(int x, int y){
        caseViseeJ2.setToucher(false);
        for (Case c : getCasesBateauxH()){
            if (c.getX() == x && c.getY() == y){
                c.setToucher(true);
                caseViseeJ2.setToucher(true);
            }
        }
        caseViseeJ2.setX(x);
        caseViseeJ2.setY(y);
        munition_specialJ2(x, y);
        turn = 1;
    }

    /**
     * Get victory int.
     *
     * @return the int
     */
    public int getVictory(){
        return this.victory;
    }

    /**
     * Notifier ia.
     */
    public void notifierIA(){
        currentPlayer = false;
        caseViseeJ2.setToucher(false);
        int coord[];
        coord = playerIA.viser();
        for (Case c : getCasesBateauxH()){
            if (coord[1] == c.getY() && coord[0] == c.getX()){
                c.setToucher(true);
                caseViseeJ2.setToucher(true);
                this.playerIA.notifierToucher();
            }
        }
        caseViseeJ2.setX(coord[0]);
        caseViseeJ2.setY(coord[1]);
    }

    /**
     * Set plateau humain.
     *
     * @param x    the x
     * @param y    the y
     * @param type the type
     */
    public void setPlateauHumain(int x, int y, int type){
        playerH.getPlateau()[x][y] = type;
    }

    /**
     * Set plateau ia.
     *
     * @param x    the x
     * @param y    the y
     * @param type the type
     */
    public void setPlateauIA(int x, int y, int type){
        playerIA.getPlateau()[x][y] = type;
    }

    /**
     * Get case visee j 1 case.
     *
     * @return the case
     */
    public Case getCaseViseeJ1(){
        return caseViseeJ1;
    }

    /**
     * Get case visee j 2 case.
     *
     * @return the case
     */
    public Case getCaseViseeJ2(){
        return caseViseeJ2;
    }

    /**
     * Get case colat j 1 case [ ].
     *
     * @return the case [ ]
     */
    public Case[] getCaseColatJ1() {
        return caseColatJ1;
    }

    /**
     * Get case colat j 2 case [ ].
     *
     * @return the case [ ]
     */
    public Case[] getCaseColatJ2(){ return caseColatJ2; }

    /**
     * Set orientation.
     *
     * @param orientation the orientation
     */
    public void setOrientation(int orientation){
        this.orientation = orientation;
        this.playerH.setOrientation(orientation);
    }

    /**
     * Set orientation j 2.
     *
     * @param orientation the orientation
     */
    public void setOrientationJ2(int orientation){
        this.playerH2.setOrientation(orientation);
    }

    /**
     * Set taille.
     *
     * @param taille the taille
     */
    public void setTaille(int taille){
        this.taille = taille;
        this.playerH.resetPos(this.taille);
        this.selectionBateau = new Case[1];
        this.selectionBateau[0] = new Case(-1, -1);
        setChanged();
        notifyObservers();
    }

    /**
     * Set taille j 2.
     *
     * @param taille the taille
     */
    public void setTailleJ2(int taille){
        this.tailleJ2 = taille;
        this.playerH2.resetPos(taille);
        this.selectionBateauJ2 = new Case[1];
        this.selectionBateauJ2[0] = new Case(-1, -1);
    }

    /**
     * Set selection.
     *
     * @param x the x
     * @param y the y
     */
    public void setSelection(int x, int y){
        if (taille != -1) {
            if (this.playerH.setSelection(x, y, this.taille)) {
                this.selectionBateau = this.playerH.getSelection(taille);
                setChanged();
                notifyObservers();
            }
        }
    }

    /**
     * Set selection j 2.
     *
     * @param x the x
     * @param y the y
     */
    public void setSelectionJ2(int x, int y){
        if (tailleJ2 != -1){
            if (this.playerH2.setSelection(x, y, this.tailleJ2)) {
                this.selectionBateauJ2 = this.playerH2.getSelection(tailleJ2);
            }
        }
    }

    /**
     * Valider selection.
     */
    public void validerSelection() {
        if (this.playerH.validerCase(getSelectionBateau(), taille)) {
            taille = 0;
            setChanged();
            notifyObservers();
            taille = -1;
        }
    }

    /**
     * Valider selection j 2.
     */
    public void validerSelectionJ2(){
        if (this.playerH2.validerCase(getSelectionBateauJ2(), tailleJ2)){
            tailleJ2 = -1;
        }
    }

    /**
     * Confirmer selection.
     */
    public void confirmerSelection(){
        this.launchGame = this.playerH.plateauValide();
        if (this.launchGame) initIA();
        setChanged();
        notifyObservers();
    }

    /**
     * Confirmer selection j 2.
     */
    public void confirmerSelectionJ2(){
        this.launchGame = this.playerH2.plateauValide();
    }

    /**
     * Reset launch.
     */
    public void resetLaunch(){
        launchGame = false;
    }

    /**
     * Get taille int.
     *
     * @return the int
     */
    public int getTaille(){
        return taille;
    }

    /**
     * Get taille j 2 int.
     *
     * @return the int
     */
    public int getTailleJ2(){ return tailleJ2; }

    /**
     * Get launch game boolean.
     *
     * @return the boolean
     */
    public boolean getLaunchGame(){
        return launchGame;
    }

    /**
     * Get cases bateaux h array list.
     *
     * @return the array list
     */
    public ArrayList<Case> getCasesBateauxH(){
        return this.playerH.getCaseValider(0);
    }

    /**
     * Get cases bateaux j 2 array list.
     *
     * @return the array list
     */
    public ArrayList<Case> getCasesBateauxJ2(){ return this.playerH2.getCaseValider(0); }

    /**
     * Get cases bateaux ia array list.
     *
     * @return the array list
     */
    public ArrayList<Case> getCasesBateauxIA(){
        return this.playerIA.getCaseValider(0);
    }

    /**
     * Is ia victory boolean.
     *
     * @return the boolean
     */
    public boolean isIAVictory(){
        return this.playerH.isOver();
    }

    /**
     * Is h victory boolean.
     *
     * @return the boolean
     */
    public boolean isHVictory(){
        return this.playerIA.isOver();
    }

    /**
     * Get selection bateau case [ ].
     *
     * @return the case [ ]
     */
    public Case[] getSelectionBateau() {
        return selectionBateau;
    }

    /**
     * Get selection bateau j 2 case [ ].
     *
     * @return the case [ ]
     */
    public Case[] getSelectionBateauJ2(){return selectionBateauJ2;}

    /**
     * Gets case valider.
     *
     * @return the case valider
     */
    public ArrayList<Case> getCaseValider() { return this.playerH.getCaseValider(this.taille); }

    /**
     * Gets case valider j 2.
     *
     * @return the case valider j 2
     */
    public ArrayList<Case> getCaseValiderJ2() { return this.playerH2.getCaseValider(this.tailleJ2); }

    /**
     * Get player plateau int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getPlayerPlateau(){
        return playerH.getPlateau();
    }

    /**
     * Get ia plateau int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getIAPlateau(){ return playerIA.getPlateau();}

    /**
     * Switch orientation.
     */
    public void switchOrientation() {
        if (this.orientation == GameManager.HORIZONTAL){
            this.orientation = GameManager.VERTICAL;
            this.playerH.setOrientation(GameManager.VERTICAL);
        }else{
            this.orientation = GameManager.HORIZONTAL;
            this.playerH.setOrientation(GameManager.HORIZONTAL);
        }
    }

    /**
     * Switch orientation j 2.
     */
    public void switchOrientationJ2(){
        if (this.orientationJ2 == GameManager.HORIZONTAL) {
            this.orientationJ2 = GameManager.VERTICAL;
            this.playerH2.setOrientation(orientationJ2);
        } else {
            this.orientationJ2 = GameManager.HORIZONTAL;
            this.playerH2.setOrientation(orientationJ2);
        }
    }

    /**
     * Case exited.
     */
    public void caseExited(){
        this.playerH.resetPos(this.taille);
    }

    /**
     * Sets factory.
     *
     * @param selectedIndex the selected index
     */
    public void setFactory(int selectedIndex) {
        if (selectedIndex == 0){
            this.epoque = new XVIemeFactory();
            this.playerH.setFactory(epoque);
            this.playerH2.setFactory(epoque);
            this.playerIA.setFactory(epoque);
        }
        if (selectedIndex == 1){
            this.epoque = new XXemeFactory();
            this.playerH.setFactory(epoque);
            this.playerH2.setFactory(epoque);
            this.playerIA.setFactory(epoque);
        }
        if (selectedIndex == 2){
            this.epoque = new XXIIemeFactory();
            this.playerH.setFactory(epoque);
            this.playerH2.setFactory(epoque);
            this.playerIA.setFactory(epoque);
        }
    }

    /**
     * Init ia.
     */
    public void initIA() {
        this.playerIA.setFactory(epoque);
        this.playerIA.poserBateaux();
        this.playerIA.resetPlateau();
        this.victory = 0;
    }

    /**
     * Sets difficulty.
     *
     * @param selectedIndex the selected index
     */
    public void setDifficulty(int selectedIndex) {
        if (selectedIndex == 0) {
            this.playerIA.setDifficulty(new BotFullRandom());
        }
        if (selectedIndex == 1) {
            this.playerIA.setDifficulty(new BotChasseur());
        }
        if (selectedIndex == 2) {
            this.playerIA.setDifficulty(new BotChasseurFort());
        }
    }

    /**
     * Is munition game boolean.
     *
     * @return the boolean
     */
    public boolean isMunitionGame(){
        return this.epoque.isMunition();
    }

    /**
     * Sets munition.
     *
     * @param munition the munition
     */
    public void setMunition(int munition) {
        this.munition = munition;
    }

    /**
     * Gets munition.
     *
     * @return the munition
     */
    public int getMunition() {
        return munition;
    }

    /**
     * Sets munition j 2.
     *
     * @param munition the munition
     */
    public void setMunitionJ2(int munition) { this.munitionJ2 = munition; }

    /**
     * Get munition j 2 int.
     *
     * @return the int
     */
    public int getMunitionJ2(){ return this.munitionJ2; }

    /**
     * Get cross munition int.
     *
     * @return the int
     */
    public int getCrossMunition(){return this.playerH.getCrossMunition();}

    /**
     * Get x munition int.
     *
     * @return the int
     */
    public int getXMunition(){return this.playerH.getXMunition();}

    /**
     * Gets cross munition j 2.
     *
     * @return the cross munition j 2
     */
    public int getCrossMunitionJ2() {return this.playerH2.getCrossMunition(); }

    /**
     * Gets x munition j 2.
     *
     * @return the x munition j 2
     */
    public int getXMunitionJ2() { return this.playerH2.getXMunition(); }

    /**
     * Get turn int.
     *
     * @return the int
     */
    public int getTurn(){ return this.turn; }

    /**
     * Sets est touche.
     *
     * @param est_touche the est touche
     */
    public void setEst_touche(boolean est_touche) {
        this.est_touche = est_touche;
    }

    /**
     * Set ia game.
     */
    public void setIAGame(){
        IAgame = true;
    }

    /**
     * Is j 1 winner boolean.
     *
     * @return the boolean
     */
    public boolean isJ1Winner(){
        return this.playerH2.isOver();
    }

    /**
     * Is j 2 winner boolean.
     *
     * @return the boolean
     */
    public boolean isJ2Winner(){
        return this.playerH.isOver();
    }
}
