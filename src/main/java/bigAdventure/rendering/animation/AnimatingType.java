package bigAdventure.rendering.animation;

/**
 * Animating a can be done in three ways:
 * Once - Whole animation sequence will play, then animation will be set to its first frame and stopped.
 * Looped - Animation will be looped forever
 * Forwarded - Similar to 'Once' but at the end instead of reverting animation to its first frame it will stay at
 * its last frame.
 */
public enum AnimatingType {
    /**
     * asdsa
     */
    ONCE,
    LOOPED,
    FORWARDED
}
