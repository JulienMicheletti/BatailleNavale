package vue;

import modele.GameManager;
import rmi.client.BatailleClient;
import rmi.serveur.ServerImplementation;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * The type Vue general.
 */
public class VueGeneral extends JPanel implements Observer, Serializable {
    private JFrame f;
    private GameManager gameManager;
    private JPanel options = new JPanel();

    /**
     * Instantiates a new Vue general.
     *
     * @param f  the f
     * @param gm the gm
     */
    public VueGeneral(JFrame f, GameManager gm) {
        gm.addObserver(this);
        this.f = f;
        this.gameManager = gm;
        f.setTitle("Bataille Navale");
        f.setPreferredSize(new Dimension(900, 600));
        this.setLayout(new BorderLayout());
        this.options = new JPanel();
        this.options.setLayout(new GridLayout(1, 6));
        try {
            Image image = ImageIO.read(getClass().getResource("/res/BN.jpg"));
            JLabel label = new JLabel(new ImageIcon(image));
            this.add(label, BorderLayout.CENTER);

            String[] choices = {"XVI Siecle", "XX Siecle", "XXII Siecle"};
            JComboBox<String> cb = new JComboBox(choices);
            cb.setToolTipText("XVI : Bataille navale normale \n XX : Bateaux ont 1 pv \n XXII : Munitions");
            cb.addActionListener(e -> this.gameManager.setFactory(cb.getSelectedIndex()));
            options.add(cb);

            String[] difficulty = {"Facile", "Normal","Difficile"};
            JComboBox<String> cbdiff = new JComboBox(difficulty);
            cbdiff.addActionListener(e -> this.gameManager.setDifficulty(cbdiff.getSelectedIndex()));
            options.add(cbdiff);

            JButton newbutton = new JButton("Nouvelle partie");
            newbutton.addActionListener(e -> this.newGame(f, gm));
            JButton loadbutton = new JButton("Charger partie");
            loadbutton.addActionListener(e -> this.loadGame());
            JButton onlinebutton = new JButton("Client");
            onlinebutton.addActionListener(e -> this.onlineGame(false));
            JButton serverbutton = new JButton("Serveur");
            serverbutton.addActionListener(e -> this.onlineGame(true));
            options.add(newbutton);
            options.add(loadbutton);
            options.add(onlinebutton);
            options.add(serverbutton);
            this.add(options, BorderLayout.NORTH);
        } catch (Exception e) {
            System.out.println("Image not found");
        }

        f.add(this);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    /**
     * New game.
     *
     * @param f  the f
     * @param gm the gm
     */
    public void newGame(JFrame f, GameManager gm) {
        f.remove(this);
        //    gm.initIA();
        gm.setIAGame();
        f.setPreferredSize(new Dimension(800, 800));
        gm.resetLaunch();
        f.setContentPane(new VueSelection(gm));
        f.invalidate();
        f.validate();
        f.pack();
        f.setVisible(true);
    }

    /**
     * Load game.
     */
    public void loadGame() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Save file", "ser");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            ObjectInputStream ois = null;
            try {
                final FileInputStream fichier = new FileInputStream(chooser.getSelectedFile().getName());
                ois = new ObjectInputStream(fichier);
                JFrame f = new JFrame();
                GameManager gm = (GameManager) ois.readObject();
                VuePlateaux vp = new VuePlateaux(f, gm);
                f.setContentPane(vp);
                f.pack();
                f.setVisible(true);
            } catch (final java.io.IOException e) {
                e.printStackTrace();
            } catch (final ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (final IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Online game.
     *
     * @param b the b
     */
    public void onlineGame(boolean b) {
        if (b){//Serveur
            JFrame frame = new JFrame();
            frame.setTitle("Serveur");
            frame.setLayout(new GridLayout(2,0));
            frame.setPreferredSize(new Dimension(500,200));
            frame.add(new JLabel("Vous avez démarrer un serveur pour jouer sur ce pc"));
            frame.add(new JLabel("Fermer cette fenêtre clos le serveur."));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            String args[] = new String[0];
            ServerImplementation.main(args);

            this.f.setVisible(false);
            this.f.dispose();
            frame.pack();
            frame.setVisible(true);
        }else{//Client
            String args[] = new String[1];
            BatailleClient.main(args);
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        if (this.gameManager.getLaunchGame()) {
            this.gameManager.resetLaunch();
            f.setPreferredSize(new Dimension(600, 650));
            VuePlateaux vp = new VuePlateaux(this.f, this.gameManager);
            this.f.setContentPane(vp);
            f.invalidate();
            this.f.validate();
            this.f.pack();
            this.f.setVisible(true);
        }
    }
}
