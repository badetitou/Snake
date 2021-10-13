package controller;

import fx.Keyboard;

/**
 * Define how to choose a direction from the user keyboard
 *
 * @author Adrien Verhaeghe
 */
public class KeyboardController extends Controller {

    /**
     *
     * @return define the keyboard control (which basically corresponds to the usage of the keyboard arrows to control the snake)
     */
    @Override
    public Direction getDirection() {
        switch (Keyboard.getLastKeyCode()) {
            case LEFT:  return Direction.LEFT;
            case UP:  return Direction.TOP;
            case RIGHT: return Direction.RIGHT;
            case DOWN: return Direction.BOT;
            default: return getSnake().getCurrentDirection();
        }
    }
}
