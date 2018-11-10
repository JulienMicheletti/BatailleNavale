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

    public BateauCinq getBateauCinq(int x, int y, int orientation, Case cases[]) {
        return new Galeasse(x, y, orientation, cases);
    }

    public BateauDeux getBateauDeux(int x, int y, int orientation, Case cases[]) {
        return new Voilier(x, y, orientation, cases);
    }

    public BateauTrois getBateauTrois(int x, int y, int orientation, Case cases[]) {
        return new TroisMats(x, y, orientation, cases);
    }

    public BateauQuatre getBateauQuatre(int x, int y, int orientation, Case cases[]) {
        return new Galion(x, y, orientation, cases);
    }
}
