package bigAdventure.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 800;

    public MainFrame() throws HeadlessException {
        this.setTitle("BigAdventure");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
    }
}
