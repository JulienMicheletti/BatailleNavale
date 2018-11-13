package modele.bots;

import java.util.Random;

public class BotFullRandom implements BotStrategie {

    private final Random rng = new Random();

    public BotFullRandom(){
    }

    @Override
    public int[] viser(int[][] plateau) {
        int x = 0;
        int y = 0;
        do {
            x = rng.nextInt(10);
            y = rng.nextInt(10);
        }while (plateau[x][y] != 0);
        int[] res = new int[2];
        res[0] = x;
        res[1] = y;
        plateau[x][y] = 1;
        return res;
    }

    @Override
    public void notifierToucher() {
    }

    @Override
    public void notifierCouler() {
    }
}
