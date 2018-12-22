package modele.bots;

/**
 * The interface Bot strategie.
 */
public interface BotStrategie {

    /**
     * Viser int [ ].
     *
     * @param plateau the plateau
     * @return the int [ ]
     */
    int[] viser(int[][] plateau);

    /**
     * Notifier toucher.
     */
    void notifierToucher();

    /**
     * Notifier couler.
     */
    void notifierCouler();

}
