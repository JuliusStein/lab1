package edu.virginia.engine.display;

import java.util.ArrayList;

public class Board extends DisplayObject {

    private boolean hasParallel;
    private ArrayList<Piece> path;
    private boolean[][] taken = new boolean[5][5];


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
}
