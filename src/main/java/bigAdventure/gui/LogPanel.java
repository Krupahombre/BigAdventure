package bigAdventure.gui;

import javax.swing.*;
import java.awt.*;

public class LogPanel extends JPanel {
    public LogPanel() {
        initializeComponent();
    }

    private void initializeComponent() {
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        this.setPreferredSize(new Dimension(250,600));
        this.setBorder(BorderFactory.createTitledBorder("Log"));
    }
}
