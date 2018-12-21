package modele.bateaux.bateauDeux;

import modele.bateaux.Case;

public interface BateauDeux {
    void resetPos();
    int getX();
    int getY();
    int getOrientation();
    Case[] getCases();
    int getTaille();
    boolean setCoord(int x, int y);
    void setX(int x);
    void setY(int y);
    void setOrientation(int orientation);
    void setCases(Case cases[]);
    boolean isDead();
    int getMunition();
    void setMunition(int i);
}
