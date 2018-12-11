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
    static DisplayObject congratulations = new DisplayObject("congratulations", "congratulations.png");
    static DisplayObject losePic = new DisplayObject("losePic", "lose.png");
    static Piece p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, bomb, battery;
    static ArrayList<Piece> bank = new ArrayList<Piece>();
    static Piece inHand = null;
    static double offsetx = 0;
    static double offsety = 0;
    static int currentLevel = 0;
    static boolean canHint = false;
    static double sinceHint = 0;
    static double time = 300;

    static SoundManager sound = new SoundManager();
    @SuppressWarnings("unused")
    private boolean hitBox = false;
    private boolean pickedUp = false;
    private boolean finished = false;
    private boolean lose = false;

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
        
        if (sinceHint >= 120)
        	canHint = true;
        else
        	sinceHint+=1;
        
        time -= (1.0/60);
        if (time < 1)
        {
        	if (lose == false)
        		sound.playSoundEffect("boom");
        	lose = true;
        }

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
	        		int x = (int) ((p.getX() - 650) / 141);
	        		int y = (int) ((p.getY() - 75) / 141);		
	        		
	        		if (board.getTaken()[x][y])
	        		{
	        			Piece pieceI = board.getPieceAtIndex(5 * y + x);
	        			if (pieceI.isMovable())
	        			{
		        			board.removePieceAtIndex(5 * y + x);
		        			inHand = pieceI;
		        			offsetx = p.getX() - pieceI.getPosition().x;
		        			offsety = p.getY() - (pieceI.getPosition().y + 50);
	        			}
	        		}
	        	}
	        	
	        	pickedUp = true;
        	}
        	
        	if (p.getX() >= 15 && p.getX() <= 165 && p.getY() >= 340 && p.getY() <= 390)
        	{
                if (board.hasCorrectResistance())
                {
                    sound.playSoundEffect("right");
                    finished = true;
                }
                else
                {
                    sound.playSoundEffect("wrong");
                }

        	}
        	if (p.getX() >= 15 && p.getX() <= 165 && p.getY() >= 415 && p.getY() <= 465)
        		hint(currentLevel);
        	
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

    private void hint(int currentLevel2) 
    {
    	if (!canHint)
    		return;
		if (currentLevel2 == 1)
		{
			if (board.getPieceAtIndex(5) != p6)
			{
				if (board.getPieceAtIndex(5) != null)
				{
					Piece piece = board.getPieceAtIndex(5);
					board.removePieceAtIndex(5);
					bank.add(piece);
					piece.setPosition(piece.getStartingPosition());
				}
				
				if (board.getPiece("bottomRight") != null)
				{
					// piece is in board
					Piece piece = board.getPiece("bottomRight");
					piece.setPosition(new Point(650, 166));
					board.removePieceAtIndex(piece.getIndex());
					board.addPieceAtIndex(piece, 5);
					piece.setMovable(false);
				}
				else
				{
					// piece is in bank
					Piece piece = null;
					if (bank.contains(p6))
						piece = bank.get(bank.indexOf(p6));
					else
						return;
					piece.setPosition(new Point(650, 166));
					removePieceAtIndex(bank.indexOf(p6));
					board.addPieceAtIndex(piece, 5);
					piece.setMovable(false);
				}
			}
			else if (board.getPieceAtIndex(6) != p1)
			{
				if (board.getPieceAtIndex(6) != null)
				{
					Piece piece = board.getPieceAtIndex(6);
					board.removePieceAtIndex(6);
					bank.add(piece);
					piece.setPosition(piece.getStartingPosition());
				}
				
				if (board.getPiece("horizontalWire") != null)
				{
					// piece is in board
					Piece piece = board.getPiece("horizontalWire");
					piece.setPosition(new Point(791, 166));
					board.removePieceAtIndex(piece.getIndex());
					board.addPieceAtIndex(piece, 6);
					piece.setMovable(false);
				}
				else
				{
					// piece is in bank
					Piece piece = null;
					if (bank.contains(p1))
						piece = bank.get(bank.indexOf(p1));
					else
						return;
					piece.setPosition(new Point(791, 166));
					removePieceAtIndex(bank.indexOf(p1));
					board.addPieceAtIndex(piece, 6);
					piece.setMovable(false);
				}
			}
			else if (board.getPieceAtIndex(7) != p4)
			{
				if (board.getPieceAtIndex(7) != null)
				{
					Piece piece = board.getPieceAtIndex(7);
					board.removePieceAtIndex(7);
					bank.add(piece);
					piece.setPosition(piece.getStartingPosition());
				}
				
				if (board.getPiece("bottomLeft") != null)
				{
					// piece is in board
					Piece piece = board.getPiece("bottomLeft");
					piece.setPosition(new Point(932, 166));
					board.removePieceAtIndex(piece.getIndex());
					board.addPieceAtIndex(piece, 7);
					piece.setMovable(false);
				}
				else
				{
					// piece is in bank
					Piece piece = null;
					if (bank.contains(p4))
						piece = bank.get(bank.indexOf(p4));
					else
						return;
					piece.setPosition(new Point(932, 166));
					removePieceAtIndex(bank.indexOf(p4));
					board.addPieceAtIndex(piece, 7);
					piece.setMovable(false);
				}
			}
		}
		else if (currentLevel2 == 2)
		{
			if (board.getPieceAtIndex(5) != p2)
			{
				if (board.getPieceAtIndex(5) != null)
				{
					Piece piece = board.getPieceAtIndex(5);
					board.removePieceAtIndex(5);
					bank.add(piece);
					piece.setPosition(piece.getStartingPosition());
				}
				
				if (board.getPiece("bottomRight") != null)
				{
					// piece is in board
					Piece piece = board.getPiece("bottomRight");
					piece.setPosition(new Point(650, 166));
					board.removePieceAtIndex(piece.getIndex());
					board.addPieceAtIndex(piece, 5);
					piece.setMovable(false);
				}
				else
				{
					// piece is in bank
					Piece piece = null;
					if (bank.contains(p2))
						piece = bank.get(bank.indexOf(p2));
					else
						return;
					piece.setPosition(new Point(650, 166));
					removePieceAtIndex(bank.indexOf(p2));
					board.addPieceAtIndex(piece, 5);
					piece.setMovable(false);
				}
			}
			else if (board.getPieceAtIndex(6) != p1)
			{
				if (board.getPieceAtIndex(6) != null)
				{
					Piece piece = board.getPieceAtIndex(6);
					board.removePieceAtIndex(6);
					bank.add(piece);
					piece.setPosition(piece.getStartingPosition());
				}
				
				if (board.getPiece("horizontalWire") != null)
				{
					// piece is in board
					Piece piece = board.getPiece("horizontalWire");
					piece.setPosition(new Point(791, 166));
					board.removePieceAtIndex(piece.getIndex());
					board.addPieceAtIndex(piece, 6);
					piece.setMovable(false);
				}
				else
				{
					// piece is in bank
					Piece piece = null;
					if (bank.contains(p1))
						piece = bank.get(bank.indexOf(p1));
					else
						return;
					piece.setPosition(new Point(791, 166));
					removePieceAtIndex(bank.indexOf(p1));
					board.addPieceAtIndex(piece, 6);
					piece.setMovable(false);
				}
			}
			else if (board.getPieceAtIndex(7) != p7)
			{
				if (board.getPieceAtIndex(7) != null)
				{
					Piece piece = board.getPieceAtIndex(7);
					board.removePieceAtIndex(7);
					bank.add(piece);
					piece.setPosition(piece.getStartingPosition());
				}
				
				if (board.getPiece("horizontalResistor10") != null)
				{
					// piece is in board
					Piece piece = board.getPiece("resistorHorizontal10");
					piece.setPosition(new Point(932, 166));
					board.removePieceAtIndex(piece.getIndex());
					board.addPieceAtIndex(piece, 7);
					piece.setMovable(false);
				}
				else
				{
					// piece is in bank
					Piece piece = null;
					if (bank.contains(p7))
						piece = bank.get(bank.indexOf(p7));
					else
						return;
					piece.setPosition(new Point(932, 166));
					removePieceAtIndex(bank.indexOf(p7));
					board.addPieceAtIndex(piece, 7);
					piece.setMovable(false);
				}
			}
		}
		else
		{
			if (board.getPieceAtIndex(1) != p1)
			{
				if (board.getPieceAtIndex(1) != null)
				{
					Piece piece = board.getPieceAtIndex(1);
					board.removePieceAtIndex(1);
					bank.add(piece);
					piece.setPosition(piece.getStartingPosition());
				}
				
				if (board.getPiece("horizontalWire") != null)
				{
					// piece is in board
					Piece piece = board.getPiece("horizontalWire");
					piece.setPosition(new Point(791, 25));
					board.removePieceAtIndex(piece.getIndex());
					board.addPieceAtIndex(piece, 1);
					piece.setMovable(false);
				}
				else
				{
					// piece is in bank
					Piece piece = null;
					if (bank.contains(p1))
						piece = bank.get(bank.indexOf(p1));
					else
						return;
					piece.setPosition(new Point(791, 25));
					removePieceAtIndex(bank.indexOf(p1));
					board.addPieceAtIndex(piece, 1);
					piece.setMovable(false);
				}
			}
			else if (board.getPieceAtIndex(3) != p2)
			{
				if (board.getPieceAtIndex(3) != null)
				{
					Piece piece = board.getPieceAtIndex(3);
					board.removePieceAtIndex(3);
					bank.add(piece);
					piece.setPosition(piece.getStartingPosition());
				}
				
				if (board.getPiece("horizontalResistor5") != null)
				{
					// piece is in board
					Piece piece = board.getPiece("horizontalResistor5");
					piece.setPosition(new Point(1073, 25));
					board.removePieceAtIndex(piece.getIndex());
					board.addPieceAtIndex(piece, 3);
					piece.setMovable(false);
				}
				else
				{
					// piece is in bank
					Piece piece = null;
					if (bank.contains(p2))
						piece = bank.get(bank.indexOf(p2));
					else
						return;
					piece.setPosition(new Point(1073, 25));
					removePieceAtIndex(bank.indexOf(p2));
					board.addPieceAtIndex(piece, 3);
					piece.setMovable(false);
				}
			}
			else if (board.getPieceAtIndex(4) != p3)
			{
				if (board.getPieceAtIndex(4) != null)
				{
					Piece piece = board.getPieceAtIndex(4);
					board.removePieceAtIndex(4);
					bank.add(piece);
					piece.setPosition(piece.getStartingPosition());
				}
				
				if (board.getPiece("botLeft") != null)
				{
					// piece is in board
					Piece piece = board.getPiece("botLeft");
					piece.setPosition(new Point(1214, 25));
					board.removePieceAtIndex(piece.getIndex());
					board.addPieceAtIndex(piece, 4);
					piece.setMovable(false);
				}
				else
				{
					// piece is in bank
					Piece piece = null;
					if (bank.contains(p3))
						piece = bank.get(bank.indexOf(p3));
					else
						return;
					piece.setPosition(new Point(1214, 25));
					removePieceAtIndex(bank.indexOf(p3));
					board.addPieceAtIndex(piece, 4);
					piece.setMovable(false);
				}
			}
		}
		
		canHint = false;
		sinceHint = 0;
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
        
        int hour = (int) (time/60);
        int minutes = (int) (time % 60);
        String clock = "";
        if (minutes < 10)
        	clock = Integer.toString(hour) + ":0" + Integer.toString(minutes);
        else 
        	clock = Integer.toString(hour) + ":" + Integer.toString(minutes);
        
        if (lose == false)
        {
	        g.setColor(Color.WHITE);
	        g.setFont(new Font("Courier", Font.PLAIN, 65));
	        g.drawString(clock, 10, 200);
        }
        else
        	if (losePic != null) losePic.draw(g);
        
        if (finished)
        	if (congratulations != null) congratulations.draw(g);
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * @throws IOException
     * */
    public static void main(String[] args) throws IOException {
        FinalProject game = new FinalProject();

		sound.loadSoundEffect("wrong", "resources/wrong.wav");
		sound.loadSoundEffect("right", "resources/right.wav");
		sound.loadSoundEffect("boom", "resources/boom.wav");

        //sound.playMusic("theme");

        background.setPosition(new Point(0,0));
        partBank.setPosition(new Point(185,25));
        board.setPosition(new Point(650,25));
        congratulations.setPosition(new Point(343, 264));
        losePic.setPosition(new Point(442, 211));
                
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
			bomb = new Piece("bomb", "bomb1.png", 5, -5, 0, 0);
			bomb.setPosition(new Point(650, 448));
			bomb.setStartingPosition(new Point(650, 448));
			bomb.setMovable(false);
			board.addPieceAtIndex(bomb, 15);
			battery = new Piece("battery", "battery.png", 5, -5, 0, 0);
			battery.setPosition(new Point(650, 307));
			battery.setStartingPosition(new Point(650, 307));
			battery.setMovable(false);
			board.addPieceAtIndex(battery, 10);
			p15 = new Piece("topRightStuck", "TopRight.png", 1, -5, 0, 0);
			p15.setPosition(new Point(650, 589));
			p15.setStartingPosition(new Point(650, 589));
			board.addPieceAtIndex(p15, 20);
			p15.setMovable(false);

			board.setBatteryIndex(10);
			board.setBombIndex(15);
			board.setFinalResistance(15);
			board.setBatteryVoltage(0);
			
		    p1 = new Piece("horizontalWire", "Horizontal.png", -1, 1, 0, 0);
			p1.setPosition(new Point(185, 25));
			p1.setStartingPosition(new Point(185, 25));
			p1.setMovable(true);
			addPieceAtIndex(p1, 0);
			p2 = new Piece("verticalWire", "Vertical.png", -5, 5, 0, 0);
            p2.setPosition(new Point(331, 25));
            p2.setStartingPosition(new Point(331, 25));
            p2.setMovable(true);
            addPieceAtIndex(p2, 1);
            p3 = new Piece("topLeft", "TopLeft.png", -1, -5, 0, 0);
            p3.setPosition(new Point(477, 25));
            p3.setStartingPosition(new Point(477, 25));
            p3.setMovable(true);
            addPieceAtIndex(p3, 2);
            p4 = new Piece("bottomLeft", "BottomLeft.png", -1, 5, 0, 0);
            p4.setPosition(new Point(185, 171));
            p4.setStartingPosition(new Point(185, 171));
            p4.setMovable(true);
            addPieceAtIndex(p4, 3);
            p5 = new Piece("topRight", "TopRight.png", -5, 1, 0, 0);
            p5.setPosition(new Point(331, 171));
            p5.setStartingPosition(new Point(331, 171));
            p5.setMovable(true);
            addPieceAtIndex(p5, 4);
            p6 = new Piece("bottomRight", "BottomRight.png", 5, 1, 0, 0);
            p6.setPosition(new Point(477, 171));
            p6.setStartingPosition(new Point(477, 171));
            p6.setMovable(true);
            addPieceAtIndex(p6, 5);
            p7 = new Piece("horizontalResistor", "resistorHorizontal5.png", -1, 1, 0, 5);
            p7.setPosition(new Point(185, 317));
            p7.setStartingPosition(new Point(185, 317));
            p7.setMovable(true);
            addPieceAtIndex(p7, 6);
            p8 = new Piece("verticalResistor", "resistorVertical10.png", -5, 5, 0, 10);
            p8.setPosition(new Point(331, 317));
            p8.setStartingPosition(new Point(331, 317));
            p8.setMovable(true);
            addPieceAtIndex(p8, 7);
		}
		else if (level == 2)
		{
			bomb = new Piece("bomb", "bomb2.png", 5, -5, 0, 0);
			bomb.setPosition(new Point(932, 448));
			bomb.setStartingPosition(new Point(932, 448));
			bomb.setMovable(false);
			board.addPieceAtIndex(bomb, 17);
			battery = new Piece("battery", "battery2.png", 5, -5, 0, 0);
			battery.setPosition(new Point(650, 307));
			battery.setStartingPosition(new Point(650, 307));
			battery.setMovable(false);
			board.addPieceAtIndex(battery, 10);
			p15 = new Piece("topRightStuck", "TopRight.png", 1, -5, 0, 0);
			p15.setPosition(new Point(650, 448));
			p15.setStartingPosition(new Point(650, 448));
			board.addPieceAtIndex(p15, 15);
			p15.setMovable(false);
			p14 = new Piece("bottomLeftStuck", "BottomLeft.png", -1, 5, 0, 0);
			p14.setPosition(new Point(932, 307));
			p14.setStartingPosition(new Point(932, 307));
			board.addPieceAtIndex(p14, 12);
			p14.setMovable(false);
			p13 = new Piece("topRightStuck2", "TopRight.png", 1, -5, 0, 0);
			p13.setPosition(new Point(932, 589));
			p13.setStartingPosition(new Point(932, 589));
			board.addPieceAtIndex(p13, 22);
			p13.setMovable(false);

			board.setBatteryIndex(10);
			board.setBombIndex(17);
			board.setFinalResistance(20);
			board.setFinalCurrent(3);
			board.setBatteryVoltage(60);
			
			p1 = new Piece("horizontalWire", "Horizontal.png", -1, 1, 0, 0);
			p1.setPosition(new Point(185, 25));
			p1.setStartingPosition(new Point(185, 25));
			p1.setMovable(true);
			addPieceAtIndex(p1, 0);
			p2 = new Piece("botRight", "BottomRight.png", 5, 1, 0, 0);
			p2.setPosition(new Point(331, 25));
			p2.setStartingPosition(new Point(331, 25));
			p2.setMovable(true);
			addPieceAtIndex(p2, 1);
			p3 = new Piece("botLeft", "BottomLeft.png", 5, -1, 0, 0);
			p3.setPosition(new Point(477, 25));
			p3.setStartingPosition(new Point(477, 25));
			p3.setMovable(true);
			addPieceAtIndex(p3, 2);
			p4 = new Piece("topLeft", "TopLeft.png", -5, -1, 0, 0);
			p4.setPosition(new Point(185, 171));
			p4.setStartingPosition(new Point(185, 171));
			p4.setMovable(true);
			addPieceAtIndex(p4, 3);
			p5 = new Piece("botRight2", "BottomRight.png", 5, 1, 0, 0);
			p5.setPosition(new Point(331, 171));
			p5.setStartingPosition(new Point(331, 171));
			p5.setMovable(true);
			addPieceAtIndex(p5, 4);
			p6 = new Piece("topLeft2", "TopLeft.png", -5, -1, 0, 0);
			p6.setPosition(new Point(477, 171));
			p6.setStartingPosition(new Point(477, 171));
			p6.setMovable(true);
			addPieceAtIndex(p6, 5);
			p7 = new Piece("horizontalResistor", "resistorHorizontal10.png", -1, 1, 0, 10);
			p7.setPosition(new Point(185, 317));
			p7.setStartingPosition(new Point(185, 317));
			p7.setMovable(true);
			addPieceAtIndex(p7, 6);
			p8 = new Piece("verticalResistor", "resistorVertical5.png", -5, 5, 0, 5);
			p8.setPosition(new Point(331, 317));
			p8.setStartingPosition(new Point(331, 317));
			p8.setMovable(true);
			addPieceAtIndex(p8, 7);
			p9 = new Piece("verticalResistor2", "resistorVertical5.png", -5, 5, 0, 5);
			p9.setPosition(new Point(477, 317));
			p9.setStartingPosition(new Point(477, 317));
			p9.setMovable(true);
			addPieceAtIndex(p9, 8);
		}
		else if (level == 3)
		{
			bomb = new Piece("bomb", "bomb3.png", 5, -5, 0, 0);
			bomb.setPosition(new Point(932, 448));
			bomb.setStartingPosition(new Point(932, 448));
			bomb.setMovable(false);
			board.addPieceAtIndex(bomb, 17);
			battery = new Piece("battery", "battery3.png", 5, -5, 0, 0);
			battery.setPosition(new Point(650, 166));
			battery.setStartingPosition(new Point(650, 166));
			battery.setMovable(false);
			board.addPieceAtIndex(battery, 5);
			p15 = new Piece("botRightStuck", "BottomRight.png", 1, 5, 0, 0);
			p15.setPosition(new Point(650, 25));
			p15.setStartingPosition(new Point(650, 25));
			board.addPieceAtIndex(p15, 0);
			p15.setMovable(false);
			p14 = new Piece("topRightStuck", "TopRight.png", -5, 1, 0, 0);
			p14.setPosition(new Point(650, 307));
			p14.setStartingPosition(new Point(650, 307));
			board.addPieceAtIndex(p14, 10);
			p14.setMovable(false);
			p13 = new Piece("verticalStuck", "Vertical.png", 5, -5, 0, 0);
			p13.setPosition(new Point(1214, 166));
			p13.setStartingPosition(new Point(1214, 166));
			board.addPieceAtIndex(p13, 9);
			p13.setMovable(false);
			p12 = new Piece("verticalStuck2", "Vertical.png", 5, -5, 0, 0);
			p12.setPosition(new Point(1214, 307));
			p12.setStartingPosition(new Point(1214, 307));
			board.addPieceAtIndex(p12, 14);
			p12.setMovable(false);
			p11 = new Piece("horizontalResistorStuck", "resistorHorizontal5.png", 1, -1, 0, 5);
			p11.setPosition(new Point(932, 25));
			p11.setStartingPosition(new Point(92, 25));
			board.addPieceAtIndex(p11, 2);
			p11.setMovable(false);
			
			board.setBatteryIndex(5);
			board.setBombIndex(17);
			board.setFinalResistance(25);
			board.setFinalCurrent(4);
			board.setBatteryVoltage(100);
			
			p1 = new Piece("horizontalWire", "Horizontal.png", -1, 1, 0, 0);
			p1.setPosition(new Point(185, 25));
			p1.setStartingPosition(new Point(185, 25));
			p1.setMovable(true);
			addPieceAtIndex(p1, 0);
			p2 = new Piece("horizontalResistor5", "resistorHorizontal5.png", -1, 1, 0, 5);
			p2.setPosition(new Point(331, 25));
			p2.setStartingPosition(new Point(331, 25));
			p2.setMovable(true);
			addPieceAtIndex(p2, 1);
			p3 = new Piece("botLeft", "BottomLeft.png", 5, -1, 0, 0);
			p3.setPosition(new Point(477, 25));
			p3.setStartingPosition(new Point(477, 25));
			p3.setMovable(true);
			addPieceAtIndex(p3, 2);
			p4 = new Piece("verticalResistor5", "resistorVertical5.png", -5, 5, 0, 5);
			p4.setPosition(new Point(185, 171));
			p4.setStartingPosition(new Point(185, 171));
			p4.setMovable(true);
			addPieceAtIndex(p4, 3);
			p5 = new Piece("topLeft", "TopLeft.png", -5, -1, 0, 0);
			p5.setPosition(new Point(331, 171));
			p5.setStartingPosition(new Point(331, 171));
			p5.setMovable(true);
			addPieceAtIndex(p5, 4);
			p6 = new Piece("horizontalResistor10", "resistorHorizontal10.png", -1, 1, 0, 10);
			p6.setPosition(new Point(477, 171));
			p6.setStartingPosition(new Point(477, 171));
			p6.setMovable(true);
			addPieceAtIndex(p6, 5);
			p7 = new Piece("topRight", "TopRight.png", -5, 1, 0, 0);
			p7.setPosition(new Point(185, 317));
			p7.setStartingPosition(new Point(185, 317));
			p7.setMovable(true);
			addPieceAtIndex(p7, 6);
			p8 = new Piece("horizontalWire2", "Horizontal.png", -1, 1, 0, 0);
			p8.setPosition(new Point(331, 317));
			p8.setStartingPosition(new Point(331, 317));
			p8.setMovable(true);
			addPieceAtIndex(p8, 7);
			p9 = new Piece("botLeft2", "BottomLeft.png", 5, -1, 0, 0);
			p9.setPosition(new Point(477, 317));
			p9.setStartingPosition(new Point(477, 317));
			p9.setMovable(true);
			addPieceAtIndex(p9, 8);
		}
	}
}