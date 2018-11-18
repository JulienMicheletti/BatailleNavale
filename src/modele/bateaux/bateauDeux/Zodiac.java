package modele.bateaux.bateauDeux;

import modele.GameManager;
import modele.bateaux.Case;

public class Zodiac implements BateauDeux {

    public int x;
    public int y;
    public int orientation;
    public Case cases[];
    public final int taille = 2;
    public int pv = 1;

    public Zodiac(){
        this.x = 0;
        this.y = 0;
        this.orientation = GameManager.HORIZONTAL;
        this.cases = new Case[taille];
        for (int i = 0; i < taille; i++)
            this.cases[i] = new Case(-1,-1);
    }

    public void resetPos(){
        for (int i = 0; i < taille; i++)
            this.cases[i] = new Case(-1,-1);
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

    public boolean isDead() {
        for (Case c : this.cases) {
            if(c.getToucher()) return true;
        }
        return false;
    }

    @Override
    public boolean setCoord(int x, int y) {
        if (orientation == GameManager.HORIZONTAL){
            if (x - 1 >= 0){
                this.cases[0].setCoord(x, y);
                this.cases[1].setCoord(x-1, y);
                return true;
            }
            return false;
        } else if (orientation == GameManager.VERTICAL){
            if (y-1 >= 0){
                this.cases[0].setCoord(x, y);
                this.cases[1].setCoord(x, y-1);
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
