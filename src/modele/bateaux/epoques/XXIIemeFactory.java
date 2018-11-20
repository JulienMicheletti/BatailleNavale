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

    public boolean isMunition(){
        return true;
    }

    public BateauCinq getBateauCinq() {
        return new Commander();
    }

    public BateauDeux getBateauDeux() {
        return new Pedalo();
    }

    public BateauTrois getBateauTrois() {
        return new Intercepter();
    }

    public BateauQuatre getBateauQuatre() {
        return new Destroyer();
    }
}
