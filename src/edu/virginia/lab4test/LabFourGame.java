package edu.virginia.lab4test;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.SoundManager;
import edu.virginia.engine.display.Sprite;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

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
	static Sprite goomba = new Sprite("goomba", "goomba.png");
	static Sprite flag = new Sprite("flag", "flagpole.png");
	static SoundManager sound = new SoundManager();
	static int points = 10;
	private boolean playingDeath = false;
	private boolean playingWin = false;
	private int playingDeathTimer = 0;
	private int playingWinTimer = 0;

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

		if(playingDeath){
			if(playingDeathTimer>0){
				playingDeathTimer--;
			}else{
				playingDeath=false;
			}
		}

		if(playingWin){
			if(playingWinTimer>0){
				playingWinTimer--;
			}else{
				System.exit(0);
			}
		}

		if(mario.isJumping()){
			if(mario.getJumpTimer()>10){
				mario.setPosition(new Point(mario.getPosition().x+(5*mario.getFacing()) , mario.getPosition().y-10));
				mario.setPivotPoint(new Point(mario.getPivotPoint().x+(5*mario.getFacing()), mario.getPivotPoint().y - 10));
				mario.setJumpTimer(mario.getJumpTimer()-1);
			}else if (mario.getJumpTimer()>0) {
				mario.setPosition(new Point(mario.getPosition().x+(5*mario.getFacing()), mario.getPosition().y+10));
				mario.setPivotPoint(new Point(mario.getPivotPoint().x+(5*mario.getFacing()), mario.getPivotPoint().y + 10));
				mario.setJumpTimer(mario.getJumpTimer() - 1);
			}else{
				mario.setJumping(false);
			}
		}

		if(!playingDeath){

		}

		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if (mario != null) mario.update(pressedKeys);


		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if (mario != null) mario.update(pressedKeys);

		//Move
		if (pressedKeys.contains(KeyEvent.VK_UP)) {

			//mario.setPlaying(true);
			if(!mario.isJumping()){
				mario.setJumping(true);
				//mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y - 5));
				sound.playSoundEffect("jump");
				mario.setJumpTimer(20);
			}
		}
		if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
			mario.animate(0, 1);
			mario.setPlaying(true);
			mario.setFacing(1);
			mario.setPosition(new Point(mario.getPosition().x + 6, mario.getPosition().y));
			mario.setPivotPoint(new Point(mario.getPivotPoint().x + 6, mario.getPivotPoint().y));
		}
		if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
			mario.animate(2, 3);
			mario.setPlaying(true);
			mario.setFacing(-1);
			mario.setPosition(new Point(mario.getPosition().x - 6, mario.getPosition().y));
			mario.setPivotPoint(new Point(mario.getPivotPoint().x - 6, mario.getPivotPoint().y));
		}

//			if (pressedKeys.contains(KeyEvent.VK_UP)) {
//				mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y - 5));
//			}

		if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
			mario.setPosition(new Point(mario.getPosition().x, mario.getPosition().y + 5));
			mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y + 5));
		}

		// Rotate
		if (pressedKeys.contains(KeyEvent.VK_Q))
		{
				mario.setRotation(mario.getRotation() + 5);
		}
		if (pressedKeys.contains(KeyEvent.VK_W))
		{
				mario.setRotation(mario.getRotation() - 5);
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
		if (pressedKeys.contains(KeyEvent.VK_A))
		{
			mario.setScaleX(mario.getScaleX() + .05);
			mario.setScaleY(mario.getScaleY() + .05);
		}
		if (pressedKeys.contains(KeyEvent.VK_S))
		{
			mario.setScaleX(mario.getScaleX() - .05);
			mario.setScaleY(mario.getScaleY() - .05);
			if ((mario.getScaleX() < 0) || (mario.getScaleY() < 0)) {
				mario.setScaleX(0);
				mario.setScaleY(0);
			}

		}

		if (pressedKeys.contains(KeyEvent.VK_V))
		{
			mario.setVisible(!mario.isVisible());
		}


		if((mario.getGameClock().getElapsedTime()>mario.getAnimationSpeed())&&(mario.isPlaying())){
			if (mario.getCurrentFrame() < mario.getEndFrame())
				mario.setCurrentFrame(mario.getCurrentFrame() + 1);
			else
				mario.setCurrentFrame(mario.getStartFrame());

        	mario.getGameClock().resetGameClock();
		}

		//mario.updateHitbox();

		if (mario.collidesWith(goomba)){
			points -= 1;
			if(!playingDeath){
				playingDeath = true;
				this.playingDeathTimer = 162;
				sound.playSoundEffect("death");
			}

		}

		if (mario.collidesWith(flag)){
			points += 100;
			if(!playingWin){
				playingWin = true;
				playingWinTimer = 330;
				sound.playSoundEffect("win");
			}

		}

		//System.out.println("Position: "+mario.getPosition()+" |##| Pivot: "+mario.getPivotPoint());

		
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		
		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		if(goomba != null) goomba.draw(g);
		if(flag != null) flag.draw(g);
		if((mario != null)&&(mario.isVisible())) mario.draw(g);
		g.drawString(Integer.toString(points), 10, 20);

		//WIN CONDITION
		if(this.points >=100){
			for(int i=1; i<=15; i++){
				for(int j=1; j<=15; j++){
					g.drawString("YOU WIN", i*60, j*20);
					g.drawString("YOU WIN", (i*60)+30, (j*20)+10);
				}
			}
		}
		//////////////

		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(mario.getHitBox());
		/*g.drawShape((int)(((Rectangle)mario.getHitBox()).getX()),(int)((Rectangle)mario.getHitBox()).getY(),
				(int)((Rectangle)mario.getHitBox()).getWidth(),(int)((Rectangle)mario.getHitBox()).getHeight());*/
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * @throws IOException 
	 * */
	public static void main(String[] args) throws IOException {
		LabFourGame game = new LabFourGame();
		
		/*TODO: 
			- change collides with or box for rotation
			- point structure?
			- win score/screen
			- change hitbox based on scale */
		
		sound.loadSoundEffect("jump", "resources/jump.wav");
		sound.loadSoundEffect("death", "resources/death.wav");
		sound.loadSoundEffect("win", "resources/win.wav");
		sound.loadMusic("theme", "resources/theme.wav");
		
		sound.playMusic("theme");

		mario.setPosition(new Point(mario.getPosition().x + 5, mario.getPosition().y + 50));
		mario.setPivotPoint(new Point (0,0));
		mario.setVisible(true);
		goomba.setPosition(new Point(500, 150));
		flag.setPosition(new Point(900, 10));

		mario.populate();
		mario.animate(0,1);
		mario.setAnimationSpeed(100);
		mario.setJumping(false);
		
		game.start();
	}
}
