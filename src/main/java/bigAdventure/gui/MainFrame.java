package bigAdventure.gui;

import bigAdventure.rendering.RectangleRenderable;
import bigAdventure.rendering.RenderArea;
import bigAdventure.rendering.Renderable;
import bigAdventure.rendering.RenderingThread;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class MainFrame extends JFrame{
    private final int WINDOW_WIDTH = 1400;
    private final int WINDOW_HEIGHT = 800;
    private ActionPanel actionPanel;
    private ControlPanel controlPanel;
    private LogPanel logPanel;
    private ProfilePanel profilePanel;


    public MainFrame() throws HeadlessException {
        this.setTitle("BigAdventure");
        this.setLayout(new BorderLayout(10,10));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        initializeComponents();
    }

    private void initializeComponents() {
        actionPanel = new ActionPanel();
        controlPanel = new ControlPanel();
        logPanel = new LogPanel();
        profilePanel = new ProfilePanel();

        this.add(actionPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.SOUTH);
        this.add(logPanel, BorderLayout.WEST);
        this.add(profilePanel, BorderLayout.EAST);
    }


}
