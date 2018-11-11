package modele;

import modele.bateaux.Case;
import modele.bateaux.ShipFactory;
import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauTrois.BateauTrois;

public class Plateau {
    private int[][] plateau;
    private ShipFactory shipFactory;
    private BateauQuatre bateauQuatre;
    private BateauTrois bateauTrois;
    private BateauCinq bateauCinq;
    private BateauDeux bateauDeux;

    public Plateau(){
        this.plateau = new int[10][10]; //List ou double tab de "Cases" ?
    }

    public void setShipFactory(ShipFactory factory){
        this.shipFactory = factory;
        bateauDeux = shipFactory.getBateauDeux();
        bateauTrois = shipFactory.getBateauTrois();
        bateauQuatre = shipFactory.getBateauQuatre();
        bateauCinq = shipFactory.getBateauCinq();
    }

    public void setOrientation(int orientation){
        bateauDeux.setOrientation(orientation);
        bateauTrois.setOrientation(orientation);
        bateauQuatre.setOrientation(orientation);
        bateauCinq.setOrientation(orientation);
    }

    public boolean setSelection(int x, int y, int taille){
        if (taille == GameManager.BATEAUDEUX){
            bateauDeux.setX(x);
            bateauDeux.setY(y);
        } else if (taille == GameManager.BATEAUTROIS1){
            bateauTrois.setX(x);
            bateauTrois.setY(y);
        } else if (taille == GameManager.BATEAUQUATRE){
            bateauQuatre.setX(x);
            bateauQuatre.setY(y);
        } else if (taille == GameManager.BATEAUCINQ){
            return bateauCinq.setCoord(x, y);
        }
        return false;
    }

    public Case[] getSelection(int taille){
        if (taille == GameManager.BATEAUDEUX){

        } else if (taille == GameManager.BATEAUTROIS1){

        } else if (taille == GameManager.BATEAUQUATRE){

        } else if (taille == GameManager.BATEAUCINQ){
            return bateauCinq.getCases();
        }
        return null;
    }

    public int[][] getPlateau() {
        return plateau;
    }
}
