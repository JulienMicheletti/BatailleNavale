package modele.bateaux;

import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauTrois.BateauTrois;

public interface ShipFactory {
    boolean isMunition();
    BateauCinq getBateauCinq();
    BateauDeux getBateauDeux();
    BateauTrois getBateauTrois();
    BateauQuatre getBateauQuatre();
}
