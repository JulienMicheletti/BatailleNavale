package modele.bots;

import modele.bateaux.Case;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class BotChasseurFort implements BotStrategie, Serializable{

    private boolean chasseur = false;
    private final Random rng = new Random();
    private int lastX = 0;
    private int lastY = 0;
    private ArrayList<Case> interest = new ArrayList<>();
    private int size = 0;

    @Override
    public int[] viser(int[][] plateau) {

        if (size == 0) size = plateau.length;
        if (interest.isEmpty()) {
            return this.tirerAuHasard(plateau);
        }else{ //mode chasseur
            Case c = null;
            boolean trouve = false;
            while (!interest.isEmpty() && !trouve){
                c = this.interest.remove(0);
                if (plateau[c.getX()][c.getY()] == 0) trouve = true; // Cas si on trouve une case d'interet eligible a un tir
                if (plateau[c.getX()][c.getY()] != 0 && this.interest.isEmpty() ) return this.tirerAuHasard(plateau);;
            }

            int x = c.getX();
            int y = c.getY();
            int[] res = new int[2];
            res[0] = x;
            res[1] = y;
            plateau[x][y] = 1;
            lastX = x;
            lastY = y;
            return res;
        }
    }

    public int[] tirerAuHasard(int[][] plateau){
        //Viser au random ant qu'on a pas trouvé une case a bateau
        int x = 0;
        int y = 0;
        int reste;
        do {
            x = rng.nextInt(10);
            y = rng.nextInt(10);
            reste = (x+y) %2;
        } while (plateau[x][y] != 0 || reste != 0 );
        int[] res = new int[2];
        res[0] = x;
        res[1] = y;
        plateau[x][y] = 1;
        lastX = x;
        lastY = y;
        return res;
    }

    @Override
    public void notifierToucher() {
        if (this.lastX-1 >= 0){
            Case c1 = new Case(this.lastX-1,this.lastY);
            this.interest.add(c1);
        }
        if (this.lastX+1 < this.size){
            Case c2 = new Case(this.lastX+1,this.lastY);
            this.interest.add(c2);
        }
        if (this.lastY-1 >= 0){
            Case c3 = new Case(this.lastX,this.lastY-1);
            this.interest.add(c3);
        }
        if (this.lastY+1 < this.size){
            Case c4 = new Case(this.lastX,this.lastY+1);
            this.interest.add(c4);
        }
    }

    @Override
    public void notifierCouler() {
        this.chasseur = false;
    }
}
