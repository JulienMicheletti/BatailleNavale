package modele.joueurs;

import modele.bots.BotFullRandom;
import modele.bots.BotStrategie;

public class JoueurIA extends Acteur {

    private int[][] plateauAdverse = new int[10][10];
    private BotStrategie bs = new BotFullRandom();

    public int[] viser(){
        return this.bs.viser(this.plateauAdverse);
    }

}
