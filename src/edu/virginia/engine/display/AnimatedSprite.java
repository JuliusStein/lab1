package edu.virginia.engine.display;

import java.awt.*;
import java.util.ArrayList;
import edu.virginia.engine.util.GameClock;

public class AnimatedSprite extends Sprite{
    private ArrayList animations;
    private boolean playing;
    private String fileName;
    private ArrayList frames;
    private int currentFrame;
    private int startFrame;
    private int endFrame;
    private static final int DEFAULT_ANIMATION_SPEED = 1;
    private int animationSpeed;
    private GameClock clock;

    public AnimatedSprite(String id, String fileName, Point position) {
        super(id);
        this.animations = new ArrayList();
        this.fileName = fileName;
        this.frames = new ArrayList();
        this.animationSpeed = 1;
        this.clock = new GameClock();
    }

    public void initGameClock(){
        if(this.clock == null){
            this.clock = new GameClock();
        }
    }
}
