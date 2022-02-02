package bigAdventure.rendering;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public abstract class AbstractRenderable implements Renderable {

    private final Area shapeArea;
    private AffineTransform currentTransform;
    private NextFrameAction nextFrameAction;
    private Image image;
    private Color color;


    public AbstractRenderable(Shape shape) {
        this.shapeArea = new Area(shape);
        this.currentTransform = new AffineTransform();
    }


    @Override
    public Shape getShape() {
        return shapeArea;
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public AffineTransform getCurrentAffineTransform() {
        return currentTransform;
    }

    @Override
    public void setCurrentAffineTransform(AffineTransform transform) {
        this.currentTransform = transform;
    }

    @Override
    public void concatAffineTransform(AffineTransform transform) {
        this.currentTransform.concatenate(transform);
    }

    @Override
    public void setActionInNextFrame(NextFrameAction function) {
        this.nextFrameAction = function;
    }

    @Override
    public NextFrameAction getActionInNextFrame() {
        return this.nextFrameAction;
    }

}
