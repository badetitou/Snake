package snake;

import fx.Game;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;

/**
 * Define graphical customization applied to all Snake part
 *
 * @author Adrien Verhaeghe
 */
public class SnakeElement extends Rectangle {

    private int positionX, positionY;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public SnakeElement(int x, int y, Color color)
    {
        super(Game.ELEMENT_SIZE, Game.ELEMENT_SIZE, color);
        setPositionX(x);
        setPositionY(y);
        setX(x* Game.ELEMENT_SIZE);
        setY(y* Game.ELEMENT_SIZE);

        // a few unnecessary esthetic calls
        setArcWidth(Game.ELEMENT_SIZE/2);
        setArcHeight(Game.ELEMENT_SIZE/2);
        setStroke(Color.BLACK);
        setStrokeWidth(3.0);
        setStrokeLineCap(StrokeLineCap.ROUND);
    }
}
