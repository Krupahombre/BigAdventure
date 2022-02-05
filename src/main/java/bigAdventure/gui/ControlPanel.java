package bigAdventure.gui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private final JButton fightButton;
    private final JButton defenseButton;
    private final JProgressBar healthBar;
    private final JButton runButton;

    public ControlPanel(ActionPanel actionPanel, LogPanel logPanel) {
        fightButton = new JButton("Fight");
        fightButton.setFocusable(false);
        fightButton.addActionListener( (e) -> actionPanel.moveBlue(1000,1));
        fightButton.addActionListener( (e) -> logPanel.printLog("You are punching the enemy"));

        defenseButton = new JButton("Defend yourself");
        defenseButton.setFocusable(false);
        defenseButton.addActionListener( (e) -> actionPanel.moveRed(1000,0));
        defenseButton.addActionListener( (e) -> health());
        defenseButton.addActionListener( (e) -> logPanel.printLog("You are defending yourself"));

        runButton = new JButton("Run");
        runButton.setFocusable(false);
        runButton.addActionListener( (e) -> actionPanel.moveRed(2000,15));
        runButton.addActionListener( (e) -> logPanel.printLog("You are running away"));

        healthBar = new JProgressBar(0,100);
        healthBar.setPreferredSize(new Dimension(800,70));
        healthBar.setValue(100);
        healthBar.setStringPainted(true);
        healthBar.setForeground(Color.red);
        healthBar.setBackground(Color.black);
        healthBar.setFont(new Font("MV Boil", Font.BOLD,25));

        initializeComponent();
    }

    private void initializeComponent(){
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        this.setBorder(BorderFactory.createTitledBorder("Controls"));
        this.add(fightButton);
        this.add(defenseButton);
        this.add(runButton);
        this.add(healthBar);
    }

    private void health() {
        healthBar.setValue(healthBar.getValue()-10);
        healthBar.setString(healthBar.getValue() + "%");
    }
}
