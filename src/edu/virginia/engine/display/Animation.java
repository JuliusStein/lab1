package edu.virginia.engine.display;

public class Animation {
    private String id;
    private int startFrame;
    private int endFrame;


    public Animation(String ID, int start, int end){
        this.id = ID;
        this.startFrame = start;
        this.endFrame = end;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStartFrame(int startFrame) {
        this.startFrame = startFrame;
    }

    public void setEndFrame(int endFrame) {
        this.endFrame = endFrame;
    }

    public String getId() {
        return id;
    }

    public int getStartFrame() {
        return startFrame;
    }

    public int getEndFrame() {
        return endFrame;
    }




}
