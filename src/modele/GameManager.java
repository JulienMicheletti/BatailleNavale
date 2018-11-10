package modele;

import modele.joueurs.Joueur;
import modele.joueurs.JoueurIA;

import java.io.Serializable;
import java.util.Observable;

public class GameManager extends Observable implements Serializable{
    private Joueur playerH;
    private JoueurIA playerIA;

    public GameManager(){
        this.playerH = new Joueur();
        this.playerIA = new JoueurIA();
    }

    public int[][] getPlayerPlateau(){
        return playerH.getPlateau();
    }
}
