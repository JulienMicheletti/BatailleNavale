package modele.bateaux;

public class Case {

    private int x;
    private int y;
    private boolean toucher;

    public Case(int x, int y){
        this.x = x;
        this.y = y;
        toucher = false;
    }

    public void setCoord(int x, int y){
        this.x =x;
        this.y =y;
    }

    public void setToucher(){
        toucher = false;
    }

    public boolean getToucher(){
        return toucher;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Case tocheck = (Case)obj;
        //System.out.println(this.x+ "=="+tocheck.getX()+"/"+this.y+"=="+tocheck.getY());
        return (this.x == tocheck.getX() && this.y == tocheck.getY());
    }
}
