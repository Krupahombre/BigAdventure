package bigAdventure.gui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private final JButton fightButton;
    private final JButton defenseButton;

    public ControlPanel(ActionPanel actionPanel) {
        fightButton = new JButton("Fight");
        fightButton.setFocusable(false);
        fightButton.addActionListener( (e) -> actionPanel.moveBlue(10,1));

        defenseButton = new JButton("Defend yourself");
        defenseButton.setFocusable(false);
        defenseButton.addActionListener( (e) -> actionPanel.moveRed(10,0));

        initializeComponent();
    }

    private void initializeComponent(){
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 20));
        this.setBorder(BorderFactory.createTitledBorder("Controls"));
        this.add(fightButton);
        this.add(defenseButton);
    }
}
