package bigAdventure.rendering.animation;

import com.sun.istack.NotNull;

import java.util.HashMap;
import java.util.Map;

public class LockedAnimationMap extends AnimationsMap<LockedAnimationSequence> {

    public LockedAnimationMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public LockedAnimationMap(int initialCapacity) {
        super(initialCapacity);
    }

    public LockedAnimationMap() {
    }

    public LockedAnimationMap(Map<? extends String, LockedAnimationSequence> m) {
        super(m);
    }

    @NotNull
    public static LockedAnimationMap fromNonLocked(@NotNull AnimationsMap<AnimationSequence> animationsMap, float targetFpsForAll) {
        Map<String, LockedAnimationSequence> converted = new HashMap<>(animationsMap.size());
        for (var key : animationsMap.keySet())
            converted.put(key, new LockedAnimationSequence(animationsMap.get(key), targetFpsForAll));
        return new LockedAnimationMap(converted);
    }
}
