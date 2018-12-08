package edu.virginia.finalProject;

import edu.virginia.engine.display.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

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
    private boolean pickedUp = false;

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public FinalProject() {
        super("Final Project Game", 1385, 775);
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys, ArrayList<Integer> pressedMouse){
        super.update(pressedKeys, pressedMouse);

        Point p = MouseInfo.getPointerInfo().getLocation();
        //System.out.println(p.getX() + " " + p.getY());

        if (pressedMouse.contains((Integer)1))
        {
        	if (pickedUp == false)
        	{
	        	if (p.getX() >= 225 && p.getX() <= 598 && p.getY() >= 75 && p.getY() <= 775)
	        		System.out.println("inside the bank");
	        	if (p.getX() >= 650 && p.getX() <= 1350 && p.getY() >= 75 && p.getY() <= 775)
	        		System.out.println("inside the board");
	        	
	        	pickedUp = true;
        	}
        	
        	if (p.getX() >= 25 && p.getX() <= 175 && p.getY() >= 340 && p.getY() <= 390)
        		System.out.println("inside the green");
        	if (p.getX() >= 25 && p.getX() <= 175 && p.getY() >= 415 && p.getY() <= 465)
        		System.out.println("inside the yellow");
        	if (p.getX() >= 25 && p.getX() <= 175 && p.getY() >= 490 && p.getY() <= 540)
        		System.out.println("inside the red");
            // pick up the piece
            // if (over the piece that we can pick up)
        }
        else if (pickedUp == true)
        {
        		// drop the piece this time
        		pickedUp = false;
        }

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

    }

    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);
        if(background != null) background.draw(g);
        if(board != null) board.draw(g);
        if(partBank != null) partBank.draw(g);

        g.setColor(Color.GREEN);
        g.fillRect(25, 290, 150, 50);

        g.setColor(Color.YELLOW);
        g.fillRect(25, 365, 150, 50);

        g.setColor(Color.RED);
        g.fillRect(25, 440, 150, 50);

        g.setColor(Color.BLACK);
        g.drawString("CHECK",75, 320);
        g.drawString("HINT",80, 395);
        g.drawString("QUIT",80, 470);

        g.fillRect(790, 25, 5,700);
        g.fillRect(930, 25, 5,700);
        g.fillRect(1070, 25, 5,700);
        g.fillRect(1210, 25, 5,700);

        g.fillRect(650, 165, 700, 5);
        g.fillRect(650, 305, 700, 5);
        g.fillRect(650, 445, 700, 5);
        g.fillRect(650, 585, 700, 5);



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


//		sound.loadSoundEffect("jump", "resources/jump.wav");
//		sound.loadSoundEffect("death", "resources/death.wav");
//		sound.loadSoundEffect("win", "resources/win.wav");
//		sound.loadMusic("theme", "resources/theme.wav");

        //sound.playMusic("theme");

        //mario.populate();
        background.setPosition(new Point(0,0));
        partBank.setPosition(new Point(225,25));
        board.setPosition(new Point(650,25));

        Scanner in = new Scanner(System.in);
        String input = "";
//        System.out.println("Please input the level you would like to play (\"1\", \"2\", or \"3\")");
//        
//        input = in.nextLine();
//        		
//        if (input.equals("1"))
//        {
//        	// start level 1
//        	System.out.println("level 1");
//        }
//        else if (input.equals("2"))
//        {
//        	// start level 2
//        	System.out.println("level 2");
//        }
//        else if (input.equals("3"))
//        {
//        	// start level 3
//        	System.out.println("level 3");
//        }
//        else
//        {
//        	System.out.println("That does not correspond to one of the levels! Quitting");
//        }
                
        final JFrame parent = new JFrame();
        
        JLabel jlabel = new JLabel("Choose a level!");
        jlabel.setFont(new Font("Verdana",1,20));
        
        parent.add(jlabel);
        
        JRadioButton option1 = new JRadioButton("Level 1");
        JRadioButton option2 = new JRadioButton("Level 2");
        JRadioButton option3 = new JRadioButton("Level 3");
 
        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
 
        parent.setLayout(new FlowLayout());
 
        parent.add(option1);
        parent.add(option2);
        parent.add(option3);
 
        parent.pack();
        parent.setVisible(true);
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        boolean level1 = option1.isSelected();
        boolean level2 = option2.isSelected();
        boolean level3 = option3.isSelected();
        
        if (level1)
        {
        	populateLevel(1);
        	parent.setVisible(false);
        	parent.dispose();
        }
        else if (level2)
        {
        	populateLevel(2);
        	parent.setVisible(false);
        	parent.dispose();
        }
        else if(level3)
        {
        	populateLevel(3);
        	parent.setVisible(false);
        	parent.dispose();
        }
        else
        	System.out.println("No level selected");
        
        game.start();
    }

	private static void populateLevel(int level) 
	{
		if (level == 1)
		{
			
		}
		else if (level == 2)
		{
			
		}
		else if (level == 3)
		{
			
		}
	}
}
