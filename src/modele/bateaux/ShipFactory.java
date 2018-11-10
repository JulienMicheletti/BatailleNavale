package modele.bateaux;

import modele.bateaux.bateauCinq.BateauCinq;
import modele.bateaux.bateauDeux.BateauDeux;
import modele.bateaux.bateauQuatre.BateauQuatre;
import modele.bateaux.bateauTrois.BateauTrois;

public interface ShipFactory {
    public BateauCinq getBateauCinq(int x, int y, int orientation, int cases[]);
    public BateauDeux getBateauDeux(int x, int y, int orientation, int cases[]);
    public BateauTrois getBateauTrois(int x, int y, int orientation, int cases[]);
    public BateauQuatre getBateauQuatre(int x, int y, int orientation, int cases[]);


}
