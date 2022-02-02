package bigAdventure.rendering;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class RenderingThread extends Thread {

    private final StopWatch stopWatch;
    private final List<Renderable> renderablesToDraw;
    private final List<Renderable> renderablesToDrawBuffer;
    private final List<Renderable> renderablesToRemoveBuffer;
    private final int computerThreads;
    private final RenderArea renderArea;
    private long frameTime;
    private ExecutorService transformThreads;
    private boolean isRendering;
    private long lastFrameTime;

    public RenderingThread(RenderArea target, long frameTime) {
        super("RenderThread-1");
        this.computerThreads = Runtime.getRuntime().availableProcessors();
        this.renderArea = target;
        this.stopWatch = new StopWatch();
        this.renderablesToDraw = new ArrayList<>(20);
        this.renderablesToDrawBuffer = new ArrayList<>(20);
        this.renderablesToRemoveBuffer = new ArrayList<>(2);
        this.frameTime = frameTime;
        this.isRendering = false;
    }

    public synchronized void addRenderable(Renderable Renderable){
        this.renderablesToDrawBuffer.add(Renderable);
    }

    public synchronized void removeRenderable(Renderable Renderable){
        this.renderablesToRemoveBuffer.add(Renderable);
    }

    public synchronized boolean isRendering() {
        return isRendering;
    }

    public synchronized void setRendering(boolean rendering) {
        isRendering = rendering;
    }

    public synchronized long getLastFrameTime() {
        return lastFrameTime;
    }

    public synchronized void setFrameTime(long value){
        this.frameTime = value;
    }

    @Override
    public void run() {
        stopWatch.start();
        while (true){

			if (!renderablesToDrawBuffer.isEmpty()){
                    stopWatch.suspend();
                    renderablesToDraw.addAll(renderablesToDrawBuffer);
                    renderablesToDrawBuffer.clear();
                    stopWatch.resume();
			}
            if (!renderablesToRemoveBuffer.isEmpty()){
                stopWatch.suspend();
                renderablesToDraw.removeAll(renderablesToRemoveBuffer);
                renderablesToRemoveBuffer.clear();
                stopWatch.resume();
            }

            if (!isRendering()) {
                try {
                    synchronized (this){
                        stopWatch.stop();
                        stopWatch.reset();
                        wait();
                        stopWatch.start();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            var deltaTime = stopWatch.getTime();
            if (deltaTime > frameTime){
                lastFrameTime = deltaTime;
                stopWatch.reset();
                stopWatch.start();
                transformFigures(deltaTime / 1000f);
                renderArea.renderNextFrame(renderablesToDraw);
            }
        }
    }

    private void transformFigures(final double deltaTime) {
        transformThreads = Executors.newFixedThreadPool(computerThreads);

        for (var renderable : renderablesToDraw){
            var action = renderable.getActionInNextFrame();
            if (action != null)
                transformThreads.submit(() -> action.execute(renderable, deltaTime));
        }
        transformThreads.shutdown();
        try {
            transformThreads.awaitTermination(frameTime / 1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
