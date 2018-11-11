package modele.bateaux.bateauCinq;

import modele.GameManager;
import modele.bateaux.Case;

public class PorteAvion implements BateauCinq {

    public int x;
    public int y;
    public int orientation;
    public Case cases[];
    public final int taille = 5;

    public PorteAvion(){
        this.x = 0;
        this.y = 0;
        this.orientation = GameManager.HORIZONTAL;
        this.cases = new Case[taille];
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setCases(Case cases[]) {
        this.cases = cases;
    }

    public int getTaille() {
        return taille;
    }

    @Override
    public boolean setCoord(int x, int y) {
return false;
    }

    public Case[] getCases() {
        return cases;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getOrientation() {
        return orientation;
    }
}
