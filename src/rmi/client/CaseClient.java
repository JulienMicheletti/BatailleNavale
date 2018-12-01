package rmi.client;

import modele.bateaux.Case;

import java.io.Serializable;

public class CaseClient implements Serializable{
    private int x;
    private int y;

    public CaseClient(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
