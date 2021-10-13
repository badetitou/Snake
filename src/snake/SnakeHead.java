package snake;

import controller.Controller;
import controller.Direction;
import fx.Game;
import fx.SnakeGame;
import javafx.scene.paint.Color;

/**
 *
 * Define the main feature of a Snake which *MUST* be fulfilled to be used in a game
 *
 * @author Adrien Verhaeghe
 */
public abstract class SnakeHead extends SnakeElement {

    private Controller controller;
    protected Direction currentDirection;
    private boolean isDead = false;
    protected SnakeGame inGame;

    public SnakeGame getInGame() {
        return inGame;
    }

    public Controller getController() {
        return controller;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }


    /**
     * Can be used to create a Snake without controller (in the case of an online game)
     *
     * @param x
     * @param y
     * @param color the color of the snake
     */
    protected SnakeHead(int x, int y, Color color) {
        super(x,y,color);
    }

    /**
     * Can be used to create a snake with a controller
     *
     * @param x
     * @param y
     * @param color the color of the snake
     * @param controller how the user can control the snake
     * @param defaultDirection Is the first direction the snake will take when the game begin
     * @param game the game in which the snake takes place
     */
    public SnakeHead(int x, int y, Color color, Controller controller, Direction defaultDirection, SnakeGame game) {
        super(x, y, color);
        this.controller = controller;
        controller.setSnake(this);
        this.currentDirection = defaultDirection;
        inGame = game;
        setArcWidth(Game.ELEMENT_SIZE);
        setArcHeight(Game.ELEMENT_SIZE);
    }

    /**
     * kill the snake (and define the dead color)
     */
    public void die(){
        isDead = true;
        setFill(Color.BLACK);
    }

    public boolean isDead() {
        return isDead;
    }

    /**
     * define a direction for a turn (a step of the game)
     */
    public void nextTurn(){
        setCurrentDirection(getController().getDirection());
    }

    /**
     *
     * @return the next X position
     */
    public abstract int getNextX();


    /**
     *
     * @return the next Y position
     */
    public abstract int getNextY();


    /**
     * move the snake to the next position
     */
    public void move() {
        setPositionX(getNextX());
        setPositionY(getNextY());
        updatePosition();
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    /**
     * define the position of the graphical representation of the snake from its board position in the game
     */
    public void updatePosition(){
        setX(getPositionX()* Game.ELEMENT_SIZE);
        setY(getPositionY()* Game.ELEMENT_SIZE);
    }
}
