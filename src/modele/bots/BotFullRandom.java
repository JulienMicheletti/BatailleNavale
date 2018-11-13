package modele.bots;

import java.util.Random;

public class BotFullRandom implements BotStrategie {

    @Override
    public int[] viser(int[][] plateau) {
        Random rng = new Random();
        int x = 0;
        int y = 0;
        do {
            x = rng.nextInt(11);
            y = rng.nextInt(11);
        }while (plateau[x][y] != 0);
        int[] res = new int[2];
        res[0] = x;
        res[1] = y;
        plateau[x][y] = 1;
        return res;
    }
}
