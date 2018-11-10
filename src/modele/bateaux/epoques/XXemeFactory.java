package modele.bateaux.epoques;

import modele.bateaux.Case;
import modele.bateaux.ShipFactory;
import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauTrois.BateauTrois;

public class XXemeFactory implements ShipFactory {

    @Override
    public BateauCinq getBateauCinq(int x, int y, int orientation, Case cases[]) {
        return null;
    }

    @Override
    public BateauDeux getBateauDeux(int x, int y, int orientation, Case cases[]) {
        return null;
    }

    @Override
    public BateauTrois getBateauTrois(int x, int y, int orientation, Case cases[]) {
        return null;
    }

    @Override
    public BateauQuatre getBateauQuatre(int x, int y, int orientation, Case cases[]) {
        return null;
    }
}
