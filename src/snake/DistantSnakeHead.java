package snake;

import controller.Direction;
import fx.Game;
import fx.SnakeGame;
import javafx.scene.paint.Color;

import java.io.*;

/**
 * Define a SnakeHead that asks through the socket the features of the snake.
 *
 * @author Adrien Verhaeghe
 *
 */

public class DistantSnakeHead extends SnakeHead {

    BufferedReader in;
    BufferedWriter out;

    /**
     * define a snake that will communicate through the socket to a HeadSnake coupled with a controller
     *
     * @param x
     * @param y
     * @param color the color of the snake in the local game
     * @param in Buffer to communicate to the socket
     * @param out Buffer to communicate to the socket
     * @param defaultDirection Is the first direction the snake will take when the game begin
     * @param game the game in which the snake takes place
     *
     * The controller is define by the SnakeHead with whom the DistantSnakeHead communicate
     */
    public DistantSnakeHead(int x, int y, Color color, BufferedReader in, BufferedWriter out, Direction defaultDirection, SnakeGame game) {
        super(x, y, color);
        this.in = in;
        this.out = out;
        this.currentDirection = defaultDirection;
        inGame = game;
        setArcWidth(Game.ELEMENT_SIZE);
        setArcHeight(Game.ELEMENT_SIZE);
    }

    /**
     * Ask the other snake to do its next turn
     */
    public void nextTurn(){
        try {
            out.write("nextTurn\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * Ask the other snake its nextX value
     */
    public int getNextX() {

        try {
            out.write("nx\n");
            out.flush();
            return Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    /**
     * Ask the other snake its nextY value
     */
    public int getNextY() {
        try {
            out.write("ny\n");
            out.flush();
            return Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
