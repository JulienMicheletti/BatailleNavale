package modele;

import modele.bateaux.Case;
import modele.bateaux.ShipFactory;
import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauTrois.BateauTrois;

import java.util.ArrayList;
import java.util.Arrays;

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

    public boolean valisationCase(Case[] valider, int taille){
        ArrayList<Case> positionBateau = getCaseToCheck(taille);
        for (Case c : valider) {
            if (positionBateau.contains(c))
                return false;
        }
        return true;
    }

    public void resetPos(int taille){
        if (taille == GameManager.BATEAUDEUX){
            bateauDeux.resetPos();
        } else if (taille == GameManager.BATEAUTROIS1){
            bateauTrois.resetPos();
        } else if (taille == GameManager.BATEAUTROIS2){
            bateauTroisDeux.resetPos();
        } else if (taille == GameManager.BATEAUQUATRE){
            bateauQuatre.resetPos();
        } else if (taille == GameManager.BATEAUCINQ){
            bateauCinq.resetPos();
        }
    }

    public ArrayList<Case> getCaseToCheck(int taille){
        ArrayList<Case> positionBateau = new ArrayList<Case>(17);
        Case[] c2 = bateauDeux.getCases();
        Case[] c31 = bateauTrois.getCases();
        Case[] c32 = bateauTroisDeux.getCases();
        Case[] c4 = bateauQuatre.getCases();
        Case[] c5 = bateauCinq.getCases();

        if (!(taille == GameManager.BATEAUDEUX))
            positionBateau.addAll(Arrays.asList(c2));
        if (!(taille == GameManager.BATEAUTROIS1))
            positionBateau.addAll(Arrays.asList(c31));
        if (!(taille == GameManager.BATEAUTROIS2))
            positionBateau.addAll(Arrays.asList(c32));
        if (!(taille == GameManager.BATEAUQUATRE))
            positionBateau.addAll(Arrays.asList(c4));
        if (!(taille == GameManager.BATEAUCINQ))
            positionBateau.addAll(Arrays.asList(c5));

        return positionBateau;
    }

    public ArrayList<Case> getCaseValider(){
        ArrayList<Case> positionBateau = new ArrayList<Case>(17);
        Case[] c2 = bateauDeux.getCases();
        Case[] c31 = bateauTrois.getCases();
        Case[] c32 = bateauTroisDeux.getCases();
        Case[] c4 = bateauQuatre.getCases();
        Case[] c5 = bateauCinq.getCases();

        positionBateau.addAll(Arrays.asList(c2));
        positionBateau.addAll(Arrays.asList(c31));
        positionBateau.addAll(Arrays.asList(c32));
        positionBateau.addAll(Arrays.asList(c4));
        positionBateau.addAll(Arrays.asList(c5));

        return positionBateau;
    }

    public int[][] getPlateau() {
        return plateau;
    }
}
