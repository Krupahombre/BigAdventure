package bigAdventure.rendering.sprite;

import bigAdventure.exceptions.InvalidAnimationException;
import bigAdventure.rendering.NextFrameAction;
import bigAdventure.rendering.animation.*;

import java.awt.*;
import java.util.Iterator;

public class AnimatedSprite extends Sprite{
    private final LockedAnimationMap availableAnimations;
    private LockedAnimationSequence activeAnimationSequence;
    private Iterator<Image> currentAnimationFrameIterator;
    private float accumulatedTime;
    private boolean isAnimating = false;
    private AnimatingType currentAnimatingType = AnimatingType.ONCE;

    /**
     * Creates sprite with only one animation sequence. Note: animation will not start automatically
     * @param singleSequence Single animation sequence this sprite will have
     * @param sequenceName Name of provided sequence
     */
    public AnimatedSprite(LockedAnimationSequence singleSequence, String sequenceName) {
        this.availableAnimations = new LockedAnimationMap();
        this.availableAnimations.put(sequenceName, singleSequence);
    }

    /**
     * Creates sprite with map of animations that will be available for this sprite.
     * Note: animation will not start automatically.
     * @param availableAnimations Map of animations that will be available for this sprite
     */
    public AnimatedSprite(LockedAnimationMap availableAnimations) {
        this.availableAnimations = availableAnimations;
    }

    @Override
    public NextFrameAction getActionInNextFrame() {
        return (renderable, deltaTime) -> {
            var originalAction = super.getActionInNextFrame();
            if(originalAction != null)
                originalAction.execute(renderable, deltaTime);
            this.accumulatedTime += deltaTime * 1000f;
            if (accumulatedTime >= activeAnimationSequence.getTargetDeltaTime()){
                this.animate();
                this.accumulatedTime = 0;
            }
        };
    }

    public void setActiveAnimationSequence(String name, AnimatingType animatingType){
        var animation = this.availableAnimations.get(name);
        if (animation == null)
            throw new InvalidAnimationException(
                    String.format("Specified animation '%s' does not exist", activeAnimationSequence));
        this.activeAnimationSequence = animation;
        this.currentAnimationFrameIterator = activeAnimationSequence.iterator();
        this.currentAnimatingType = animatingType;
    }

    public void setIsAnimating(boolean value){
        this.isAnimating = value;
    }

    public boolean isAnimating(boolean value){
        return this.isAnimating;
    }

    public AnimatingType getCurrentAnimatingType() {
        return currentAnimatingType;
    }

    private void animate(){
        if (!isAnimating || this.activeAnimationSequence.isEmpty()) return;
        if (this.activeAnimationSequence.size() == 1){
            this.setImage(this.activeAnimationSequence.peek());
            return;
        }

        if (currentAnimatingType == AnimatingType.LOOPED){
            if (!this.currentAnimationFrameIterator.hasNext()){
                this.currentAnimationFrameIterator = this.activeAnimationSequence.iterator();
            }
            this.setImage(this.currentAnimationFrameIterator.next());
        } else if (currentAnimatingType == AnimatingType.ONCE){
            if (!this.currentAnimationFrameIterator.hasNext()) {
                this.currentAnimationFrameIterator = this.activeAnimationSequence.iterator();
                this.setImage(this.currentAnimationFrameIterator.next());
                isAnimating = false;
                return;
            }
            this.setImage(this.currentAnimationFrameIterator.next());
        } else if (currentAnimatingType == AnimatingType.FORWARDED){
            if (!this.currentAnimationFrameIterator.hasNext()) {
                isAnimating = false;
                return;
            }
            this.setImage(this.currentAnimationFrameIterator.next());
        }


    }
}
