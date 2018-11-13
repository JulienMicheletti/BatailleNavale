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
    private BateauTrois bateauTroisDeux;
    private BateauCinq bateauCinq;
    private BateauDeux bateauDeux;

    public Plateau(){
        this.plateau = new int[10][10]; //List ou double tab de "Cases" ?
    }

    public void setShipFactory(ShipFactory factory){
        this.shipFactory = factory;
        bateauDeux = shipFactory.getBateauDeux();
        bateauTrois = shipFactory.getBateauTrois();
        bateauTroisDeux = shipFactory.getBateauTrois();
        bateauQuatre = shipFactory.getBateauQuatre();
        bateauCinq = shipFactory.getBateauCinq();
    }

    public void setOrientation(int orientation){
        bateauDeux.setOrientation(orientation);
        bateauTrois.setOrientation(orientation);
        bateauTroisDeux.setOrientation(orientation);
        bateauQuatre.setOrientation(orientation);
        bateauCinq.setOrientation(orientation);
    }

    public boolean setSelection(int x, int y, int taille){
        if (taille == GameManager.BATEAUDEUX){
            return bateauDeux.setCoord(x,y);
        } else if (taille == GameManager.BATEAUTROIS1){
            return bateauTrois.setCoord(x, y);
        } else if (taille == GameManager.BATEAUTROIS2){
            return bateauTroisDeux.setCoord(x, y);
        } else if (taille == GameManager.BATEAUQUATRE){
            return bateauQuatre.setCoord(x, y);
        } else if (taille == GameManager.BATEAUCINQ){
            return bateauCinq.setCoord(x, y);
        }
        return false;
    }

    public Case[] getSelection(int taille){
        if (taille == GameManager.BATEAUDEUX){
            return bateauDeux.getCases();
        } else if (taille == GameManager.BATEAUTROIS1){
            return bateauTrois.getCases();
        } else if (taille == GameManager.BATEAUTROIS2){
            return bateauTroisDeux.getCases();
        } else if (taille == GameManager.BATEAUQUATRE){
            return bateauQuatre.getCases();
        } else if (taille == GameManager.BATEAUCINQ){
            return bateauCinq.getCases();
        }
        return null;
    }

    public int[][] getPlateau() {
        return plateau;
    }
}
