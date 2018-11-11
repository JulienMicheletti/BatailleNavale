package modele.joueurs;

import modele.Plateau;
import modele.bateaux.Case;
import modele.bateaux.ShipFactory;

public abstract class Acteur {
    private Plateau plateau;

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

    public Case[] getSelection(int taille){
        return this.plateau.getSelection(taille);
    }
}
