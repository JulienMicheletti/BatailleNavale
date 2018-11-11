package modele.bateaux.bateauCinq;

import modele.GameManager;
import modele.bateaux.Case;

public class Commander implements BateauCinq {

    public int x;
    public int y;
    public int orientation;
    public Case cases[];
    public final int taille = 5;

    public Commander(){
        this.x = 0;
        this.y = 0;
        this.orientation = GameManager.HORIZONTAL;
        this.cases = new Case[taille];
        this.cases[0] = new Case(0,0);
        this.cases[1] = new Case(0,0);
        this.cases[2] = new Case(0,0);
        this.cases[3] = new Case(0,0);
        this.cases[4] = new Case(0,0);
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

    public boolean setCoord(int x, int y) {
        if (orientation == GameManager.HORIZONTAL){
            if (x + 2 <= 9 && x - 2 >= 0){
                this.cases[0].setCoord(x-2, y);
                this.cases[1].setCoord(x-1, y);
                this.cases[2].setCoord(x, y);
                this.cases[3].setCoord(x+1, y);
                this.cases[4].setCoord(x+2, y);
                return true;
            }
            return false;
        } else if (orientation == GameManager.VERTICAL){
            if (y+2 <= 9 && y-2 >= 0){
                this.cases[0].setCoord(x, y-2);
                this.cases[1].setCoord(x, y-1);
                this.cases[2].setCoord(x, y);
                this.cases[3].setCoord(x, y+1);
                this.cases[4].setCoord(x, y+2);
                return true;
            }
            return false;
        }
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
