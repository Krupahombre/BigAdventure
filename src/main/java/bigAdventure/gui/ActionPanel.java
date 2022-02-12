package bigAdventure.gui;

import bigAdventure.global.GameResources;
import bigAdventure.rendering.RenderCanvas;
import bigAdventure.rendering.RenderingThread;
import bigAdventure.rendering.animation.AnimatingType;
import bigAdventure.rendering.animation.AnimationSequence;
import bigAdventure.rendering.animation.AnimationsMap;
import bigAdventure.rendering.animation.LockedAnimationMap;
import bigAdventure.rendering.sprite.AnimatedSprite;
import bigAdventure.rendering.sprite.Sprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class ActionPanel extends JPanel {

    private final RenderingThread renderingThread;
    private final RenderCanvas renderCanvas;
    private Sprite blue;
    private AnimatedSprite red;

    public ActionPanel() {
        this.setBackground(Color.WHITE);
        this.setIgnoreRepaint(false);
        this.setDoubleBuffered(false);
        this.setFocusable(true);
        this.setOpaque(false);
        this.setBorder(BorderFactory.createTitledBorder("Action"));
        this.setLayout(new BorderLayout(40,40));

        this.renderCanvas = new RenderCanvas();
        renderingThread = new RenderingThread(renderCanvas, 16);

        blue = new Sprite(GameResources.getInstance()
                .getStaticResourceAsImage("black_knight_static.png").get());

        var loadedAnimationMap = GameResources.getInstance()
                .getAnimationMap("green_knight");


        red = new AnimatedSprite(LockedAnimationMap.fromNonLocked(loadedAnimationMap, 10));
        red.setActiveAnimationSequence("stand", AnimatingType.LOOPED);
        red.setIsAnimating(true);


        renderingThread.addRenderable(blue);
        renderingThread.addRenderable(red);

        renderingThread.setRendering(true);
        SwingUtilities.invokeLater(() -> {
            //renderCanvas.setSize(new Dimension(this.getWidth(), this.getHeight()));
            renderCanvas.initialize();
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    renderCanvas.initialize();
                }
            });
            renderingThread.start();
        });
        this.setVisible(true);
        this.add(renderCanvas, BorderLayout.CENTER);
    }

    public void moveBlue(int offsetX, int offsetY){
        blue.setActionInNextFrame((thisRenderable, deltaTime) -> {
            thisRenderable.concatAffineTransform(AffineTransform.getTranslateInstance(offsetX * deltaTime, offsetY * deltaTime));
            thisRenderable.setActionInNextFrame(null);
        });
    }

    public void moveRed(int offsetX, int offsetY){
        red.setActionInNextFrame((thisRenderable, deltaTime) -> {
            thisRenderable.concatAffineTransform(AffineTransform.getTranslateInstance(offsetX * deltaTime, offsetY * deltaTime));
            thisRenderable.setActionInNextFrame(null);
        });
    }
}
