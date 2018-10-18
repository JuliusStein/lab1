package edu.virginia.engine.display;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class DisplayObjectContainer extends DisplayObject{

    private ArrayList<DisplayObject> children;
    private String id;


    public DisplayObjectContainer(String ID){
        super(ID);
        this.children = null;
    }

    public DisplayObjectContainer(String ID, String fileName){
        super(ID, fileName);
        this.children = null;
    }

    public void addChild(DisplayObject child){
        this.children.add(child);
    }

    public void addChildAtIndex(DisplayObject child, int index){
        if((index<this.children.size())&&(index >= 0)){
            this.children.add(index, child);
        }else{
            this.children.add(child);
        }
    }

    public void removeChild(DisplayObject child){
        this.children.remove(child);
    }

    public void removeChildAtIndex(int index){
        this.children.remove(index);
    }

    public void removeAllChildren(){
        this.children.clear();
    }

    public boolean contains(DisplayObject child){
        return this.children.contains(child);
    }

    public boolean contains(String id){
        for(int i=0; i<this.children.size(); i++){
            if(getChildAtIndex(i).getId() == id){
                return true;
            }
        }
        return false;
    }

    public DisplayObject getChild(String id){
        for(int i=0; i<this.children.size(); i++){
            if(getChildAtIndex(i).getId() == id){
                return getChildAtIndex(i);
            }
        }
        return null;
    }

    public DisplayObject getChildAtIndex(int index){
        return this.children.get(index);
    }

    public ArrayList<DisplayObject> getChildren() {
        return children;
    }

    @Override
    public void draw(Graphics g){
        super.draw(g);

        Graphics2D g2d = (Graphics2D) g;
        applyTransformations(g2d);

        for(int i=0; i<this.children.size(); i++){
            getChildAtIndex(i).draw(g);
        }

        reverseTransformations(g2d);
    }

    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

        for(int i=0; i<this.children.size(); i++){
            getChildAtIndex(i).update(pressedKeys);
        }
    }

    public static void main(String[] args) throws IOException {

    }
}
