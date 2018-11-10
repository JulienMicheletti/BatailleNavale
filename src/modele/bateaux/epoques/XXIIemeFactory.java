package modele.bateaux.epoques;

import modele.bateaux.Case;
import modele.bateaux.ShipFactory;
import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauCinq.Commander;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauDeux.Pedalo;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauQuatre.Destroyer;
import modele.bateaux.bateauTrois.BateauTrois;
import modele.bateaux.bateauTrois.Intercepter;

public class XXIIemeFactory implements ShipFactory {

    public BateauCinq getBateauCinq(int x, int y, int orientation, Case[] cases) {
        return new Commander(x, y, orientation, cases);
    }

    public BateauDeux getBateauDeux(int x, int y, int orientation, Case[] cases) {
        return new Pedalo(x, y, orientation, cases);
    }

    public BateauTrois getBateauTrois(int x, int y, int orientation, Case[] cases) {
        return new Intercepter(x, y, orientation, cases);
    }

    public BateauQuatre getBateauQuatre(int x, int y, int orientation, Case[] cases) {
        return new Destroyer(x, y, orientation, cases);
    }
}
