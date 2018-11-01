package edu.virginia.lab4test;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabFourGame extends Game{

	static /* Create a sprite object for our game. We'll use mario */
	AnimatedSprite mario = new AnimatedSprite("Mario", "Mario.png", new Point(0,0));


	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabFourGame() {
		super("Lab Four Test Game", 1000, 300);
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
		mario.setPlaying(false);

		if(mario.isJumping()){
			if(mario.getJumpTimer()>4){
				mario.setPosition(new Point(mario.getPosition().x , mario.getPosition().y-12));
				mario.setJumpTimer(mario.getJumpTimer()-1);
			}else if (mario.getJumpTimer()>0) {
				mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y + 12));
				mario.setJumpTimer(mario.getJumpTimer() - 1);
			}else{
				mario.setJumping(false);
			}

		}else{

			/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
			if (mario != null) mario.update(pressedKeys);


			/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
			if (mario != null) mario.update(pressedKeys);

			//Move
			if (pressedKeys.contains(KeyEvent.VK_UP)) {
				mario.setJumping(true);
				//mario.setPlaying(true);
				mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y - 12));
				if(mario.getFacing()==1){
					//mario.animate(4,5);
				}else if(mario.getFacing()==-1){
					//mario.animate(6,7);
				}
				mario.setJumpTimer(7);
			}
			if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
				mario.animate(0, 1);
				mario.setPlaying(true);
				mario.setFacing(1);
				mario.setPosition(new Point(mario.getPosition().x + 5, mario.getPosition().y));
			}
			if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
				mario.animate(2, 3);
				mario.setPlaying(true);
				mario.setFacing(-1);
				mario.setPosition(new Point(mario.getPosition().x - 5, mario.getPosition().y));
			}
			
//			if (pressedKeys.contains(KeyEvent.VK_UP)) {
//				mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y - 5));
//			}
			
			if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
				mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y + 5));
			}

			//Animation Speed
			if (pressedKeys.contains(KeyEvent.VK_X)) {
				mario.setAnimationSpeed(mario.getAnimationSpeed() + 5);
				if (mario.getAnimationSpeed() > 350) {
					mario.setAnimationSpeed(350);
				}
			}
			if (pressedKeys.contains(KeyEvent.VK_Z)) {
				mario.setAnimationSpeed(mario.getAnimationSpeed() - 5);
				if (mario.getAnimationSpeed() < 25) {
					mario.setAnimationSpeed(25);
				}
			}
			if (pressedKeys.contains(KeyEvent.VK_C)) {
				System.out.println("Animation Speed: " + mario.getAnimationSpeed());
			}

			if (pressedKeys.contains(null)) {
				if (mario.getFacing() == 1) {
					mario.animate(0, 0);
				} else if (mario.getFacing() == -1) {
					mario.animate(2, 2);
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
				if ((mario.getScaleX() < 0) || (mario.getScaleY() < 0)) {
					mario.setScaleX(0);
					mario.setScaleY(0);
				}
			}
		}

		
		if((mario.getGameClock().getElapsedTime()>mario.getAnimationSpeed())&&(mario.isPlaying())){
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
		LabFourGame game = new LabFourGame();

		mario.setPosition(new Point(mario.getPosition().x + 5, mario.getPosition().y+50));

		mario.populate();
		mario.animate(0,1);
		mario.setAnimationSpeed(100);
		
		
		game.start();
	}
}
