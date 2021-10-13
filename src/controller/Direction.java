package controller;

/**
 * Define the different directions a snake can take
 *
 * @author Adrien Verhaeghe
 */
public enum Direction {
    TOP(0,-1),
    LEFT(-1,0),
    RIGHT(1,0),
    BOT(0,1);

    private int movX;
    private int movY;

    Direction(int x, int y){
        this.movX = x;
        this.movY = y;
    }

    public int getMovX() {
        return movX;
    }

    public int getMovY() {
        return movY;
    }


}
