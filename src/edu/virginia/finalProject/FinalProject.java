package edu.virginia.finalProject;

import edu.virginia.engine.display.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class FinalProject extends Game{

	static Board board = new Board("board", "board.png");
	static DisplayObject background = new DisplayObject("background", "background.png");
    static DisplayObject partBank = new DisplayObject("partBank", "partBank.png");
    static Piece p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, bomb, battery;
    static ArrayList<Piece> bank = new ArrayList<Piece>();
    static Piece inHand = null;
    static double offsetx = 0;
    static double offsety = 0;
    static int currentLevel = 0;

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

    public static void addPieceAtIndex(Piece newPiece, int index){
        newPiece.setIndex(index);
        if((index<bank.size())&&(index >= 0)){
            bank.add(index, newPiece);
        }else{
            bank.add(newPiece);
        }
    }

    public static void removePieceAtIndex(int index){
        bank.remove(index);
    }

    public static Piece getPieceAtIndex(int index){
        return bank.get(index);
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys, ArrayList<Integer> pressedMouse){
        super.update(pressedKeys, pressedMouse);

        Point p = MouseInfo.getPointerInfo().getLocation();
        
        if (pickedUp)
        {	
        	if (inHand != null)
        		inHand.setPosition(new Point((int)(p.x - offsetx), (int)(p.y - 50 - offsety)));
        	else
        		pickedUp = false;
        }

        if (pressedMouse.contains((Integer)1))
        {
        	if (pickedUp == false)
        	{
	        	if (p.getX() >= 185 && p.getX() <= 623 && p.getY() >= 75 && p.getY() <= 775)
	        	{
	        		for (int i = 0; i < bank.size(); i++)
	        		{
	        			Piece pieceI = bank.get(i);
	        			if (pieceI != null)
	        			{
	        				if (p.getX() >= pieceI.getPosition().x && 
	        					p.getX() <= (pieceI.getPosition().x + 136)  && 
	        					p.getY() >= (pieceI.getPosition().y + 50) && 
	        					p.getY() <= (pieceI.getPosition().y + 136 + 50))
	        				{
	        					inHand = pieceI;
	        					offsetx = p.getX() - pieceI.getPosition().x;
	        					offsety = p.getY() - (pieceI.getPosition().y + 50);
	        				}
	        			}
	        		}
	        	}
	        	if (p.getX() >= 650 && p.getX() <= 1350 && p.getY() >= 75 && p.getY() <= 775)
	        	{
	        		System.out.println("inside the board");
	        		// TODO: this
	        		// determine what board square you are in with coordinates - then save those temporarily
	        		// determine if a piece is there, if so, pick it up
	        		// empty the array section on the board if so
	        	}
	        	
	        	pickedUp = true;
        	}
        	
        	if (p.getX() >= 15 && p.getX() <= 165 && p.getY() >= 340 && p.getY() <= 390)
        	{
        		System.out.println("inside the green");
        		// TODO: this
        		
        		// call the check correct method or whatever we want to call it
        	}
        	if (p.getX() >= 15 && p.getX() <= 165 && p.getY() >= 415 && p.getY() <= 465)
        	{
        		System.out.println("inside the yellow");
        		// TODO: this
        		
        		// call the hint method? maybe if we decide to do it
        	}
        	if (p.getX() >= 15 && p.getX() <= 165 && p.getY() >= 490 && p.getY() <= 540)
        	{	
        		getMainFrame().setVisible(false);
            	getMainFrame().dispose();   
            	System.exit(0);
            }
        }
        else if (pickedUp == true)
        {
        	if (p.getX() >= 650 && p.getX() <= 1350 && p.getY() >= 75 && p.getY() <= 775)
        	{
        		int x = (int) ((p.getX() - 650) / 141);
        		int y = (int) ((p.getY() - 75) / 141);		
        		
        		if (board.getTaken()[x][y])
        			inHand.setPosition(inHand.getStartingPosition());
        		else
        		{
        			// JULIUS THIS IS THE PLACE THAT IS HAVING ISSUES BECAUSE OF THE ADDPIECEATINDEX FUNCTION!!!!
        			// CHANGE BOARD.JAVA SO PIECES IS AN ARRAY AND NOT ARRAYLIST, THANK YOU BB LOVE YOUUUUUUU
        			inHand.setPosition(new Point(650 + (x * 141), 25 + (y * 141)));
        			board.addPieceAtIndex(inHand, 5 * y + x);
        		}
        	}
        	
        	pickedUp = false;
        	inHand = null;
        	offsetx = 0;
        	offsety = 0;
        }
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
        g.fillRect(15, 290, 150, 50);

        g.setColor(Color.YELLOW);
        g.fillRect(15, 365, 150, 50);

        g.setColor(Color.RED);
        g.fillRect(15, 440, 150, 50);

        g.setColor(Color.BLACK);
        g.drawString("CHECK",65, 320);
        g.drawString("HINT",70, 395);
        g.drawString("QUIT",70, 470);

        g.fillRect(790, 25, 5,700);
        g.fillRect(930, 25, 5,700);
        g.fillRect(1070, 25, 5,700);
        g.fillRect(1210, 25, 5,700);

        g.fillRect(650, 165, 700, 5);
        g.fillRect(650, 305, 700, 5);
        g.fillRect(650, 445, 700, 5);
        g.fillRect(650, 585, 700, 5);

        for(int i=0; i<bank.size(); i++){
            getPieceAtIndex(i).draw(g);
        }

        for(int j=0; j<25; j++){
            if(board.getPieceAtIndex(j) != null){
                board.getPieceAtIndex(j).draw(g);
            }
        }
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
        partBank.setPosition(new Point(185,25));
        board.setPosition(new Point(650,25));
                
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
            currentLevel = 1;
        	populateLevel(1);
        	parent.setVisible(false);
        	parent.dispose();
        }
        else if (level2)
        {
            currentLevel = 2;
        	populateLevel(2);
        	parent.setVisible(false);
        	parent.dispose();
        }
        else if(level3)
        {
            currentLevel = 3;
        	populateLevel(3);
        	parent.setVisible(false);
        	parent.dispose();
        }
        else
        {
        	System.out.println("No level selected");
        	parent.setVisible(false);
        	parent.dispose();
        	game.getMainFrame().setVisible(false);
        	game.getMainFrame().dispose();
        	return;
        }
        
        game.start();

        //Restart on win case?
    }

	private static void populateLevel(int level) 
	{
		if (level == 1)
		{	
			bomb = new Piece("bomb", "bomb.png", 5, -5, 0, 0);
			bomb.setPosition(new Point(650, 448));
			bomb.setStartingPosition(new Point(650, 448));
			board.addPieceAtIndex(bomb, 15);
			battery = new Piece("battery", "battery.png", 5, -5, 0, 0);
			battery.setPosition(new Point(650, 307));
			battery.setStartingPosition(new Point(650, 307));
			board.addPieceAtIndex(battery, 10);
			p15 = new Piece("topRightStuck", "TopRight.png", 1, -5, 0, 0);
			p15.setPosition(new Point(650, 589));
			p15.setStartingPosition(new Point(650, 589));
			board.addPieceAtIndex(p15, 20);

			board.setBatteryIndex(10);
			board.setBombIndex(15);
			board.setFinalResistance(15);
			board.setBatteryVoltage(0);
			
		    p1 = new Piece("horizontalWire", "Horizontal.png", -1, 1, 0, 0);
			p1.setPosition(new Point(185, 25));
			p1.setStartingPosition(new Point(185, 25));
			addPieceAtIndex(p1, 0);
			p2 = new Piece("verticalWire", "Vertical.png", -5, 5, 0, 0);
            p2.setPosition(new Point(331, 25));
            p2.setStartingPosition(new Point(331, 25));
            addPieceAtIndex(p2, 1);
            p3 = new Piece("topLeft", "TopLeft.png", -1, -5, 0, 0);
            p3.setPosition(new Point(477, 25));
            p3.setStartingPosition(new Point(477, 25));
            addPieceAtIndex(p3, 2);
            p4 = new Piece("bottomLeft", "BottomLeft.png", -1, 5, 0, 0);
            p4.setPosition(new Point(185, 171));
            p4.setStartingPosition(new Point(185, 171));
            addPieceAtIndex(p4, 3);
            p5 = new Piece("topRight", "TopRight.png", -5, 1, 0, 0);
            p5.setPosition(new Point(331, 171));
            p5.setStartingPosition(new Point(331, 171));
            addPieceAtIndex(p5, 4);
            p6 = new Piece("bottomRight", "BottomRight.png", 5, 1, 0, 0);
            p6.setPosition(new Point(477, 171));
            p6.setStartingPosition(new Point(477, 171));
            addPieceAtIndex(p6, 5);
            p7 = new Piece("horizontalResistor", "resistorHorizontal5.png", -5, 1, 0, 5);
            p7.setPosition(new Point(185, 317));
            p7.setStartingPosition(new Point(185, 317));
            addPieceAtIndex(p7, 6);
            p8 = new Piece("verticalResistor", "resistorVertical10.png", 5, 1, 0, 10);
            p8.setPosition(new Point(331, 317));
            p8.setStartingPosition(new Point(331, 317));
            addPieceAtIndex(p8, 7);
		}
		else if (level == 2)
		{
			
		}
		else if (level == 3)
		{
			
		}
	}
}
