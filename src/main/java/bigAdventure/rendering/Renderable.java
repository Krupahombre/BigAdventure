package bigAdventure.rendering;

import java.awt.*;
import java.awt.geom.AffineTransform;

public interface Renderable {
    Shape getShape();
    Image getImage();
    void setImage(Image image);
    Color getColor();
    void setColor(Color color);
    AffineTransform getCurrentAffineTransform();
    void setCurrentAffineTransform(AffineTransform transform);
    void concatAffineTransform(AffineTransform transform);
    void setActionInNextFrame(NextFrameAction function);
    NextFrameAction getActionInNextFrame();
}
