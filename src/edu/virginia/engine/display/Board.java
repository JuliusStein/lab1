package edu.virginia.engine.display;

import java.util.ArrayList;

public class Board extends DisplayObject {
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
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
        for(int i=0; i<5; i++)
            for(int j=0; j<5; j++)
                this.taken[i][j] = false;
        // TODO Auto-generated constructor stub
    }

    public Board(String id, String filename) {
        super(id, filename);
        for(int i=0; i<5; i++)
            for(int j=0; j<5; j++)
                this.taken[i][j] = false;
        // TODO Auto-generated constructor stub
    }

    public void addPiece(Piece newPiece){
        this.pieces.add(newPiece);
    }

    public void addPieceAtIndex(Piece newPiece, int index){
        newPiece.setIndex(index);
        if((index<this.pieces.size())&&(index >= 0)){
            this.pieces.add(index, newPiece);
            taken[index/5][index%5] = true;
        }else{
            this.pieces.add(newPiece);
        }
    }

    public void removePiece(Piece piece){
        this.pieces.remove(piece);
    }

    public void removePieceAtIndex(int index){
        this.pieces.remove(index);
        taken[index/5][index%5] = false;
    }

    public void removeAllPieces(){
        this.pieces.clear();
    }

    public boolean contains(Piece piece){
        return this.pieces.contains(piece);
    }



    public Piece getPiece(String id){
        for(int i=0; i<this.pieces.size(); i++){
            if(getPieceAtIndex(i).getId() == id){
                return getPieceAtIndex(i);
            }
        }
        return null;
    }

    public Piece getPieceAtIndex(int index){
        return this.pieces.get(index);
    }

    public boolean contains(String id){
        for(int i=0; i<this.pieces.size(); i++){
            if(getPieceAtIndex(i).getId() == id){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
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

        for(Piece p = start; p.getIndex() !=  bombIndex; p = getPieceAtIndex(p.getIndex() + p.getEnd())){
            if(p.isHasResistance()){
                netResistance+=p.getResistance();
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
        return false;
    }

}
