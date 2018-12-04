package vue;

import modele.GameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

public class VueGeneral extends JPanel implements Observer, Serializable {
    private JFrame f;
    private GameManager gameManager;

    public VueGeneral(JFrame f, GameManager gm) {
        gm.addObserver(this);
        this.f = f;
        this.gameManager = gm;
        f.setTitle("Bataille Navale");
        f.setPreferredSize(new Dimension(600, 600));
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("src/res/BN.jpg"));
            Image image = ImageIO.read(is);
            JLabel label = new JLabel(new ImageIcon(image));
            label.setLayout(new FlowLayout());


            String[] choices = {"XVI Siecle", "XX Siecle", "XXII Siecle"};
            JComboBox<String> cb = new JComboBox(choices);
            cb.setToolTipText("XVI : Bataille navale normale \n XX : Bateaux ont 1 pv \n XXII : Munitions");
            cb.addActionListener(e -> this.gameManager.setFactory(cb.getSelectedIndex()));
            label.add(cb);

            String[] difficulty = {"Facile", "Normal"};
            JComboBox<String> cbdiff = new JComboBox(difficulty);
            cbdiff.addActionListener(e -> this.gameManager.setDifficulty(cbdiff.getSelectedIndex()));
            label.add(cbdiff);

            JButton newbutton = new JButton("Nouvelle partie");
            newbutton.addActionListener(e -> {
                this.newGame(f, gm);
            });
            JButton loadbutton = new JButton("Charger partie");
            loadbutton.addActionListener(e -> {
                this.loadGame();
            });
            JButton onlinebutton = new JButton("Jouer en ligne");
            onlinebutton.addActionListener(e -> {
                this.onlineGame();
            });
            JButton exitbutton = new JButton("Quitter");
            exitbutton.addActionListener(e -> {
                System.exit(1337);
            });
            label.add(newbutton);
            label.add(loadbutton);
            label.add(onlinebutton);
            label.add(exitbutton);
            this.add(label);

        } catch (Exception e) {
            System.out.println("Image not found");
        }

        f.add(this);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

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
                VuePlateaux vp = (VuePlateaux) ois.readObject();
                vp.add();
                JFrame f = new JFrame();
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

    public void onlineGame() {
        //TODO Online logic
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
