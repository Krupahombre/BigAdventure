package bigAdventure.rendering.animation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class AnimationSequence extends ArrayList<Image> {
    private final float targetDeltaTime;

    public AnimationSequence(float targetFps) {
        this.targetDeltaTime = 1/targetFps*1000;
    }

    public AnimationSequence(int initialCapacity, float targetFps) {
        super(initialCapacity);
        this.targetDeltaTime = (1f/targetFps)*1000f;
    }

    public AnimationSequence(Collection<? extends Image> c, float targetFps) {
        super(c);
        this.targetDeltaTime = (1f/targetFps)*1000f;
    }

    public float getTargetDeltaTime() {
        return targetDeltaTime;
    }
}
