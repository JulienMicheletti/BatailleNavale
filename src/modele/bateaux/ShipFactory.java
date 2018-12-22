package modele.bateaux;

import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauTrois.BateauTrois;

/**
 * The interface Ship factory.
 */
public interface ShipFactory {
    /**
     * Is munition boolean.
     *
     * @return the boolean
     */
    boolean isMunition();

    /**
     * Gets bateau cinq.
     *
     * @return the bateau cinq
     */
    BateauCinq getBateauCinq();

    /**
     * Gets bateau deux.
     *
     * @return the bateau deux
     */
    BateauDeux getBateauDeux();

    /**
     * Gets bateau trois.
     *
     * @return the bateau trois
     */
    BateauTrois getBateauTrois();

    /**
     * Gets bateau quatre.
     *
     * @return the bateau quatre
     */
    BateauQuatre getBateauQuatre();
}
