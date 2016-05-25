import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * Created by Mohammed on 5/18/2016.
 */

@Data
public class Settings extends JFrame {

    private static Settings instance = null;

    public static Settings getInstance() {
        if(instance==null) {
            instance = new Settings();
        }
        return instance;
    }

    public Settings() {
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setName("Clustering");
        Dimension d = new Dimension();
        d.setSize(1024,768);
        this.setJMenuBar(jMenuBar);
        this.setName("K-means Clustering");
        this.setSize(d);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // define the position
        int locX = 200;
        int locY = 200;

        // draw a line (there is no drawPoint..)
        g.drawLine(locX, locY, locX, locY);
    }


}
