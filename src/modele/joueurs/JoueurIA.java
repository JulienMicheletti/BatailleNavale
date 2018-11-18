package modele.joueurs;

import modele.GameManager;
import modele.bateaux.Case;
import modele.bots.BotFullRandom;
import modele.bots.BotStrategie;

import java.security.SecureRandom;
import java.util.ArrayList;

public class JoueurIA extends Acteur {

    private int[][] plateauAdverse = new int[10][10];
    private BotStrategie bs = new BotFullRandom();

    public JoueurIA(){
    }

    public void poserBateaux() {
        this.poseRandom(GameManager.BATEAUDEUX);
        this.poseRandom(GameManager.BATEAUTROIS2);
        this.poseRandom(GameManager.BATEAUTROIS1);
        this.poseRandom(GameManager.BATEAUQUATRE);
        this.poseRandom(GameManager.BATEAUCINQ);
    }

    private void poseRandom(int taille){
        SecureRandom rng = new SecureRandom();
        int x;
        int y;
        Case[] test;
        do{
            x = rng.nextInt(10);
            y = rng.nextInt(10);
            if (Math.random() < 0.5){
                this.setOrientation(GameManager.HORIZONTAL);
            }else{
                this.setOrientation(GameManager.VERTICAL);
            }
            this.setSelection(x,y, taille);
            test = this.plateau.getSelection(taille);
        }while(!this.validerCase(test,taille));
    }

    public int[] viser(){
        return this.bs.viser(this.plateauAdverse);
    }

}
