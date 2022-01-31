package bigAdventure.gui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private final JButton fightButton;
    private final JButton defenseButton;

    public ControlPanel() {
        fightButton = new JButton("Fight");
        defenseButton = new JButton("Defend yourself");
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
