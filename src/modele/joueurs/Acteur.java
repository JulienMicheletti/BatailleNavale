package modele.joueurs;

import modele.Plateau;

public abstract class Acteur {
    private Plateau plateau;

    public Acteur(){
        this.plateau = new Plateau();
    }

    public int[][] getPlateau() {
        return plateau.getPlateau();
    }
}
