package bigAdventure.gui;

import bigAdventure.rendering.RectangleRenderable;
import bigAdventure.rendering.RenderCanvas;
import bigAdventure.rendering.Renderable;
import bigAdventure.rendering.RenderingThread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.IOException;

public class ActionPanel extends JPanel {

    private final RenderingThread renderingThread;
    private final RenderCanvas renderCanvas;
    private final Renderable blue;
    private final Renderable red;

    public ActionPanel() {
        this.setBackground(Color.WHITE);
        this.setIgnoreRepaint(false);
        this.setDoubleBuffered(false);
        this.setFocusable(true);
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder("Action"));
        this.setLayout(new BorderLayout(40,40));

        this.renderCanvas = new RenderCanvas();

        renderingThread = new RenderingThread(renderCanvas, 16);

        blue = new RectangleRenderable(new Point2D.Double(100,100),200,200);
        red = new RectangleRenderable(new Point2D.Double(50,50),100,200);

        blue.setColor(Color.BLUE);
        //red.setColor(Color.RED);
        try {
            red.setImage(ImageIO.read(this.getClass().getResourceAsStream("/sprites/black_knight/tile000.png")));
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
