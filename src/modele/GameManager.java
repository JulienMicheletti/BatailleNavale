package modele;

import modele.bateaux.ShipFactory;
import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauCinq.Commander;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauTrois.BateauTrois;
import modele.bateaux.epoques.XXIIemeFactory;
import modele.joueurs.Joueur;
import modele.joueurs.JoueurIA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class GameManager extends Observable implements Serializable{
    private static int BATEAUDEUX = 2;
    private static int BATEAUTROIS= 3;
    private static int BATEAUQUATRE = 4;
    private static int BATEAUCINQ = 5;
    private Joueur playerH;
    private JoueurIA playerIA;
    private BateauQuatre bateauQuatre;
    private BateauTrois bateauTrois;
    private BateauCinq bateauCinq;
    private BateauDeux bateauDeux;
    private int orientation;
    private int taille;
    private ShipFactory epoque;

    public GameManager(){
        this.playerH = new Joueur();
        this.playerIA = new JoueurIA();
        epoque = new XXIIemeFactory();
        bateauDeux = epoque.getBateauDeux(0, 0, orientation, null);
        bateauTrois = epoque.getBateauTrois(0, 0, orientation, null);
        bateauQuatre = epoque.getBateauQuatre(0, 0, orientation, null);
        bateauCinq = epoque.getBateauCinq(0, 0, orientation, null);
    }

    public void setOrientation(int orientation){
        this.orientation = orientation;
    }

    public void setTaille(int taille){
        this.taille = taille;
    }

    public void positionnerBateau(int x, int y, int cases[], int taille) {
        if (taille == BATEAUDEUX) {
            bateauDeux.setX(x);
            bateauDeux.setY(y);
            bateauDeux.setCases(cases);
        } else if (taille == BATEAUTROIS) {
            bateauTrois.setX(x);
            bateauTrois.setY(y);
            bateauTrois.setCases(cases);
        } else if (taille == BATEAUQUATRE) {
            bateauQuatre.setX(x);
            bateauQuatre.setY(y);
            bateauQuatre.setCases(cases);
        } else if (taille == BATEAUCINQ) {
            bateauCinq.setX(x);
            bateauCinq.setY(y);
            bateauCinq.setCases(cases);
        }
    }

    public int[][] getPlayerPlateau(){
        return playerH.getPlateau();
    }
}
