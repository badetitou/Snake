package controller;

/**
 * Is a testing controller which force a snake to go forward
 *
 * @author Adrien Verhaeghe
 */
public class StupidController extends Controller {

    /**
     *
     * @return the previous direction (so... the snake never turns and will inevitably die in atrocious suffering)
     */
    @Override
    public Direction getDirection() {
        return getSnake().getCurrentDirection();
    }
}
