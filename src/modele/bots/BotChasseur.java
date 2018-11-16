package modele.bots;

import java.util.Random;

public class BotChasseur implements BotStrategie {

    private boolean chasseur = false;
    private final Random rng = new Random();
    private int lastXpossible = 0;
    private int lastYpossible = 0;
    private int lastXconfirme = 0;
    private int lastYconfirme = 0;

    @Override
    public int[] viser(int[][] plateau) {

        if (!chasseur) {
            //Viser au random ant qu'on a pas trouvé une case a bateau
            int x = 0;
            int y = 0;
            do {
                x = rng.nextInt(10);
                y = rng.nextInt(10);
            } while (plateau[x][y] != 0);
            int[] res = new int[2];
            res[0] = x;
            res[1] = y;
            plateau[x][y] = 1;
            lastXpossible = x;
            lastYpossible = y;
            return res;
        }else{ //mode chasseur
            
        }

        return null;
    }

    @Override
    public void notifierToucher() {
        lastXconfirme = lastXpossible;
        lastYconfirme = lastYpossible;
        this.chasseur = true;
    }

    @Override
    public void notifierCouler() {
        this.chasseur = false;
    }
}
