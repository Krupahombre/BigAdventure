package bigAdventure.rendering.animation;

import java.util.HashMap;
import java.util.Map;

public class AnimationsMap<A extends AnimationSequence> extends HashMap<String, A> {

    public AnimationsMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public AnimationsMap(int initialCapacity) {
        super(initialCapacity);
    }

    public AnimationsMap() {
    }

    public AnimationsMap(Map<? extends String, A> m) {
        super(m);
    }
}
