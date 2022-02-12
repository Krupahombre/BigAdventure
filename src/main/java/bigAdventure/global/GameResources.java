package bigAdventure.global;

import bigAdventure.exceptions.GameResourceLoadingException;
import bigAdventure.exceptions.UnrecoverableGameResourceLoadingException;
import bigAdventure.rendering.animation.AnimationSequence;
import bigAdventure.rendering.animation.AnimationsMap;
import com.sun.istack.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameResources {
    private static GameResources instance;
    private Map<String, AnimationsMap<AnimationSequence>> animations;
    private Map<String, File> staticResources;

    public GameResources() throws UnrecoverableGameResourceLoadingException {
        //TODO: Not all exceptions should be unrecoverable, but for now let's leave it that way for sake of simplicity
        try {
            loadAnimationSequences();
            loadStaticResources();
        } catch (GameResourceLoadingException e){
            throw new UnrecoverableGameResourceLoadingException();
        }
        instance = this;
    }

    public static GameResources getInstance() {
        return instance;
    }

    private void loadAnimationSequences() throws GameResourceLoadingException {
        animations = new HashMap<>();
        File animsRoot = new File(GameResources.class.getResource("/sprites/animations").getPath());

        try {
            for (var animFolder : animsRoot.listFiles()){
                var animMap = new AnimationsMap();
                for (var animSequenceFolder : animFolder.listFiles()){

                    var animSequence = new AnimationSequence();
                    for (var animFrame : animSequenceFolder.listFiles())
                        animSequence.add(ImageIO.read(animFrame));
                    animMap.put(animSequenceFolder.getName(), animSequence);
                }
                animations.put(animFolder.getName(), animMap);
            }
        } catch (Exception e){
            throw new GameResourceLoadingException(e);
        }
    }

    private void loadStaticResources() throws GameResourceLoadingException {
        staticResources = new HashMap<>();
        var foundStaticResources = new File(GameResources.class.getResource("/static").getPath())
                .listFiles();

        try {
            for(var file : foundStaticResources)
                staticResources.put(file.getName(), file);
        } catch (Exception e){
            throw new GameResourceLoadingException(e);
        }

    }

    public File getStaticResource(@NotNull String fileName){
        return this.staticResources.get(fileName);
    }

    public Optional<BufferedImage> getStaticResourceAsImage(@NotNull String fileName){
        try {
            return Optional.ofNullable(ImageIO.read(this.staticResources.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public AnimationsMap<AnimationSequence> getAnimationMap(@NotNull String name){
        return this.animations.get(name);
    }

    public AnimationSequence getAnimationSequence(@NotNull String animationMapName, @NotNull String animationSequenceName){
        return this.animations.get(animationMapName).get(animationSequenceName);
    }
}
