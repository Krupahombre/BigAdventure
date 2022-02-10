package bigAdventure.gui;

import bigAdventure.rendering.RectangleRenderable;
import bigAdventure.rendering.RenderCanvas;
import bigAdventure.rendering.Renderable;
import bigAdventure.rendering.RenderingThread;
import bigAdventure.rendering.sprite.Sprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActionPanel extends JPanel {

    private final RenderingThread renderingThread;
    private final RenderCanvas renderCanvas;
    private final Renderable blue;
    private Renderable red;

    public ActionPanel() {
        this.setBackground(Color.WHITE);
        this.setIgnoreRepaint(false);
        this.setDoubleBuffered(false);
        this.setFocusable(true);
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder("Action"));
        this.setLayout(new BorderLayout(40,40));

        this.renderCanvas = new RenderCanvas();

        renderingThread = new RenderingThread(renderCanvas, 150);

        blue = new RectangleRenderable(new Point2D.Double(100,100),200,200);


        blue.setColor(Color.BLUE);
        //red.setColor(Color.RED);
        try {
            List<Image> tiles = new ArrayList<>(50);
            File tilesDir = new File(getClass().getResource("/sprites/green_knight/tile000.png").getPath());
            var tileFiles = tilesDir.getParentFile().listFiles();
            for (var tileFile : tileFiles){
                tiles.add(ImageIO.read(tileFile));
            }
            red = new Sprite(Map.of("test_animation", tiles), "test_animation");

        } catch (IOException e){
            e.printStackTrace();
        }

        renderingThread.addRenderable(blue);
        renderingThread.addRenderable(red);

        renderingThread.setRendering(true);
        SwingUtilities.invokeLater(() -> {
            //renderCanvas.setSize(new Dimension(this.getWidth(), this.getHeight()));
            renderCanvas.initialize();
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    renderCanvas.initialize();
                }
            });
            renderingThread.start();
        });
        this.setVisible(true);
        this.add(renderCanvas, BorderLayout.CENTER);
    }

    public void moveBlue(int offsetX, int offsetY){
        blue.setActionInNextFrame((thisRenderable, deltaTime) -> {
            thisRenderable.concatAffineTransform(AffineTransform.getTranslateInstance(offsetX * deltaTime, offsetY * deltaTime));
            thisRenderable.setActionInNextFrame(null);
        });
    }

    public void moveRed(int offsetX, int offsetY){
        red.setActionInNextFrame((thisRenderable, deltaTime) -> {
            thisRenderable.concatAffineTransform(AffineTransform.getTranslateInstance(offsetX * deltaTime, offsetY * deltaTime));
            thisRenderable.setActionInNextFrame(null);
        });
    }
}
