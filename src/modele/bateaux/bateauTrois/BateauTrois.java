package modele.bateaux.bateauTrois;

public interface BateauTrois {
    public int getX();
    public int getY();
    public int getOrientation();
    public int[] getCases();
    public int getTaille();
    public void setX(int x);
    public void setY(int y);
    public void setOrientation(int orientation);
    public void setCases(int cases[]);
}
