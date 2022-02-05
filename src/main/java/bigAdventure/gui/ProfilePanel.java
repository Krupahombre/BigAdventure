package bigAdventure.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private final JLabel avatar;
    private final JLabel room;
    private final JLabel enemy;
    private final JLabel gold;
    private int roomCounter = 4;
    private int enemyCounter = 2;
    private int goldCounter = 0;
    private final ImageIcon avatarIcon = new ImageIcon("src/main/resources/Arecki.jpg");
    private final Border avatarBorder = BorderFactory.createLineBorder(Color.DARK_GRAY,2);

    public ProfilePanel() {
        avatar = new JLabel();
        avatar.setIcon(avatarIcon);
        avatar.setBorder(avatarBorder);
        avatar.setPreferredSize(new Dimension(190,216));

        room = new JLabel();
        room.setText("Discovered rooms: " + roomCounter);

        enemy = new JLabel();
        enemy.setText("Conquered enemies: " + enemyCounter);

        gold = new JLabel();
        gold.setText("Collected gold: " + goldCounter);

        initializeComponent();
    }

    private void initializeComponent() {
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        this.setPreferredSize(new Dimension(250,600));
        this.setBorder(BorderFactory.createTitledBorder("Hero"));
        this.add(avatar);
        this.add(room);
        this.add(enemy);
        this.add(gold);
    }
}
