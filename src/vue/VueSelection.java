package vue;

import controleur.PositionController;
import modele.GameManager;
import modele.bateaux.Case;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;

public class VueSelection extends JPanel implements Observer {
    private GameManager gameManager;
    protected JButton boardJoueur[][] = new JButton[10][10];
    private JPanel board;
    private JPanel options;
    private JButton bateaux[] = new JButton[5];
    private JRadioButton horizontal;
    private JRadioButton vertical;
    private ButtonGroup position;

    public VueSelection(GameManager gm){
        super();
        gm.addObserver(this);
        this.gameManager = gm;
        this.board = new JPanel();
        this.options = new JPanel();
        this.horizontal = new JRadioButton("Horizontal");
        this.vertical = new JRadioButton("Vertical");
        this.position = new ButtonGroup();
        this.position.add(horizontal);
        this.position.add(vertical);
        this.horizontal.setSelected(true);
        setAffichage();
    }

    public void setAffichage(){
        this.setLayout(new BorderLayout());

        board.setLayout(new GridLayout(11, 11));
        options.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < 11; i++){
            JButton caseindexJ = new JButton(i+"");
            caseindexJ.setEnabled(false);
            board.add(caseindexJ);
        }


        for (int i = 0; i < boardJoueur.length; i++){
            JButton caseindexJ = new JButton((i+1)+"");
            caseindexJ.setEnabled(false);
            board.add(caseindexJ);
            for (int j = 0; j < boardJoueur.length; j++){
                boardJoueur[i][j] = new JButton();
                boardJoueur[i][j].addMouseListener(new PositionController(this.gameManager, j, i));
                board.add(boardJoueur[i][j]);
            }
        }

        bateaux[0] = new JButton("Bateau 2");
        bateaux[1] = new JButton("Bateau 3_1");
        bateaux[2] = new JButton("Bateau 3_2");
        bateaux[3] = new JButton("Bateau 4");
        bateaux[4] = new JButton("Bateau 5");
        bateaux[0].addActionListener(e -> {
            gameManager.setTaille(GameManager.BATEAUDEUX);
        });
        bateaux[1].addActionListener(e -> {
            gameManager.setTaille(GameManager.BATEAUTROIS1);
        });
        bateaux[2].addActionListener(e -> {
            gameManager.setTaille(GameManager.BATEAUTROIS2);
        });
        bateaux[3].addActionListener(e -> {
            gameManager.setTaille(GameManager.BATEAUQUATRE);
        });
        bateaux[4].addActionListener(e -> {
            gameManager.setTaille(GameManager.BATEAUCINQ);
        });
        options.add(bateaux[0]);
        options.add(bateaux[1]);
        options.add(bateaux[2]);
        options.add(bateaux[3]);
        options.add(bateaux[4]);

        horizontal.addActionListener(e ->{
            gameManager.setOrientation(GameManager.HORIZONTAL);
        });
        vertical.addActionListener(e -> {
            gameManager.setOrientation(GameManager.VERTICAL);

        });
        options.add(horizontal);
        options.add(vertical);

        this.add(board, BorderLayout.CENTER);
        this.add(options, BorderLayout.EAST);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if (gameManager.getTaille() >= 2) {
            for (int i = 0; i < boardJoueur.length; i++) {
                for (int j = 0; j < boardJoueur[i].length; j++) {
                    if (!boardJoueur[i][j].getBackground().equals(Color.GREEN))
                        boardJoueur[i][j].setBackground(null);
                }
            }
            Case[] selection = gameManager.getSelectionBateau();
            for (Case c : selection) {
                if (!boardJoueur[c.getY()][c.getX()].getBackground().equals(Color.GREEN))
                    boardJoueur[c.getY()][c.getX()].setBackground(Color.BLUE);
            }
        } else if (gameManager.getTaille() == 0){
            Case[] selection = gameManager.getSelectionBateau();
            for (Case c : selection) {
                boardJoueur[c.getY()][c.getX()].setBackground(Color.GREEN);
            }
        }
    }
}
