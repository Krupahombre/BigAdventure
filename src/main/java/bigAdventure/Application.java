package bigAdventure;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Application {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.enableGLDebugOutput(true, System.out);
        configuration.setIdleFPS(60);
        configuration.useVsync(true);
        configuration.setWindowedMode(800,800);
        configuration.setTitle("Big Adventure");
        configuration.setResizable(true);

        new Lwjgl3Application(new Boot(), configuration);
    }
}
