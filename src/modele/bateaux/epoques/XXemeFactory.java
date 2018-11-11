package modele.bateaux.epoques;

import modele.bateaux.Case;
import modele.bateaux.ShipFactory;
import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauCinq.PorteAvion;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauDeux.Zodiac;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauQuatre.Croiseur;
import modele.bateaux.bateauTrois.BateauTrois;
import modele.bateaux.bateauTrois.Furtif;

public class XXemeFactory implements ShipFactory {

    @Override
    public BateauCinq getBateauCinq() {
        return new PorteAvion();
    }

    @Override
    public BateauDeux getBateauDeux() {
        return new Zodiac();
    }

    @Override
    public BateauTrois getBateauTrois() {
        return new Furtif();
    }

    @Override
    public BateauQuatre getBateauQuatre() {
        return new Croiseur();
    }
}
