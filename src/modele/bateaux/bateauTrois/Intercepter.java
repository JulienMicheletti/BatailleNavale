package modele.bateaux.bateauTrois;

import modele.bateaux.bateauDeux.BateauDeux;

public class Intercepter implements BateauTrois {
    public int x;
    public int y;
    public int orientation;

    public int taille;
    public int cases[];
    public Intercepter(int x, int y, int orientation, int[] cases){
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        taille = 3;
        this.cases = cases;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    @Override
    public void setCases(int[] cases) {
        this.cases = cases;
    }

    public int getTaille() {
        return taille;
    }

    public int[] getCases() {
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
