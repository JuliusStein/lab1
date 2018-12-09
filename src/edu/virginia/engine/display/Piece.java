package edu.virginia.engine.display;

import java.awt.Point;

public class Piece extends DisplayObject {

    //start and end need to be set according to (-1 = left, 1 = right, -5 = up, 5 = down)
    private int start;
    private int end;
    private int index;
    private boolean hasVoltage;
    private boolean hasResistance;
    private int voltage;
    private int resistance;
    private Point startingPosition;
    private boolean movable;

    public Piece(String id) {
        super(id);
        // TODO Auto-generated constructor stub
    }

    public Piece(String id, String filename) {
        super(id, filename);
        // TODO Auto-generated constructor stub
    }

    public Piece(int start, int end)
    {
        super("");
        this.start = start;
        this.end = end;
        this.hasVoltage = false;
        this.hasResistance = false;
    }

    public Piece(int start, int end, boolean volt, boolean res)
    {
        super("");
        this.start = start;
        this.end = end;
        this.hasVoltage = volt;
        this.hasResistance = res;
    }

    public Piece(String id, String filename, int start, int end, int volt, int res)
    {
        super(id, filename);
        this.start = start;
        this.end = end;
        if(voltage!=0)
            this.hasVoltage = true;
        else
            this.hasVoltage = false;
        if(resistance!=0)
            this.hasResistance = true;
        else
            this.hasResistance = false;
        this.voltage = volt;
        this.resistance = res;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public boolean isHasVoltage() {
        return hasVoltage;
    }

    public void setHasVoltage(boolean hasVoltage) {
        this.hasVoltage = hasVoltage;
    }

    public boolean isHasResistance() {
        return hasResistance;
    }

    public void setHasResistance(boolean hasResistance) {
        this.hasResistance = hasResistance;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

	public Point getStartingPosition() {
		return startingPosition;
	}

	public void setStartingPosition(Point startingPosition) {
		this.startingPosition = startingPosition;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}
}

