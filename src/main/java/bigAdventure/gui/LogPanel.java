package bigAdventure.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LogPanel extends JPanel {
    private final JTextArea logArea;
    private final JButton clearLogButton;
    private final Border logBorder = BorderFactory.createLineBorder(Color.DARK_GRAY,2);
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss");

    public LogPanel() {
        clearLogButton = new JButton("Clear log");
        clearLogButton.setFocusable(false);
        clearLogButton.addActionListener(this::clearLog);

        logArea = new JTextArea();
        logArea.setPreferredSize(new Dimension(220,400));
        logArea.setBorder(logBorder);
        logArea.setEditable(false);

        initializeComponent();
    }

    private void initializeComponent() {
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        this.setPreferredSize(new Dimension(250,600));
        this.setBorder(BorderFactory.createTitledBorder("Log"));
        this.add(logArea);
        this.add(clearLogButton);
    }

    public void printLog(String text) {
        logArea.append(LocalTime.now().format(dtf) + " " + text + "\n");
    }
    private void clearLog(ActionEvent a){
        logArea.setText("");
    }
}
