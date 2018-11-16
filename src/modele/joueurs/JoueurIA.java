package modele.joueurs;

import modele.bots.BotStrategie;

public class JoueurIA extends Acteur {

    private int[][] plateauAdverse;
    private BotStrategie bs;

    public int[] viser(){
        return this.bs.viser(this.plateauAdverse);
    }

}
