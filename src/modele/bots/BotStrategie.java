package modele.bots;

public interface BotStrategie {

    int[] viser(int[][] plateau);
    void notifierToucher();
    void notifierCouler();

}
