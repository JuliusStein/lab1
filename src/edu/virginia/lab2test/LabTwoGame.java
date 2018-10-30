package edu.virginia.lab2test;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabTwoGame extends Game{

	static /* Create a sprite object for our game. We'll use mario */
	AnimatedSprite mario = new AnimatedSprite("Mario", "Mario.png", new Point(0,0));
	static DisplayObjectContainer sun = new DisplayObjectContainer("sun", "sun.png");
	static DisplayObject earth = new DisplayObject("earth", "earth.png");


	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabTwoGame() {
		super("Lab Two Test Game", 500, 500);
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
		
		if (pressedKeys.contains(KeyEvent.VK_UP)) 
			sun.setPosition(new Point(sun.getPosition().x, sun.getPosition().y + 5));
		if (pressedKeys.contains(KeyEvent.VK_DOWN)) 
			sun.setPosition(new Point(sun.getPosition().x, sun.getPosition().y - 5));
		if (pressedKeys.contains(KeyEvent.VK_RIGHT)) 
			sun.setPosition(new Point(sun.getPosition().x - 5, sun.getPosition().y));
		if (pressedKeys.contains(KeyEvent.VK_LEFT)) 
			sun.setPosition(new Point(sun.getPosition().x + 5, sun.getPosition().y));
		
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		
		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */		
		if (sun != null) sun.draw(g);
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * @throws IOException 
	 * */
	public static void main(String[] args) throws IOException {
		LabTwoGame game = new LabTwoGame();
		sun.setScaleX(.5);
		sun.setScaleY(.5);
		sun.setPosition(new Point(180, 180));
		
//		earth.setScaleX(.2);
//		earth.setScaleY(.2);
		sun.addChild(earth);
		
		game.start();
	}
}
