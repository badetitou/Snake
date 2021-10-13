package controller;

/**
 * @author Adrien Verhaeghe
 */
public class SuperSmartController extends Controller {

    @Override
    /**
     * Select the direction which as the biggest number of case
     * available before an obstacle
     * @return the direction with the more space
     */
    public Direction getDirection() {
        int[] options = {0,0,0,0};
        int X = getSnake().getPositionX();
        int Y = getSnake().getPositionY();
        
        //Find the number of case available if the snake goes right
        while (!(getSnake().getInGame().snakeShouldDie(X+Direction.RIGHT.getMovX(), Y+Direction.RIGHT.getMovY()))){
            options[0] = options[0]+1;
            X = X+Direction.RIGHT.getMovX();
            Y = Y+Direction.RIGHT.getMovY();
        }
        
        X = getSnake().getPositionX();
        Y = getSnake().getPositionY();
      //Find the number of case available if the snake goes left
        while (!(getSnake().getInGame().snakeShouldDie(X+Direction.LEFT.getMovX(), Y+Direction.LEFT.getMovY()))){
            options[1] = options[1]+1;
            X = X+Direction.LEFT.getMovX();
            Y = Y+Direction.LEFT.getMovY();
        }
        
        X = getSnake().getPositionX();
        Y = getSnake().getPositionY();
        
      //Find the number of case available if the snake goes up
        while (!(getSnake().getInGame().snakeShouldDie(X+Direction.TOP.getMovX(), Y+Direction.TOP.getMovY()))){
            options[2] = options[2]+1;
            X = X+Direction.TOP.getMovX();
            Y = Y+Direction.TOP.getMovY();
        }
        
        X = getSnake().getPositionX();
        Y = getSnake().getPositionY();
        
      //Find the number of case available if the snake goes down
        while (!(getSnake().getInGame().snakeShouldDie(X+Direction.BOT.getMovX(), Y+Direction.BOT.getMovY()))){
            options[3] = options[3]+1;
            X = X+Direction.BOT.getMovX();
            Y = Y+Direction.BOT.getMovY();
        }
        
        
        //give the optimal direction
        int indiceMax = 0;
        int max = options[0];
        
        for (int i =1 ; i <4 ; i++) {
        	if (options[i] > max) {
        		indiceMax = i;
        		max = options[indiceMax];
        	}
        }

        if(max == 0){
            return Direction.LEFT;
        }
        if(indiceMax == 0) {
        	return Direction.RIGHT;
        }
        if(indiceMax == 1) {
        	return Direction.LEFT;
        }
        if(indiceMax == 2) {
        	return Direction.TOP;
        }
        else {
        	return Direction.BOT;
        }
    }
}