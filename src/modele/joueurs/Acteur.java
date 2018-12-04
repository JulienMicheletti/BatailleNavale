package modele.joueurs;

import modele.Plateau;
import modele.bateaux.Case;
import modele.bateaux.ShipFactory;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Acteur implements Serializable {
    protected Plateau plateau;

    public Acteur(){
        this.plateau = new Plateau();
    }

    public int[][] getPlateau() {
        return plateau.getPlateau();
    }

    public void setFactory(ShipFactory factory){
        this.plateau.setShipFactory(factory);
    }

    public void setOrientation(int orientation){
        this.plateau.setOrientation(orientation);
    }

    public boolean setSelection(int x, int y, int taille){
        return this.plateau.setSelection(x, y, taille);
    }

    public boolean plateauValide(){ return this.plateau.isValide(); }

    public Case[] getSelection(int taille){
        return this.plateau.getSelection(taille);
    }

    public boolean validerCase(Case[] cases, int taille){ return this.plateau.validationCase(cases, taille);}

    public ArrayList<Case> getCaseValider(int taille){ return this.plateau.getCaseToCheck(taille); }

    public boolean isOver(){return this.plateau.isOver();}

    public void removeMunition(int ammoType){
        this.plateau.removeMunition(ammoType);
    }

    public void resetPos(int taille){ this.plateau.resetPos(taille);}

    public int getCrossMunition() {return this.plateau.getCrossMunition();}

    public int getXMunition() {return this.plateau.getXMunition(); }
}
