package modele.bateaux.bateauCinq;

import modele.bateaux.Case;

public interface BateauCinq{
    public void resetPos();
    public int getX();
    public int getY();
    public int getOrientation();
    public Case[] getCases();
    public int getTaille();
    public boolean setCoord(int x, int y);
    public void setX(int x);
    public void setY(int y);
    public void setOrientation(int orientation);
    public void setCases(Case cases[]);
}
