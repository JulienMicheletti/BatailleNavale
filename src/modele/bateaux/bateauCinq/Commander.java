package modele.bateaux.bateauCinq;

import modele.bateaux.Case;

public class Commander implements BateauCinq {
    public int x;
    public int y;
    public int orientation;
    public int taille;
    public Case cases[];

    public Commander(int x, int y, int orientation, Case cases[]){
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        taille = 5;
        this.cases = cases;
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

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setCases(Case cases[]) {
        this.cases = cases;
    }

    public int getTaille() {
        return taille;
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
