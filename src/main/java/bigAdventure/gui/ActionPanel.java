package bigAdventure.gui;

import bigAdventure.rendering.RectangleRenderable;
import bigAdventure.rendering.RenderArea;
import bigAdventure.rendering.Renderable;
import bigAdventure.rendering.RenderingThread;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;

public class ActionPanel extends JPanel implements RenderArea {

    private final RenderingThread renderingThread;
    private Image backBuffer;

    public ActionPanel() {
        this.setSize(800,600);
        this.setBackground(Color.WHITE);
        this.setIgnoreRepaint(true);
        this.setDoubleBuffered(false);
        this.setFocusable(true);

        renderingThread = new RenderingThread(this, 17);

        var renderable = new RectangleRenderable(new Point2D.Double(100,100),200,200);
        renderable.setColor(Color.BLUE);
        renderable.setActionInNextFrame(thisRenderable -> {
            thisRenderable.concatAffineTransform(AffineTransform.getTranslateInstance(1,1));
        });

        renderingThread.addRenderable(renderable);

        renderingThread.setRendering(true);
        SwingUtilities.invokeLater(() -> {
            backBuffer = createImage(getWidth(), getHeight());
            renderingThread.start();
        });
    }

    private void clearAndCopyBackBufferToScreen(){
        final var backBufferGraphics = (Graphics2D) this.backBuffer.getGraphics();
        backBufferGraphics.clearRect(0,0, this.getWidth(), this.getHeight());
        this.getGraphics().drawImage(this.backBuffer, 0 ,0, null);
        backBufferGraphics.dispose();
    }

    @Override
    public void renderNextFrame(List<Renderable> renderables) {

        final var screen = (Graphics2D) this.backBuffer.getGraphics();

        for (var renderable : renderables){
            final var originalTransform = screen.getTransform();
            final var originalColor = screen.getColor();

            screen.setTransform(renderable.getCurrentAffineTransform());
            screen.setColor(renderable.getColor());
            screen.setStroke(new BasicStroke(1));
            screen.fill(renderable.getShape());
            screen.setColor(Color.BLACK);
            screen.draw(renderable.getShape());

            screen.setTransform(originalTransform);
            screen.setColor(originalColor);
            screen.drawImage(renderable.getImage(), originalTransform, null);
        }
        screen.dispose();
        clearAndCopyBackBufferToScreen();
    }

    @Override
    public void renderNextFrame(Renderable renderable) {

    }

    @Override
    public void renderColor(Color color) {

    }

    @Override
    public Graphics2D getScreen() {
        return (Graphics2D) this.getGraphics();
    }
}
