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

    static DisplayObjectContainer solarSystem = new DisplayObjectContainer("solarSystem");
    static DisplayObjectContainer sun = new DisplayObjectContainer("sun", "sun.png");
    static DisplayObject earth = new DisplayObject("earth", "earth.png");
    static DisplayObject mars = new DisplayObject("mars", "mars.png");
    static DisplayObjectContainer jupiter = new DisplayObjectContainer("jupiter", "jupiter.png");
    static DisplayObject moon = new DisplayObject("moon", "moon.png");

    private int rotationDirection = 1;

    public int getRotationDirection() {
        return rotationDirection;
    }

    public void setRotationDirection(int rotationDirection) {
        this.rotationDirection = rotationDirection;
    }

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public LabThreeGame() {
        super("Lab Three Test Game", 1400, 900);
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

        if (pressedKeys.contains(KeyEvent.VK_UP))
            solarSystem.setPosition(new Point(solarSystem.getPosition().x, solarSystem.getPosition().y + 5));
        if (pressedKeys.contains(KeyEvent.VK_DOWN))
            solarSystem.setPosition(new Point(solarSystem.getPosition().x, solarSystem.getPosition().y - 5));
        if (pressedKeys.contains(KeyEvent.VK_RIGHT))
            solarSystem.setPosition(new Point(solarSystem.getPosition().x - 5, solarSystem.getPosition().y));
        if (pressedKeys.contains(KeyEvent.VK_LEFT))
            solarSystem.setPosition(new Point(solarSystem.getPosition().x + 5, solarSystem.getPosition().y));
        
        if (pressedKeys.contains(KeyEvent.VK_A))
        {
        	setRotationDirection(-1);
        }

        if (pressedKeys.contains(KeyEvent.VK_S))
        {
            setRotationDirection(1);
        }

        if (pressedKeys.contains(KeyEvent.VK_Q))
        {
            solarSystem.setScaleX(solarSystem.getScaleX()+0.05);
            solarSystem.setScaleY(solarSystem.getScaleY()+0.05);
        }

        if (pressedKeys.contains(KeyEvent.VK_W))
        {
            if(!((solarSystem.getScaleX()<=0.05)||(solarSystem.getScaleY()<=0.05))){
                solarSystem.setScaleX(solarSystem.getScaleX()-0.05);
                solarSystem.setScaleY(solarSystem.getScaleY()-0.05);
            }
        }

        earth.setRotation(earth.getRotation() + (2*getRotationDirection()));
        mars.setRotation(mars.getRotation() + (2*getRotationDirection()));
        jupiter.setRotation(jupiter.getRotation() + (2*getRotationDirection()));
        moon.setPivotPoint(new Point(jupiter.getPosition().x, jupiter.getPosition().y));
        moon.setPosition(new Point(jupiter.getPosition().x+350, jupiter.getPosition().y+350));
        moon.setRotation(moon.getRotation() + (2*getRotationDirection()));
        //moon.setRotation(jupiter.getRotation());
        System.out.println(jupiter.getPosition());
        System.out.println(jupiter.getPivotPoint());
        System.out.println(moon.getPosition());
        System.out.println(moon.getPivotPoint());


        if (pressedKeys.contains(KeyEvent.VK_W))
        {
            if(!((solarSystem.getScaleX()<=0.05)||(solarSystem.getScaleY()<=0.05))){
                solarSystem.setScaleX(solarSystem.getScaleX()-0.05);
                solarSystem.setScaleY(solarSystem.getScaleY()-0.05);
            }
        }

        earth.setRotation(earth.getRotation() + (2*getRotationDirection()));
        mars.setRotation(mars.getRotation() + (2*getRotationDirection()));
        jupiter.setRotation(jupiter.getRotation() + (2*getRotationDirection()));
        moon.setRotation(moon.getRotation() + (1*getRotationDirection()));
    }

    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        /* Same, just check for null in case a frame gets thrown in before Mario is initialized */
        if (solarSystem != null) solarSystem.draw(g);

    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * @throws IOException
     * */
    public static void main(String[] args) throws IOException {
        LabThreeGame game = new LabThreeGame();
        solarSystem.setPosition(new Point(700, 450));
        sun.setPosition(new Point(-50, -50));

        //sun.setPivotPoint(new Point(50, 50));
        

        //mars.setPosition(new Point(50, 100));
        solarSystem.addChild(sun);
        sun.setParent(solarSystem);
        sun.addChild(earth);
        earth.setParent(sun);
        sun.addChild(mars);
        mars.setParent(sun);
        sun.addChild(jupiter);
        jupiter.setParent(sun);
        jupiter.addChild(moon);
        moon.setParent(jupiter);

        earth.setPosition(new Point(-175,-175));
        mars.setPosition(new Point(-150,-150));
        jupiter.setPosition(new Point(-350, -350));
        moon.setPosition(new Point(0,0));

        earth.setPivotPoint(new Point(125, 125));
        mars.setPivotPoint(new Point(100, 100));
        jupiter.setPivotPoint(new Point(300, 300));
        moon.setPivotPoint(new Point(jupiter.getPosition().x, jupiter.getPosition().y));

        System.out.println(jupiter.getPosition());
        System.out.println(jupiter.getPivotPoint());
        System.out.println(moon.getPosition());
        System.out.println(moon.getPivotPoint());
        moon.setPivotPoint(new Point(jupiter.getPosition().x, jupiter.getPosition().x));

        System.out.println(earth.localToGlobal(earth.getPivotPoint()));
        System.out.println(earth.localToGlobal(earth.getPosition()));

        //System.out.println(jupiter.getPivotPoint());
        
        //moon.setPosition(new Point(-20, -20));

        game.start();
    }
}
