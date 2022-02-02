package bigAdventure.rendering;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

public class RenderCanvas extends Canvas implements RenderArea {

    private Image backBuffer;

    public RenderCanvas() {
        super();
        this.setVisible(true);
        this.setIgnoreRepaint(true);
        this.setBackground(new Color(255,255,255,0));
    }

    public void initialize(){
        backBuffer = createImage(getWidth(), getHeight());
    }

    private void copyBackBufferToScreen(){
        final var backBufferGraphics = (Graphics2D) this.backBuffer.getGraphics();
        this.getGraphics().drawImage(this.backBuffer, 0 ,0, null);
        backBufferGraphics.dispose();
    }

    @Override
    public void renderNextFrame(java.util.List<Renderable> renderables) {
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
