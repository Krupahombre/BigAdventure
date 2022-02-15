package bigAdventure.gui;

import bigAdventure.global.GameResources;
import bigAdventure.rendering.RenderCanvas;
import bigAdventure.rendering.RenderingThread;
import bigAdventure.rendering.animation.AnimatingType;
import bigAdventure.rendering.animation.LockedAnimationMap;
import bigAdventure.rendering.sprite.AnimatedSprite;
import bigAdventure.rendering.sprite.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class ActionPanel extends JPanel {

    private final RenderingThread renderingThread;
    private final RenderCanvas renderCanvas;
    private final JLabel fpsLabel;
    private final Sprite blue;
    private final AnimatedSprite red;

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

        renderingThread.addRenderable(red);
        renderingThread.addRenderable(blue);

        SwingUtilities.invokeLater(() -> {
            renderCanvas.initialize();
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    renderCanvas.initialize();
                }
            });
            renderingThread.setRendering(true);
            renderingThread.start();
        });


        fpsLabel = new JLabel();
        Timer fpsLabelTimer = new Timer( 100,
                (e) -> fpsLabel.setText(String.format("FPS : %.2f", 1f/(renderingThread.getLastFrameTime() / 1000f)) ) );
        fpsLabelTimer.start();
        registerExperimentalKeyboardMovement();

        this.setVisible(true);
        this.add(renderCanvas, BorderLayout.CENTER);
        this.add(fpsLabel, BorderLayout.NORTH);
    }

    public void registerExperimentalKeyboardMovement(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                requestFocusInWindow();
            }
        });
        renderCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                requestFocusInWindow();
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_D -> moveRed(1000, 0);
                    case KeyEvent.VK_A -> moveRed(-1000, 0);
                    case KeyEvent.VK_S -> moveRed(0, 1000);
                    case KeyEvent.VK_W -> moveRed(0, -1000);
                }
            }
        });
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
