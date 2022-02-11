package bigAdventure.rendering.animation;

import java.util.HashMap;
import java.util.Map;

public class AnimationsMap extends HashMap<String, AnimationSequence> {

    public AnimationsMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public AnimationsMap(int initialCapacity) {
        super(initialCapacity);
    }

    public AnimationsMap() {
    }

    public AnimationsMap(Map<? extends String, ? extends AnimationSequence> m) {
        super(m);
    }
}
