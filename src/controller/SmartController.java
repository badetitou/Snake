package controller;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * Define a strategy that avoid the snake to die the next turn
 *
 * @author Adrien Verhaeghe
 */
public class SmartController extends Controller {

    /**
     *
     * @return a direction in which the snake will not die the next turn (except if there is no other choice)
     */
    @Override
    public Direction getDirection() {
        ArrayList<Direction> options = new ArrayList<>();
        if(!(getSnake().getInGame().snakeShouldDie(getSnake().getPositionX()+ Direction.RIGHT.getMovX(), getSnake().getPositionY()+ Direction.RIGHT.getMovY()))){
            options.add(Direction.RIGHT);
        }
        if(!(getSnake().getInGame().snakeShouldDie(getSnake().getPositionX()+ Direction.LEFT.getMovX(), getSnake().getPositionY()+ Direction.LEFT.getMovY()))){
            options.add(Direction.LEFT);
        }
        if(!(getSnake().getInGame().snakeShouldDie(getSnake().getPositionX()+ Direction.TOP.getMovX(), getSnake().getPositionY()+ Direction.TOP.getMovY()))){
            options.add(Direction.TOP);
        }
        if(!(getSnake().getInGame().snakeShouldDie(getSnake().getPositionX()+ Direction.BOT.getMovX(), getSnake().getPositionY()+ Direction.BOT.getMovY()))){
            options.add(Direction.BOT);
        }

        if(options.isEmpty()){
            return Direction.LEFT;
        } else {
            return options.get(ThreadLocalRandom.current().nextInt(0, options.size()));
        }
    }

}
