package modele;

import modele.bateaux.Case;
import modele.bateaux.ShipFactory;
import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauTrois.BateauTrois;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Plateau.
 */
public class Plateau implements Serializable{
    private int[][] plateau;
    private ShipFactory shipFactory;
    private BateauQuatre bateauQuatre;
    private BateauTrois bateauTrois;
    private BateauTrois bateauTroisDeux;
    private BateauCinq bateauCinq;
    private BateauDeux bateauDeux;
    private boolean[] toConfirm;

    /**
     * Instantiates a new Plateau.
     */
    public Plateau(){
        this.plateau = new int[10][10]; //List ou double tab de "Cases" ?
    }

    /**
     * Set ship factory.
     *
     * @param factory the factory
     */
    public void setShipFactory(ShipFactory factory){
        this.shipFactory = factory;
        this.toConfirm = new boolean[5];
        bateauDeux = shipFactory.getBateauDeux();
        bateauTrois = shipFactory.getBateauTrois();
        bateauTroisDeux = shipFactory.getBateauTrois();
        bateauQuatre = shipFactory.getBateauQuatre();
        bateauCinq = shipFactory.getBateauCinq();
    }

    /**
     * Set orientation.
     *
     * @param orientation the orientation
     */
    public void setOrientation(int orientation){
        bateauDeux.setOrientation(orientation);
        bateauTrois.setOrientation(orientation);
        bateauTroisDeux.setOrientation(orientation);
        bateauQuatre.setOrientation(orientation);
        bateauCinq.setOrientation(orientation);
    }

    /**
     * Set selection boolean.
     *
     * @param x      the x
     * @param y      the y
     * @param taille the taille
     * @return the boolean
     */
    public boolean setSelection(int x, int y, int taille){
        if (taille == GameManager.BATEAUDEUX){
            toConfirm[0] = bateauDeux.setCoord(x,y);
            return toConfirm[0];
        } else if (taille == GameManager.BATEAUTROIS1){
            toConfirm[1] = bateauTrois.setCoord(x, y);
            return toConfirm[1];
        } else if (taille == GameManager.BATEAUTROIS2){
            toConfirm[2] = bateauTroisDeux.setCoord(x, y);
            return toConfirm[2];
        } else if (taille == GameManager.BATEAUQUATRE){
            toConfirm[3] = bateauQuatre.setCoord(x, y);
            return toConfirm[3];
        } else if (taille == GameManager.BATEAUCINQ){
            toConfirm[4] = bateauCinq.setCoord(x, y);
            return toConfirm[4];
        }
        return false;
    }

    /**
     * Get selection case [ ].
     *
     * @param taille the taille
     * @return the case [ ]
     */
    public Case[] getSelection(int taille){
        if (taille == GameManager.BATEAUDEUX){
            return bateauDeux.getCases();
        } else if (taille == GameManager.BATEAUTROIS1){
            return bateauTrois.getCases();
        } else if (taille == GameManager.BATEAUTROIS2){
            return bateauTroisDeux.getCases();
        } else if (taille == GameManager.BATEAUQUATRE){
            return bateauQuatre.getCases();
        } else if (taille == GameManager.BATEAUCINQ){
            return bateauCinq.getCases();
        }
        return null;
    }

    /**
     * Validation case boolean.
     *
     * @param valider the valider
     * @param taille  the taille
     * @return the boolean
     */
    public boolean validationCase(Case[] valider, int taille){
        ArrayList<Case> positionBateau = getCaseToCheck(taille);
        for (Case c : valider) {
            if (c.getY() == -1 || c.getX() == -1) return false;
            if (positionBateau.contains(c))
                return false;
        }
        return true;
    }

    /**
     * Reset pos.
     *
     * @param taille the taille
     */
    public void resetPos(int taille){
        if (taille == GameManager.BATEAUDEUX){
            bateauDeux.resetPos();
            toConfirm[0] = false;
        } else if (taille == GameManager.BATEAUTROIS1){
            bateauTrois.resetPos();
            toConfirm[1] = false;
        } else if (taille == GameManager.BATEAUTROIS2){
            bateauTroisDeux.resetPos();
            toConfirm[2] = false;
        } else if (taille == GameManager.BATEAUQUATRE){
            bateauQuatre.resetPos();
            toConfirm[3] = false;
        } else if (taille == GameManager.BATEAUCINQ){
            bateauCinq.resetPos();
            toConfirm[4] = false;
        }
    }

