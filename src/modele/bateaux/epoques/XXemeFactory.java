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
    public BateauCinq getBateauCinq(int x, int y, int orientation, Case cases[]) {
        return new PorteAvion(x, y, orientation, cases);
    }

    @Override
    public BateauDeux getBateauDeux(int x, int y, int orientation, Case cases[]) {
        return new Zodiac(x, y, orientation, cases);
    }

    @Override
    public BateauTrois getBateauTrois(int x, int y, int orientation, Case cases[]) {
        return new Furtif(x, y, orientation, cases);
    }

    @Override
    public BateauQuatre getBateauQuatre(int x, int y, int orientation, Case cases[]) {
        return new Croiseur(x, y, orientation, cases);
    }
}
