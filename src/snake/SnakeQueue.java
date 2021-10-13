package snake;

import javafx.scene.paint.Color;
import snake.SnakeElement;

/**
 * Define the graphical representation of a snake queue
 *
 * @author Adrien Verhaeghe
 */
public class SnakeQueue extends SnakeElement {

    public SnakeQueue(int x, int y, Color color)
    {
        super(x, y, color);
        setArcWidth(0);
        setArcHeight(0);
    }
}
