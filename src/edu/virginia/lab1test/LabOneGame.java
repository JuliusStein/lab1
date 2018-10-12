package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Animation;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabOneGame extends Game{

	static /* Create a sprite object for our game. We'll use mario */
	AnimatedSprite mario = new AnimatedSprite("Mario", "Mario.png", new Point(0,0));
	
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabOneGame() {
		super("Lab One Test Game", 500, 300);
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
		
		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if(mario != null) mario.update(pressedKeys);

		
		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if(mario != null) mario.update(pressedKeys);
		
		//Move
		if (pressedKeys.contains(KeyEvent.VK_UP))
			mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y - 5));
		if (pressedKeys.contains(KeyEvent.VK_DOWN))
				mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y + 5));
		if (pressedKeys.contains(KeyEvent.VK_RIGHT))
				mario.setPosition(new Point(mario.getPosition().x + 5, mario.getPosition().y));
		if (pressedKeys.contains(KeyEvent.VK_LEFT))
				mario.setPosition(new Point(mario.getPosition().x - 5, mario.getPosition().y));
		
		// Rotate
		if (pressedKeys.contains(KeyEvent.VK_Q))
				mario.setRotation(mario.getRotation() + 5);
		if (pressedKeys.contains(KeyEvent.VK_W))
				mario.setRotation(mario.getRotation() - 5);
		
		// Visible
		if (pressedKeys.contains(KeyEvent.VK_V))
				mario.setVisible(!mario.isVisible());
		
		//Alpha
		if (pressedKeys.contains(KeyEvent.VK_Z)){
			mario.setAlpha(mario.getAlpha() + .025f);
			if(mario.getAlpha()>1.0f){
				mario.setAlpha(1.0f);
			}
		}
		if (pressedKeys.contains(KeyEvent.VK_X)){
			mario.setAlpha(mario.getAlpha() - .025f);
			if(mario.getAlpha()<0.0f){
				mario.setAlpha(0.0f);
			}
		}
		
		//scale
		if (pressedKeys.contains(KeyEvent.VK_A)) {
			mario.setScaleX(mario.getScaleX() + .05);
			mario.setScaleY(mario.getScaleY() + .05);
		}
		if (pressedKeys.contains(KeyEvent.VK_S)) {
			mario.setScaleX(mario.getScaleX() - .05);
			mario.setScaleY(mario.getScaleY() - .05);
			if((mario.getScaleX()<0) || (mario.getScaleY()<0)){
				mario.setScaleX(0);
				mario.setScaleY(0);
			}
		}
		
		// pivot point
		if (pressedKeys.contains(KeyEvent.VK_I))
			mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y - 5));
		if (pressedKeys.contains(KeyEvent.VK_K))
			mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y + 5));
		if (pressedKeys.contains(KeyEvent.VK_J))
			mario.setPivotPoint(new Point(mario.getPivotPoint().x - 5, mario.getPivotPoint().y));
		if (pressedKeys.contains(KeyEvent.VK_L))
			mario.setPivotPoint(new Point(mario.getPivotPoint().x + 5, mario.getPivotPoint().y));
		
		if (pressedKeys.contains(KeyEvent.VK_H))
			mario.animate(0, 1);
		if (pressedKeys.contains(KeyEvent.VK_G))
			mario.animate(2, 3);
		
		System.out.println(mario.getGameClock().getElapsedTime());
		System.out.println(mario.getAnimationSpeed());
		if(mario.getGameClock().getElapsedTime()>mario.getAnimationSpeed()){
			if (mario.getCurrentFrame() < mario.getEndFrame())
				mario.setCurrentFrame(mario.getCurrentFrame() + 1);
			else 
				mario.setCurrentFrame(mario.getStartFrame());
			
        	mario.getGameClock().resetGameClock();
		}
		
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		
		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		if(mario != null) mario.draw(g);
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * @throws IOException 
	 * */
	public static void main(String[] args) throws IOException {
		LabOneGame game = new LabOneGame();
		
		// Set all the animation garbage here and starting info with mario
		//Animation right = new Animation("right", 0, 1);
		//Animation left = new Animation("left", 0, 1);
		mario.populate();
		mario.animate(0,1);
		mario.setAnimationSpeed(300);
		
		
		game.start();
	}
}
