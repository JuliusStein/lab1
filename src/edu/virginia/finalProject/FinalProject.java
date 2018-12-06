package edu.virginia.finalProject;

import edu.virginia.engine.display.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class FinalProject extends Game{

	static Board board = new Board("board", "board.png");
	static DisplayObject background = new DisplayObject("background", "background.png");
    static DisplayObject partBank = new DisplayObject("partBank", "partBank.png");

    static SoundManager sound = new SoundManager();
    @SuppressWarnings("unused")
    private boolean hitBox = false;

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public FinalProject() {
        super("Final Project Game", 1400, 800);
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys, ArrayList<Integer> pressedMouse){
        super.update(pressedKeys, pressedMouse);

        Point p = MouseInfo.getPointerInfo().getLocation();
        //System.out.println("Mouse position: x = " + p.x + " y = " + p.y);

        if (pressedMouse.contains(1))
        {
            //System.out.println("mouse pressed yeet");
            // this is where mouse down feature will go
        }

        //mario.setPlaying(false);

        /* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        //if (mario != null) mario.update(pressedKeys);


        //Move
        //if (pressedKeys.contains(KeyEvent.VK_UP))


        // pivot point
//		if (pressedKeys.contains(KeyEvent.VK_I))
//			mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y - 5));
//		if (pressedKeys.contains(KeyEvent.VK_K))
//			mario.setPivotPoint(new Point(mario.getPivotPoint().x, mario.getPivotPoint().y + 5));
//		if (pressedKeys.contains(KeyEvent.VK_J))
//			mario.setPivotPoint(new Point(mario.getPivotPoint().x - 5, mario.getPivotPoint().y));
//		if (pressedKeys.contains(KeyEvent.VK_L))
//			mario.setPivotPoint(new Point(mario.getPivotPoint().x + 5, mario.getPivotPoint().y));

        //System.out.println("Position: "+mario.getPosition()+" |##| Pivot: "+mario.getPivotPoint());


    }

    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);
        if(board != null) board.draw(g);
        if(partBank != null) partBank.draw(g);
        if(background != null) background.draw(g);

        /* Same, just check for null in case a frame gets thrown in before Mario is initialized */
        //if((mario != null)&&(mario.isVisible())) mario.draw(g);
        //g.drawString(Integer.toString(points), 10, 20);


		/*g.drawShape((int)(((Rectangle)mario.getHitBox()).getX()),(int)((Rectangle)mario.getHitBox()).getY(),
				(int)((Rectangle)mario.getHitBox()).getWidth(),(int)((Rectangle)mario.getHitBox()).getHeight());*/
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * @throws IOException
     * */
    public static void main(String[] args) throws IOException {
        FinalProject game = new FinalProject();

		/*TODO:
			- change collides with or box for rotation
			- point structure?
			- win score/screen
			- change hitbox based on scale */

//		sound.loadSoundEffect("jump", "resources/jump.wav");
//		sound.loadSoundEffect("death", "resources/death.wav");
//		sound.loadSoundEffect("win", "resources/win.wav");
//		sound.loadMusic("theme", "resources/theme.wav");

        //sound.playMusic("theme");

        //mario.populate();
        background.setPosition(new Point(0,0));
        partBank.setPosition(new Point(100,100));
        board.setPosition(new Point(500,100));

        game.start();
    }
}
