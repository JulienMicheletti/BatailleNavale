package modele.bateaux.epoques;

import modele.bateaux.Case;
import modele.bateaux.ShipFactory;
import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauCinq.Galeasse;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauDeux.Voilier;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauQuatre.Galion;
import modele.bateaux.bateauTrois.BateauTrois;
import modele.bateaux.bateauTrois.TroisMats;

public class XVIemeFactory implements ShipFactory {

    public BateauCinq getBateauCinq() {
        return new Galeasse();
    }

    public BateauDeux getBateauDeux() {
        return new Voilier();
    }

    public BateauTrois getBateauTrois() {
        return new TroisMats();
    }

    public BateauQuatre getBateauQuatre() {
        return new Galion();
    }
}
