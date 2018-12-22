package rmi.client;

import java.io.Serializable;

/**
 * The type Case client.
 */
public class CaseClient implements Serializable{
    private int x;
    private int y;

    /**
     * Instantiates a new Case client.
     *
     * @param x the x
     * @param y the y
     */
    public CaseClient(int x, int y){
        this.x = x;
        this.y = y;
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
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }
}
