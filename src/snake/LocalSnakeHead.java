package snake;

import controller.Controller;
import controller.Direction;
import fx.SnakeGame;
import javafx.scene.paint.Color;

/**
 * Define how the snake behave in a local game.
 *
 * @author Adrien Verhaeghe
 */
public class LocalSnakeHead extends SnakeHead {

    public LocalSnakeHead(int x, int y, Color color, Controller controller, Direction defaultDirection, SnakeGame game) {
        super(x, y, color, controller, defaultDirection, game);
        updatePosition();
    }

    /**
     *
     * @return the next X position computed from the current X position and the defined direction (see @nextTurn)
     */
    public int getNextX(){
        return getPositionX() + getCurrentDirection().getMovX();
    }

    /**
     *
     * @return the next Y position computed from the current Y position and the defined direction (see @nextTurn)
     */
    public int getNextY(){
        return getPositionY() + getCurrentDirection().getMovY();
    }

}
