package bigAdventure.gui;

import bigAdventure.entities.*;
import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private final JButton fightButton;
    private final JButton defenseButton;
    private final JProgressBar healthBar;
    private final JButton runButton;
    private final Calculator randDmgCalculator = new RandomDamageCalculator();

    public ControlPanel(ActionPanel actionPanel, LogPanel logPanel,ProfilePanel profilePanel, Player player, Enemy enemy) {
        fightButton = new JButton("Fight");
        fightButton.setFocusable(false);
        fightButton.addActionListener( (e) -> {
            actionPanel.moveBlue(1000,1);
            if(enemy.isAlive(enemy.getHealth())){
                enemy.receiveDamage(randDmgCalculator.calculateDamage(player.getBaseDamage()));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                logPanel.printLog("You are punching the enemy\n" + "Enemy HP left: " + enemy.getHealth());
            } else {
                logPanel.printLog("Enemy is dead!");
                profilePanel.setEnemyCounter(profilePanel.getEnemyCounter()+1);
                profilePanel.setGoldCounter(profilePanel.getGoldCounter()+69);
                profilePanel.setRoomCounter(profilePanel.getRoomCounter()+1);
            }
        });

        defenseButton = new JButton("Defend yourself");
        defenseButton.setFocusable(false);
        defenseButton.addActionListener( (e) -> {
            actionPanel.moveRed(1000,0);
            player.receiveDamage(randDmgCalculator.calculateDamage(enemy.getBaseDamage()));
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            health(player.getHealth());
            logPanel.printLog("You are defending yourself\n" + "Your HP left: " + player.getHealth());
        });

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

    private void health(int health) {
        healthBar.setValue(health);
        healthBar.setString(healthBar.getValue() + "%");
    }
}
