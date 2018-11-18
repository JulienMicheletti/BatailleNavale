package vue;

import modele.GameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

public class VueGeneral implements Observer {
    private JFrame f;
    private JPanel mainmenu;
    private GameManager gameManager;

    public VueGeneral(GameManager gm){
        gm.addObserver(this);
        this.gameManager = gm;
        this.f=new JFrame();
        f.setTitle("Bataille Navale");
        f.setPreferredSize(new Dimension(600,600));
        this.mainmenu = new JPanel();
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("src/res/BN.jpg"));
            Image image = ImageIO.read(is);
            JLabel label = new JLabel(new ImageIcon(image));
            label.setLayout(new FlowLayout());

            JButton newbutton = new JButton("Nouvelle partie");
            newbutton.addActionListener(e -> {
                this.newGame(f,gm);
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
            mainmenu.add(label);

        } catch (Exception e) {
            System.out.println("Image not found");
        }

        f.add(mainmenu);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    public void newGame(JFrame f, GameManager gm){
        f.remove(this.mainmenu);
        f.setPreferredSize(new Dimension(800,800));
        f.setContentPane(new VueSelection(gm));
        f.invalidate();
        f.validate();
        f.pack();
        f.setVisible(true);
    }

    public void loadGame(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Save file", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            //TODO Loading logic
        }
    }

    public void onlineGame(){
        //TODO Online logic
    }

    @Override
    public void update(Observable o, Object arg) {
        if (this.gameManager.getLaunchGame()){
            f.setPreferredSize(new Dimension(800, 800));
            this.f.setContentPane(new VuePlateaux(this.f,this.gameManager));
            f.invalidate();
            this.f.validate();
            this.f.pack();
            this.f.setVisible(true);
        }
    }
}
