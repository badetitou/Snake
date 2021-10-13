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
 * creat a client game
 *
 * @author Adrien Verhaeghe
 *
 */

public class SnakeGameClient extends SnakeGame {

    BufferedWriter outputWriter;
    BufferedReader in;

    /**
     *
     * @param controller1 the controller of the "client player"
     * @param socket the socket used to communicate with the server
     */
    public SnakeGameClient(Controller controller1, Socket socket) {
        super();
        this.allNodes = new ArrayList<>();
        snake1 = new LocalSnakeHead(18, 18, Color.AQUAMARINE, controller1, Direction.LEFT, this);
        this.allNodes.add(new SnakeQueue(19, 18, Color.AQUAMARINE));
        try {
            outputWriter = new BufferedWriter(new PrintWriter(socket.getOutputStream()));
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            snake2 = new DistantSnakeHead(6, 6, Color.ORANGE,
                    new BufferedReader(new InputStreamReader(socket.getInputStream())),
                    outputWriter, Direction.RIGHT, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.allNodes .add(new SnakeQueue(5, 6, Color.ORANGE));
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
        // Answer the server request
        String strin = "";
        try {
            while (!(strin = in.readLine()).equals("endTurn")) {
                switch (strin) {
                    case "nextTurn":
                        snake1.nextTurn();
                        break;
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
            // Execute the turn of the snake 1 locally (must be done of after answering the client to avoid time shift)
            executeTurn(snake1);
            executeTurn(snake2);
            outputWriter.write("endClient\n");
            outputWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
        }

        return allNodes;
    }
}
