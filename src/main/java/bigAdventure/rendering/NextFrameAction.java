package bigAdventure.rendering;

@FunctionalInterface
public interface NextFrameAction {
    void execute(Renderable thisRenderable);
}
