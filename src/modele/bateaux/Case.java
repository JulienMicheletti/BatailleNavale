package modele.bateaux;

import java.io.Serializable;

/**
 * The type Case.
 */
public class Case implements Serializable {

    private int x;
    private int y;
    private boolean toucher;

    /**
     * Instantiates a new Case.
     *
     * @param x the x
     * @param y the y
     */
    public Case(int x, int y){
        this.x = x;
        this.y = y;
        toucher = false;
    }

    /**
     * Set coord.
     *
     * @param x the x
     * @param y the y
     */
    public void setCoord(int x, int y){
        this.x =x;
        this.y =y;
    }

    /**
     * Set toucher.
     *
     * @param toucher the toucher
     */
    public void setToucher(boolean toucher){
        this.toucher = toucher;
    }

    /**
     * Get toucher boolean.
     *
     * @return the boolean
     */
    public boolean getToucher(){
        return toucher;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Case tocheck = (Case)obj;
        //System.out.println(this.x+ "=="+tocheck.getX()+"/"+this.y+"=="+tocheck.getY());
        return (this.x == tocheck.getX() && this.y == tocheck.getY());
    }

    @Override
    public String toString() {
        return "["+ getX()+","+getY() + ":"+getToucher()+"]";
    }
}
