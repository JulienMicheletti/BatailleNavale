package modele.bateaux.bateauQuatre;

import modele.GameManager;
import modele.bateaux.Case;

public class Croiseur implements BateauQuatre {

    public int x;
    public int y;
    public int orientation;
    public final int taille = 4;
    public Case cases[];

    public Croiseur(){
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
    public void setCoord(int x, int y) {

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
