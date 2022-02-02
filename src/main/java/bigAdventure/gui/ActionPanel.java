package bigAdventure.gui;

import bigAdventure.rendering.RectangleRenderable;
import bigAdventure.rendering.RenderArea;
import bigAdventure.rendering.Renderable;
import bigAdventure.rendering.RenderingThread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class ActionPanel extends JPanel implements RenderArea {

    private final RenderingThread renderingThread;
    private Image backBuffer;
    private final Renderable blue;
    private final Renderable red;

    public ActionPanel() {
        this.setSize(800,600);
        this.setBackground(Color.WHITE);
        this.setIgnoreRepaint(true);
        this.setDoubleBuffered(false);
        this.setFocusable(true);

        renderingThread = new RenderingThread(this, 16);

        blue = new RectangleRenderable(new Point2D.Double(100,100),200,200);
        red = new RectangleRenderable(new Point2D.Double(50,50),100,200);

        blue.setColor(Color.BLUE);
        //red.setColor(Color.RED);
        try {
            red.setImage(ImageIO.read(this.getClass().getResourceAsStream("/AdamantKnight.png")));
        } catch (IOException e){
            e.printStackTrace();
        }



        renderingThread.addRenderable(blue);
        renderingThread.addRenderable(red);

        renderingThread.setRendering(true);
        SwingUtilities.invokeLater(() -> {
            backBuffer = createImage(getWidth(), getHeight());
            renderingThread.start();
        });
    }

    public void moveBlue(int offsetX, int offsetY){
        blue.setActionInNextFrame(thisRenderable -> {
            thisRenderable.concatAffineTransform(AffineTransform.getTranslateInstance(offsetX, offsetY));
            thisRenderable.setActionInNextFrame(null);
        });
    }

    public void moveRed(int offsetX, int offsetY){
        red.setActionInNextFrame(thisRenderable -> {
            thisRenderable.concatAffineTransform(AffineTransform.getTranslateInstance(offsetX, offsetY));
            thisRenderable.setActionInNextFrame(null);
        });
    }

    private void copyBackBufferToScreen(){
        final var backBufferGraphics = (Graphics2D) this.backBuffer.getGraphics();
        this.getGraphics().drawImage(this.backBuffer, 0 ,0, null);
        backBufferGraphics.dispose();
    }

    @Override
    public void renderNextFrame(List<Renderable> renderables) {
        final var screen = (Graphics2D) this.backBuffer.getGraphics();
        screen.clearRect(0,0, this.getWidth(), this.getHeight());

        for (var renderable : renderables){
            final var originalTransform = screen.getTransform();
            final var originalColor = screen.getColor();

            screen.setTransform(renderable.getCurrentAffineTransform());
            screen.setColor(renderable.getColor());
            screen.setStroke(new BasicStroke(1));
            screen.fill(renderable.getShape());
            screen.setColor(Color.BLACK);
            screen.draw(renderable.getShape());

            if (renderable.getImage() != null){
                screen.setClip(renderable.getShape());
                screen.drawImage(renderable.getImage(), new AffineTransform(), null);
            }

            //screen.setTransform(originalTransform);
            //screen.setColor(originalColor);
            screen.drawImage(renderable.getImage(), originalTransform, null);
        }
        screen.dispose();
        copyBackBufferToScreen();
    }

    @Override
    public void renderNextFrame(Renderable renderable) {
        this.renderNextFrame(List.of(renderable));
    }

    @Override
    public void renderColor(Color color) {
        final var screen = (Graphics2D) this.backBuffer.getGraphics();
        screen.setColor(color);
        screen.fillRect(0,0, this.getWidth(), this.getHeight());
    }

    @Override
    public Graphics2D getScreen() {
        return (Graphics2D) this.getGraphics();
    }
}
