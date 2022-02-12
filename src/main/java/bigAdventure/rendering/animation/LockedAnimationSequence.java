package bigAdventure.rendering.animation;

import java.awt.*;
import java.util.Collection;

public class LockedAnimationSequence extends AnimationSequence{
    private final float targetDeltaTime;

    public LockedAnimationSequence(float targetFps) {
        this.targetDeltaTime = (1f/targetFps)*1000f;
    }

    public LockedAnimationSequence(Collection<? extends Image> c, float targetFps) {
        super(c);
        this.targetDeltaTime = (1f/targetFps)*1000f;
    }

    public LockedAnimationSequence(AnimationSequence c, float targetFps) {
        super(c);
        this.targetDeltaTime = (1f/targetFps)*1000f;
    }

    public float getTargetDeltaTime() {
        return targetDeltaTime;
    }
}
