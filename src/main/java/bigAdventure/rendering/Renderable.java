package bigAdventure.rendering;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Interface that represents object that is composition of {@link Image} and {@link Shape}.
 * Class implementing this interface is suitable to be rendered on objects that implement
 * {@link RenderArea} and can be used by {@link RenderingThread}.
 */
public interface Renderable {
    Shape getShape();
    Image getImage();
    void setImage(Image image);
    Color getColor();
    void setColor(Color color);
    AffineTransform getCurrentAffineTransform();
    void setCurrentAffineTransform(AffineTransform transform);
    void concatAffineTransform(AffineTransform transform);

    /**
     * Sets {@link NextFrameAction}.
     * @param function - {@link NextFrameAction}.
     */
    void setActionInNextFrame(NextFrameAction function);
    NextFrameAction getActionInNextFrame();
}
