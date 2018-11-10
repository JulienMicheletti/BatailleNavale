package modele.bateaux.epoques;

import modele.bateaux.ShipFactory;
import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauCinq.Commander;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauDeux.Zodiac;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauQuatre.Destroyer;
import modele.bateaux.bateauTrois.BateauTrois;
import modele.bateaux.bateauTrois.Intercepter;
import sun.security.krb5.internal.crypto.Des;

public class XXIIemeFactory implements ShipFactory {
    @Override
    public BateauCinq getBateauCinq(int x, int y, int orientation, int[] cases) {
        return new Commander(x, y, orientation, cases);
    }

    @Override
    public BateauDeux getBateauDeux(int x, int y, int orientation, int[] cases) {
        return new Zodiac(x, y, orientation, cases);
    }

    @Override
    public BateauTrois getBateauTrois(int x, int y, int orientation, int[] cases) {
        return new Intercepter(x, y, orientation, cases);
    }

    @Override
    public BateauQuatre getBateauQuatre(int x, int y, int orientation, int[] cases) {
        return new Destroyer(x, y, orientation, cases);
    }
}
