package bigAdventure.gui;

import bigAdventure.entities.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private final int WINDOW_WIDTH = 1700;
    private final int WINDOW_HEIGHT = 800;
    private final ActionPanel actionPanel;
    private final ControlPanel controlPanel;
    private final LogPanel logPanel;
    private final ProfilePanel profilePanel;
    private final Player player;
    private final Enemy enemy;


    public MainFrame() throws HeadlessException {
        player = new Player("Rycerz", 100,20);
        enemy = new Enemy("Zombie", 60, 2);

        actionPanel = new ActionPanel();
        logPanel = new LogPanel();
        profilePanel = new ProfilePanel();
        controlPanel = new ControlPanel(actionPanel, logPanel, profilePanel, player, enemy);

        initializeComponents();
    }

    private void initializeComponents() {
        this.setTitle("BigAdventure");
        this.setLayout(new BorderLayout(10,10));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);

        this.add(actionPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.SOUTH);
        this.add(logPanel, BorderLayout.WEST);
        this.add(profilePanel, BorderLayout.EAST);
    }


}
