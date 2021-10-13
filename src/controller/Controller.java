package controller;

import snake.SnakeHead;

/**
 * Define the methods that must be defined to create a Controller
 *
 * @author Adrien Verhaeghe
 */
public abstract class Controller {

    private SnakeHead snake;

    public void setSnake(SnakeHead snake) {
        this.snake = snake;
    }

    public SnakeHead getSnake() {
        return snake;
    }

    /**
     *
     * @return the next direction of the snake
     */
    public abstract Direction getDirection();
}
