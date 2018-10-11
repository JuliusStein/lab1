package edu.virginia.engine.display;

<<<<<<< HEAD
import java.awt.Point;
import java.util.ArrayList;

import edu.virginia.engine.util.GameClock;
import javafx.animation.Animation;

public class AnimatedSprite extends Sprite 
{
	private boolean playing;
	private String fileName;
	private ArrayList frames;
	private ArrayList<Animation> animations;
	private int currentFrame;
	private int startFrame;
	private int endFrame;
	private static final int DEFAULT_ANIMATION_SPEED = 3;
	private int animationSpeed;
	private GameClock gameClock;
	
	public AnimatedSprite(String ID, String fileName, Point position)
	{
		super(ID);
		this.fileName = fileName;
		this.frames = new ArrayList();
		this.animations = new ArrayList();
		this.currentFrame = 0;
		this.startFrame = 0;
		this.endFrame = 0;
		this.animationSpeed = 0;
		this.gameClock = null;
	}
	
	private void initGameClock()
	{
		if (this.gameClock == null)
			this.gameClock = new GameClock();
	}
	
	private Animation getAnimation(String id)
	{
		for (int i = 0; i < this.animations.size(); i++)
			if (animations.get(i).getId().equals(id))
				return animations.get(i);
		return null;
	}
	
	private void animate(Animation animation)
	{
		this.startFrame = animation.getStartFrame();
		this.endFrame = animation.getEndFrame();
	}
	
	private void animate(String id)
	{
		Animation anim = getAnimation(id);
		this.startFrame = anim.getStartFrame();
		this.endFrame = anim.getEndFrame();
	}
	
	private void animate(int start, int end)
	{
		this.startFrame = start;
		this.endFrame = end;
	}
	
	private void stopAnimation(int frame)
	{
		//stops the animation at this frame. 
		//(Hint: Use some of the code from initializeFrames() to extract this image.)
	}
	
	private void stopAnimation()
	{
		stopAnimation(0);
	}
	
	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public int getStartFrame() {
		return startFrame;
	}

	public void setStartFrame(int startFrame) {
		this.startFrame = startFrame;
	}

	public int getEndFrame() {
		return endFrame;
	}

	public void setEndFrame(int endFrame) {
		this.endFrame = endFrame;
	}

	public int getAnimationSpeed() {
		return animationSpeed;
	}

	public void setAnimationSpeed(int animationSpeed) {
		this.animationSpeed = animationSpeed;
	}

	public GameClock getGameClock() {
		return gameClock;
	}

	public void setGameClock(GameClock gameClock) {
		this.gameClock = gameClock;
	}
=======
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
>>>>>>> 3b7ff519a75888cebaf0777816b234c4dff04e83
}
