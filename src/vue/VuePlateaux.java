package vue;

import controleur.GameController;
import modele.GameManager;
import javax.swing.*;
import java.awt.*;
import java.util.Observer;

public class VuePlateaux extends JPanel implements Observer {

    protected JButton boardJoueur[][] = new JButton[10][10];
    protected JButton boardAdversaire[][] = new JButton[10][10];
    private GameManager gm;
    private JPanel contentJoueur;
    private JPanel contentIAdversaire;
    private JPanel afficheur;
    private JPanel selectionBateau;

    public VuePlateaux(GameManager gm){
        super();
        gm.addObserver(this);
        this.gm = gm;
        this.contentIAdversaire = new JPanel();
        this.contentJoueur = new JPanel();
        this.afficheur = new JPanel();
        this.selectionBateau = new JPanel();
        setAffichage();
    }

    public void setAffichage(){
        this.setLayout(new GridLayout(3, 0));

        //Panel for boat selection, will be replaced by another Panel when
        ButtonGroup bg = new ButtonGroup();
        JRadioButton br1 = new JRadioButton("Bateau de 2 cases");
        JRadioButton br2 = new JRadioButton("Bateau de 3 cases");
        JRadioButton br3 = new JRadioButton("Bateau de 4 cases");
        bg.add(br1);
        bg.add(br2);
        bg.add(br3);
        br1.setSelected(true);
        JButton confirmation = new JButton("Confirmer");
        this.selectionBateau.add(Box.createVerticalStrut(50));
        this.selectionBateau.add(new JLabel("Coucou, pose tes bateaux fdp"));
        this.selectionBateau.add(Box.createHorizontalStrut(500));
        this.selectionBateau.add(br1);
        this.selectionBateau.add(br2);
        this.selectionBateau.add(br3);
        this.selectionBateau.add(Box.createHorizontalStrut(30));
        this.selectionBateau.add(Box.createVerticalStrut(150));
        this.selectionBateau.add(confirmation);

        //Board parts for self and CPU
        contentIAdversaire.setLayout(new GridLayout(11, 11));
        contentJoueur.setLayout(new GridLayout(11, 11));
        for (int i = 0; i < 11; i++){
            JButton caseindexJ = new JButton(i+"");
            JButton caseindexA = new JButton(i+"");
            caseindexJ.setEnabled(false);
            caseindexA.setEnabled(false);
            contentJoueur.add(caseindexJ);
            contentIAdversaire.add(caseindexA);
        }

        for (int i = 0; i < boardAdversaire.length; i++){
            JButton caseindexJ = new JButton((i+1)+"");
            JButton caseindexA = new JButton((i+1)+"");
            caseindexJ.setEnabled(false);
            caseindexA.setEnabled(false);
            contentJoueur.add(caseindexJ);
            contentIAdversaire.add(caseindexA);
            for (int j = 0; j < boardJoueur.length; j++){
                boardAdversaire[i][j] = new JButton();
                boardAdversaire[i][j].setEnabled(false);
                boardJoueur[i][j] = new JButton();
                boardAdversaire[i][j].addActionListener(new GameController(gm, j, i,false));
                boardJoueur[i][j].addActionListener(new GameController(gm, j, i,true));
                contentIAdversaire.add(boardAdversaire[i][j]);
                contentJoueur.add(boardJoueur[i][j]);
            }
        }

        //End of initialisation, show Panels
        this.add(contentIAdversaire);
        this.add(this.selectionBateau);
        this.add(contentJoueur);

    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}
