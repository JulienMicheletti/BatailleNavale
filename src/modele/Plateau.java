package modele;

public class Plateau {

    private int[][] plateau;

    public Plateau(){
        this.plateau = new int[10][10]; //List ou double tab de "Cases" ?
    }

    public int[][] getPlateau() {
        return plateau;
    }
}
