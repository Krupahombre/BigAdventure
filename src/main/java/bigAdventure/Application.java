package bigAdventure;

import bigAdventure.exceptions.UnrecoverableGameResourceLoadingException;
import bigAdventure.global.GameResources;
import bigAdventure.gui.MainFrame;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");
        System.setProperty("sun.java2d.opengl", "true");


        try {
            var gameResources = new GameResources();
        } catch (UnrecoverableGameResourceLoadingException e) {
            e.printStackTrace();
            JOptionPane.showConfirmDialog(null,
                    e.getMessage(),
                    "Critical error!",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        SwingUtilities.invokeLater(() -> {
            try{
                new MainFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
