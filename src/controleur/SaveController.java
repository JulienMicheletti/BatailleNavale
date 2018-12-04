package controleur;

import modele.GameManager;
import vue.VuePlateaux;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveController implements ActionListener {
    VuePlateaux vp;

    public SaveController(VuePlateaux vuePlateaux){
        this.vp = vuePlateaux;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream("save.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(vp);
            oos.flush();
        } catch (final java.io.IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
