package controller;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * Define a random selection of the direction
 *
 * @author Adrien Verhaeghe
 */
public class RandomController extends Controller {

    /**
     *
     * @return a random next direction
     */
    @Override
    public Direction getDirection() {
        switch (ThreadLocalRandom.current().nextInt(0, 3)) {
            case 0: return Direction.TOP;
            case 1: return  Direction.RIGHT;
            case 2: return  Direction.LEFT;
            case 3: return  Direction.BOT;
            default: return getSnake().getCurrentDirection();
        }
    }
}
