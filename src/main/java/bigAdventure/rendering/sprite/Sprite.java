package bigAdventure.rendering.sprite;

import bigAdventure.exceptions.InvalidAnimationException;
import bigAdventure.rendering.NextFrameAction;
import bigAdventure.rendering.Renderable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;

public class Sprite implements Renderable {

    private Map<String, List<Image>> availableAnimations;
    private List<Image> activeAnimation;
    private Image activeAnimationFrame;
    private AffineTransform currentAffineTransform = new AffineTransform();
    private NextFrameAction nextFrameAction;
    private Iterator<Image> currentAnimationFrameIterator;

    public Sprite() {
        this.availableAnimations = new HashMap<>();
        this.activeAnimation = new ArrayList<>();
        this.currentAnimationFrameIterator = this.activeAnimation.listIterator();
    }

    public Sprite(Map<String, List<Image>> availableAnimations, String activeAnimation) {
        this.availableAnimations = availableAnimations;
        this.activeAnimation = availableAnimations.get(activeAnimation);
        this.currentAnimationFrameIterator = this.activeAnimation.listIterator(0);

        if (this.activeAnimation == null)
            throw new InvalidAnimationException(
                    String.format("Specified animation '%s' does not exist", activeAnimation));
    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public Image getImage() {
        return activeAnimationFrame;
    }

    @Override
    public void setImage(Image image) {
        this.activeAnimationFrame = image;
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
        return (renderable, deltaTime) -> {
            if(this.nextFrameAction != null)
                this.nextFrameAction.execute(renderable, deltaTime);
            this.animate();
        };
    }

    public void setActiveAnimation(String name){
        var animation = this.availableAnimations.get(name);
        if (animation == null)
            throw new InvalidAnimationException(
                    String.format("Specified animation '%s' does not exist", activeAnimation));
        this.activeAnimation = animation;
        this.currentAnimationFrameIterator = activeAnimation.listIterator(0);
    }

    private void animate(){
        if (this.activeAnimation.isEmpty()) return;
        if (this.activeAnimation.size() == 1){
            this.activeAnimationFrame = this.activeAnimation.get(0);
            return;
        }
        if (!this.currentAnimationFrameIterator.hasNext()){
            this.currentAnimationFrameIterator = this.activeAnimation.listIterator(0);
        }
        this.activeAnimationFrame = this.currentAnimationFrameIterator.next();
    }

}
