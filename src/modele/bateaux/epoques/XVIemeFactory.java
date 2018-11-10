package modele.bateaux.epoques;

import modele.bateaux.ShipFactory;
import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauTrois.BateauTrois;

public class XVIemeFactory implements ShipFactory {

    @Override
    public BateauCinq getBateauCinq(int x, int y, int orientation, int[] cases) {
        return null;
    }

    @Override
    public BateauDeux getBateauDeux(int x, int y, int orientation, int[] cases) {
        return null;
    }

    @Override
    public BateauTrois getBateauTrois(int x, int y, int orientation, int[] cases) {
        return null;
    }

    @Override
    public BateauQuatre getBateauQuatre(int x, int y, int orientation, int[] cases) {
        return null;
    }
}
