package fx;

import controller.Controller;
import controller.Direction;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import snake.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * Create a server game
 *
 * @author Adrien Verhaeghe
 */
public class SnakeGameServer extends SnakeGame {

    BufferedWriter outputWriter;
    BufferedReader in;

    /**
     *
     * @param controller1 the controller of the "server player"
     * @param socket the socket used to communicate with the client
     */
    public SnakeGameServer(Controller controller1, Socket socket) {
        super();
        this.allNodes = new ArrayList<>();
        snake1 = new LocalSnakeHead(6, 6, Color.AQUAMARINE, controller1, Direction.RIGHT, this);
        this.allNodes.add(new SnakeQueue(5, 6, Color.AQUAMARINE));
        try {
            outputWriter = new BufferedWriter(new PrintWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            snake2 = new DistantSnakeHead(18, 18, Color.ORANGE,
                    in,
                    outputWriter, Direction.LEFT, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.allNodes.add(new SnakeQueue(19, 18, Color.ORANGE));
        this.allNodes.add(snake1);
        this.allNodes.add(snake2);
    }

    /**
     * Perform one step of the game
     *
     * @return the position of all the node on the game board for a step
     */
    public ArrayList<Node> gameStep()
    {
        // Choose the next positions for the snakes
        snake1.nextTurn();
        snake2.nextTurn();

        // Look after a dead snake (if there is at least one dead snake.. The turn will not be performed)
        boolean end = snake1.isDead() || snake2.isDead();
        if (!end) {
            // Execute the turn of the distant snake
            executeTurn(snake2);

            try {
                // say to the client that it can play
                outputWriter.write("endTurn\n");
                outputWriter.flush();

                String strin = "";
                // Answer the client request
                // The client must send "endClient" when it finishes its turn
                while (!(strin = in.readLine()).equals("endClient")) {
                    switch (strin) {
                        case "x":
                            outputWriter.write(snake1.getPositionX() + "\n");
                            outputWriter.flush();
                            break;
                        case "y":
                            outputWriter.write(snake1.getPositionY() + "\n");
                            outputWriter.flush();
                            break;
                        case "nx":
                            outputWriter.write(snake1.getNextX() + "\n");
                            outputWriter.flush();
                            break;
                        case "ny":
                            outputWriter.write(snake1.getNextY() + "\n");
                            outputWriter.flush();
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Execute the turn of the snake 1 locally (must be done of after answering the client to avoid time shift)
            executeTurn(snake1);
        }
        return allNodes;
    }

}