    /**
     * Reset all pos.
     */
    public void resetAllPos(){
        this.plateau = new int[10][10];
        toConfirm[0] = false;
        toConfirm[1] = false;
        toConfirm[2] = false;
        toConfirm[3] = false;
        toConfirm[4] = false;
        bateauDeux.resetPos();
        bateauTrois.resetPos();
        bateauTroisDeux.resetPos();
        bateauQuatre.resetPos();
        bateauCinq.resetPos();
    }

    /**
     * Get case to check array list.
     *
     * @param taille the taille
     * @return the array list
     */
    public ArrayList<Case> getCaseToCheck(int taille){
        ArrayList<Case> positionBateau = new ArrayList<Case>();
        Case[] c2 = bateauDeux.getCases();
        Case[] c31 = bateauTrois.getCases();
        Case[] c32 = bateauTroisDeux.getCases();
        Case[] c4 = bateauQuatre.getCases();
        Case[] c5 = bateauCinq.getCases();

        if (!(taille == GameManager.BATEAUDEUX))
            positionBateau.addAll(Arrays.asList(c2));
        if (!(taille == GameManager.BATEAUTROIS1))
            positionBateau.addAll(Arrays.asList(c31));
        if (!(taille == GameManager.BATEAUTROIS2))
            positionBateau.addAll(Arrays.asList(c32));
        if (!(taille == GameManager.BATEAUQUATRE))
            positionBateau.addAll(Arrays.asList(c4));
        if (!(taille == GameManager.BATEAUCINQ))
            positionBateau.addAll(Arrays.asList(c5));
        return positionBateau;
    }

    /**
     * Get case valider array list.
     *
     * @return the array list
     */
    public ArrayList<Case> getCaseValider(){
        ArrayList<Case> positionBateau = new ArrayList<Case>(17);
        Case[] c2 = bateauDeux.getCases();
        Case[] c31 = bateauTrois.getCases();
        Case[] c32 = bateauTroisDeux.getCases();
        Case[] c4 = bateauQuatre.getCases();
        Case[] c5 = bateauCinq.getCases();

        positionBateau.addAll(Arrays.asList(c2));
        positionBateau.addAll(Arrays.asList(c31));
        positionBateau.addAll(Arrays.asList(c32));
        positionBateau.addAll(Arrays.asList(c4));
        positionBateau.addAll(Arrays.asList(c5));

        return positionBateau;
    }

    /**
     * Is valide boolean.
     *
     * @return the boolean
     */
    public boolean isValide(){
        for (int i = 0; i < toConfirm.length; i++){
            if (!toConfirm[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get plateau int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getPlateau() {
        return plateau;
    }

    /**
     * Is over boolean.
     *
     * @return the boolean
     */
    public boolean isOver() {
        if (!this.bateauCinq.isDead()) return false;
        if (!this.bateauQuatre.isDead()) return false;
        if (!this.bateauTrois.isDead()) return false;
        if (!this.bateauTroisDeux.isDead()) return false;
        if (!this.bateauDeux.isDead()) return false;
        return true;
    }

    /**
     * Remove munition.
     *
     * @param ammoType the ammo type
     */
    public void removeMunition(int ammoType) {
        if (ammoType == 1) this.bateauQuatre.setMunition(this.bateauQuatre.getMunition()-1);
        if (ammoType == 2) this.bateauCinq.setMunition(this.bateauCinq.getMunition()-1);
    }

    /**
     * Gets x munition.
     *
     * @return the x munition
     */
    public int getXMunition() {
        return this.bateauQuatre.getMunition();
    }

    /**
     * Gets cross munition.
     *
     * @return the cross munition
     */
    public int getCrossMunition() {return this.bateauCinq.getMunition();
    }
}
