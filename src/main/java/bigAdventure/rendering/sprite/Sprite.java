package bigAdventure.rendering.sprite;

import bigAdventure.exceptions.InvalidAnimationException;
import bigAdventure.rendering.NextFrameAction;
import bigAdventure.rendering.Renderable;
import bigAdventure.rendering.animation.AnimationSequence;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;

public class Sprite implements Renderable {
    private Image image;
    private AffineTransform currentAffineTransform;
    private NextFrameAction nextFrameAction;


    public Sprite() {
        this.currentAffineTransform = new AffineTransform();
    }

    public Sprite(Image image) {
        this();
        this.image = image;
    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void setColor(Color color) {

    }

    @Override
    public AffineTransform getCurrentAffineTransform() {
        return this.currentAffineTransform;
    }

    @Override
    public void setCurrentAffineTransform(AffineTransform transform) {
        this.currentAffineTransform = transform;
    }

    @Override
    public void concatAffineTransform(AffineTransform transform) {
        this.currentAffineTransform.concatenate(transform);
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
