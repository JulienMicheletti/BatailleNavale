package modele.bots;

import modele.joueurs.JoueurIA;

public interface BotStrategie {

    public int[] viser(int[][] plateau);
    public void notifierToucher();
    public void notifierCouler();

}
