package bigAdventure.rendering.animation;

import java.awt.*;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AnimationSequence extends ConcurrentLinkedQueue<Image> {

    public AnimationSequence() {
    }

    public AnimationSequence(Collection<? extends Image> c) {
        super(c);
    }
}
