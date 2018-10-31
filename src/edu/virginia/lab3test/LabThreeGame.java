package edu.virginia.lab3test;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabThreeGame extends Game{

    static DisplayObjectContainer sun = new DisplayObjectContainer("sun", "sun.png");
    static DisplayObject earth = new DisplayObject("earth", "earth.png");
    static DisplayObject mars = new DisplayObject("mars", "mars.png");
    static DisplayObjectContainer jupiter = new DisplayObjectContainer("jupiter", "jupiter.png");
    static DisplayObject moon = new DisplayObject("moon", "moon.png");


    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public LabThreeGame() {
        super("Lab Three Test Game", 800, 800);
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
        
        if (pressedKeys.contains(KeyEvent.VK_Q))
        {
        	//sun.setRotation(sun.getRotation() + 2);
        	//earth.setPivotPoint(new Point(sun.getPosition().x, sun.getPosition().y));
        	earth.setRotation(earth.getRotation() + 2);
        	//mars.setPivotPoint(new Point(sun.getPosition().x, sun.getPosition().y));
        	mars.setRotation(mars.getRotation() + 2);
        	//jupiter.setPivotPoint(new Point(0, 0));
        	jupiter.setRotation(jupiter.getRotation() + 2);
        }
        //if (pressedKeys.contains(KeyEvent.VK_W))
        //	sun.setRotation(sun.getRotation() - 2);
        
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
        LabThreeGame game = new LabThreeGame();
        sun.setPosition(new Point(350, 350));
        //sun.setPivotPoint(new Point(50, 50));
        
        jupiter.setPosition(new Point(-200, 0));
        mars.setPosition(new Point(50, 100));
        
        sun.addChild(earth);
        sun.addChild(mars);
        sun.addChild(jupiter);
        jupiter.addChild(moon);
        
//        earth.setPivotPoint(new Point(400, 400));
//        mars.setPivotPoint(new Point(400, 400));
//        jupiter.setPivotPoint(new Point(400, 400));
        
        System.out.println(earth.getPivotPoint());
        System.out.println(mars.getPivotPoint());
        System.out.println(jupiter.getPivotPoint());
        
        //moon.setPosition(new Point(-20, -20));

        game.start();
    }
}
