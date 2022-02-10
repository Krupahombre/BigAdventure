package bigAdventure;

import bigAdventure.gui.MainFrame;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");
        SwingUtilities.invokeLater(() -> {
            try{
                new MainFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
