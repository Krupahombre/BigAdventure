package bigAdventure.rendering;

import java.awt.*;
import java.util.List;

public interface RenderArea {
    void renderNextFrame(List<Renderable> renderables);
    void renderNextFrame(Renderable renderable);
    void renderColor(Color color);
    Graphics2D getScreen();
}
