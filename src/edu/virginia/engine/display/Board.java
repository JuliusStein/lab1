package edu.virginia.engine.display;

public class Board extends DisplayObject {
    private Piece[] pieces  = new Piece[25];
    private boolean[][] taken = new boolean[5][5];
    private int batteryIndex;
    private int bombIndex;
    private int batteryVoltage;
    private boolean hasParallel = false;
    private int finalVoltage;
    private int finalResistance;
    private int finalCurrent;


    public Board(String id) {
        super(id);
        for(int i=0; i<5; i++) {
            for (int j = 0; j < 5; j++) {
                this.taken[i][j] = false;
            }
        }
        for(int j=0; j<25; j++){
            pieces[j] = null;
        }

    }

    public Board(String id, String filename) {
        super(id, filename);
        for(int i=0; i<5; i++) {
            for (int j = 0; j < 5; j++) {
                this.taken[i][j] = false;
            }
        }
        for(int j=0; j<25; j++){
            pieces[j] = null;
        }
    }

    public void addPiece(Piece newPiece){
        this.pieces[pieces.length] = newPiece;
    }

    public void addPieceAtIndex(Piece newPiece, int index){
        newPiece.setIndex(index);
        if((index<this.pieces.length)&&(index >= 0)){
            this.pieces[index] = newPiece;
            taken[index%5][index/5] = true;
        }else{
            addPiece(newPiece);
        }
    }

    public Piece removePieceAtIndex(int index){
        Piece toReturn = this.pieces[index];
        this.pieces[index] = null;
        taken[index%5][index/5] = false;
        return toReturn;
    }

    public void removeAllPieces(){
        for(int i=0; i<this.pieces.length; i++){
            this.pieces[i] = null;
        }
    }

    public boolean contains(Piece piece){
        for(int i=0; i<this.pieces.length; i++){
            if (this.pieces[i].getId() == piece.getId())
                return true;
        }
        return false;
    }



    public Piece getPiece(String id){
        for(int i=0; i<this.pieces.length; i++){
        	if (this.pieces[i] != null)
	            if (this.pieces[i].getId() == id)
	                return this.pieces[i];
        }
        return null;
    }

    public Piece getPieceAtIndex(int index){
        return pieces[index];
    }

    public boolean contains(String id){
        for(int i=0; i<this.pieces.length; i++){
            if(getPieceAtIndex(i).getId() == id){
                return true;
            }
        }
        return false;
    }

    public Piece[] getPieces() {
        return this.pieces;
    }

    public void setPieces(Piece[] pieces) {
        this.pieces = pieces;
    }

    public int getBatteryIndex() {
        return batteryIndex;
    }

    public void setBatteryIndex(int batteryIndex) {
        this.batteryIndex = batteryIndex;
    }

    public int getBombIndex() {
        return bombIndex;
    }

    public void setBombIndex(int bombIndex) {
        this.bombIndex = bombIndex;
    }

    public int getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(int batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public int getFinalVoltage() {
        return finalVoltage;
    }

    public void setFinalVoltage(int finalVoltage) {
        this.finalVoltage = finalVoltage;
    }

    public int getFinalResistance() {
        return finalResistance;
    }

    public void setFinalResistance(int finalResistance) {
        this.finalResistance = finalResistance;
    }

    public int getFinalCurrent() {
        return finalCurrent;
    }

    public void setFinalCurrent(int finalCurrent) {
        this.finalCurrent = finalCurrent;
    }

    public boolean hasParallel() {
        return hasParallel;
    }

    public void setHasParallel(boolean hasParallel) {
        this.hasParallel = hasParallel;
    }

    public boolean hasCorrectResistance(){
        Piece start = getPieceAtIndex(this.batteryIndex-5);
        int netResistance = 0;
        int previousEnd = -5;

        if(start == null){
            return false;
        }

        for(Piece p = start; p.getIndex() !=  batteryIndex; p = getPieceAtIndex(p.getIndex() + p.getEnd())){
        	if(p.getStart() != (-1*previousEnd)){
        		int t = p.getEnd();
        		p.setEnd(p.getStart());
        		p.setStart(t);
        	}
        	if(p.isHasResistance()){
                netResistance+=p.getResistance();
            }
        	if(getPieceAtIndex(p.getIndex() + p.getEnd()) == null){
        		return false;
        	}else{
        		previousEnd = p.getEnd();
        	}
        }
        
        if(netResistance == getFinalResistance()){
            return true;
        }else{
            return false;
        }
    }

    public boolean hasCorrectVoltage(){
        return false;
    }

    public boolean hasCorrectCurrent(){
    	Piece start = getPieceAtIndex(this.batteryIndex-5);
        int netResistance = 0;
        int previousEnd = -5;

        if(start == null){
            return false;
        }

        for(Piece p = start; p.getIndex() !=  batteryIndex; p = getPieceAtIndex(p.getIndex() + p.getEnd())){
        	if(p.getStart() != (-1*previousEnd)){
        		int t = p.getEnd();
        		p.setEnd(p.getStart());
        		p.setStart(t);
        	}
        	if(p.isHasResistance()){
                netResistance+=p.getResistance();
            }
        	if(getPieceAtIndex(p.getIndex() + p.getEnd()) == null){
        		return false;
        	}else{
        		previousEnd = p.getEnd();
        	}
        }
        
        if((batteryVoltage / netResistance) == getFinalCurrent()){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean[][] getTaken()
    {
    	return this.taken;
    }

}
