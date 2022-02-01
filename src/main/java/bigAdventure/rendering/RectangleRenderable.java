package bigAdventure.rendering;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class RectangleRenderable extends AbstractRenderable{

    public RectangleRenderable(Point2D initialPosition, double width, double height) {
        super(new Rectangle2D.Double(initialPosition.getX(), initialPosition.getY(), width, height));
    }

    public RectangleRenderable(double width, double height) {
        this(new Point2D.Double(0,0), width, height);
    }
}
