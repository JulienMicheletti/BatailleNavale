package modele.bateaux.bateauTrois;

import modele.bateaux.Case;

public interface BateauTrois {
    public int getX();
    public int getY();
    public int getOrientation();
    public Case[] getCases();
    public int getTaille();
    public void setCoord(int x, int y);
    public void setX(int x);
    public void setY(int y);
    public void setOrientation(int orientation);
    public void setCases(Case cases[]);
}
