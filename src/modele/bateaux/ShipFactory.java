package modele.bateaux;

import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauTrois.BateauTrois;

public interface ShipFactory {
    public BateauCinq getBateauCinq();
    public BateauDeux getBateauDeux();
    public BateauTrois getBateauTrois();
    public BateauQuatre getBateauQuatre();
}
