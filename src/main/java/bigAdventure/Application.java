package bigAdventure;

import bigAdventure.gui.MainFrame;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try{
                new MainFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
