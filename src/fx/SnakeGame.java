package fx;

import java.util.ArrayList;

import controller.Controller;
import controller.Direction;
import javafx.application.Application;
import javafx.scene.paint.Color;
import snake.LocalSnakeHead;
import snake.SnakeBody;
import snake.SnakeElement;
import snake.SnakeHead;
import snake.SnakeQueue;

/**
 *
 * Create a game from the controllers defined in the Launcher window and
 * run the game step by step
 *
 * @author Adrien Verhaeghe
 */
public class SnakeGame extends Game {
	protected ArrayList<Node> allNodes;
    protected SnakeHead snake1;
    protected SnakeHead snake2;

    /**
     * Create the snakes with their controllers
     *
     * @param controller1
     * @param controller2
     */
    public SnakeGame(Controller controller1, Controller controller2)
    {
        this.allNodes = new ArrayList<>();
        snake1 = new LocalSnakeHead(6, 6, Color.AQUAMARINE, controller1, Direction.RIGHT, this);
        this.allNodes.add(new SnakeQueue(5, 6, Color.AQUAMARINE));
        snake2 = new LocalSnakeHead(18, 18, Color.ORANGE, controller2, Direction.LEFT, this);
        this.allNodes.add(new SnakeQueue(19, 18, Color.ORANGE));
        this.allNodes.add(snake1);
        this.allNodes.add(snake2);
    }

    /**
     * Default constructor for the Client and Server game
     */
    public SnakeGame() {}


    /** Application main method	 */
	public static void main(String[] args) { Application.launch(args); }

    /**
     * Perform one step of the game
     *
     * @return the position of all the node on the game board for a step
     */
	public ArrayList<Node> gameStep()
	{
	    // Choose the next positions for the snakes
        snake1.nextTurn();
        snake2.nextTurn();


        // Look after a dead snake (if there is at least one dead snake.. The turn will not be performed)
	    boolean end = snake1.isDead() || snake2.isDead();
        if (!end) {
            // see @executeTurn
            executeTurn(snake1);
            executeTurn(snake2);
        }
		return allNodes;
	}

    /**
     * Execute the turn of a snake
     *
     * @param snake the snake which moves
     */
	protected void executeTurn(SnakeHead snake){
        if (snakeShouldDie(snake.getNextX(), snake.getNextY()))
            snake.die();
        else {
            // Add a body block at the previous position
            allNodes.add(new SnakeBody(snake.getPositionX(), snake.getPositionY(), (Color) snake.getFill()));
            // Move to the next position
            snake.move();
        }
    }

    /**
     * Check if a snake die if it is at a position x,y
     *
     * @param x abscissa
     * @param y ordered
     * @return if the snake should die
     */
	public boolean snakeShouldDie(int x, int y){
        return isElementAt(x, y) || isOutOfBoard(x, y);
    }

    /**
     * Check that the abscissa and the ordered are inside the board
     *
     * @param x abscissa
     * @param y ordered
     * @return true if the coordinates are out of the board. Else return false
     */
    private boolean isOutOfBoard(int x, int y){
	    return x < 0 || x >= Game.GRID_SIZE || y < 0 || y >= Game.GRID_SIZE;
    }

    /**
     * Check if there is already an element at the coordinates x,y
     *
     * @param x abscissa
     * @param y ordered
     * @return true if the coordinates are already filled in the board. Else return false
     */
	private boolean isElementAt(double x, double y){
	    for(Node node : allNodes){
            if (((SnakeElement) node).getPositionX() == x && ((SnakeElement) node).getPositionY() == y)
                return true;
        }
        return false;
    }
}
