import modele.GameManager;
import vue.VueGeneral;
import vue.VuePlateaux;

public class main {
    public static void main(String[] args){
        GameManager gm = new GameManager();
        new VueGeneral(gm);
    }
}
