package bigAdventure.rendering;

/**
 * This functional interface represents a task that will be offloaded to the {@link RenderingThread}
 * and will be executed when {@link RenderingThread} decides to render next frame. Note that it does not mean
 * that {@link RenderingThread} will do all the work by itself - it further assigns it to other threads
 * using {@link java.util.concurrent.ThreadPoolExecutor}.
 */
@FunctionalInterface
public interface NextFrameAction {
    /**
     * Action that will happen before next frame will be rendered .
     * @param thisRenderable - object implementing {@link Renderable} that this action was assigned to.
     */
    void execute(Renderable thisRenderable);
}
