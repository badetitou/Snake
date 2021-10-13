package snake;

import javafx.scene.paint.Color;
import snake.SnakeElement;

/**
 * Define the representation of a part of the snake body (which is basically an element)
 *
 * @author Adrien Verhaeghe
 */
public class SnakeBody extends SnakeElement {

    public SnakeBody(int x, int y, Color color)
    {
        super(x, y, color);
    }

}
