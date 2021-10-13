package fx;

import controller.*;
import fx.SnakeGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

/**
 * launcher defining the options of the game
 *
 * @author Adrien Verhaeghe
 */
public class SnakeMenu extends Application {

    private static int MENU_HEIGHT = 275;
    private static int MENU_WIDTH = 350;
    public static int SERVER_PORT = 6789;
    public static String SERVER = "localhost";


    MediaPlayer mediaPlayer;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Blockade Launcher!");
        primaryStage.setScene(mainScene(primaryStage));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> { Platform.exit(); System.exit(0); } );


        File dir = new File("res");
        File[] files = dir.listFiles();
        Media media = new Media(files[ThreadLocalRandom.current().nextInt(0, files.length)].toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        //mediaPlayer.setAutoPlay(true);
    }

    /**
     * Chose the controllers for a local game
     * from a list of choices
     *
     * @param stage window that can be use by the input graphical elements (e.g. button)
     * @return the scene
     */
    private Scene localSceneSelectController(Stage stage){
        ChoiceBox<String> firstController = new ChoiceBox<>(FXCollections.observableArrayList(
                "RandomController", "KeyboardController", "SmartController", "StupidController", "SuperSmartController")
        );
        // default choice
        firstController.setValue("SmartController");
        ChoiceBox<String> secondController = new ChoiceBox<>(FXCollections.observableArrayList(
                "RandomController", "KeyboardController", "SmartController", "StupidController", "SuperSmartController")
        );
        // default choice
        secondController.setValue("SmartController");

        Button run = new Button();
        run.setText("Run");
        run.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(new Runnable() {
                    // is executed when a user click on "run" button
                    public void run() {
                        new SnakeGame(createControllerFromString(firstController.getValue()), createControllerFromString(secondController.getValue())).start(new Stage());
                    }
                });
            }
        });

        Button back = new Button();
        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        stage.setScene(mainScene(stage));
                    }
                });
            }
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(firstController,1,0);
        grid.add(secondController,1,1);
        grid.add(run,2,2);
        grid.add(back,0,2);
        return new Scene(grid, MENU_WIDTH, MENU_HEIGHT);
    }

    /**
     * Is the main scene which corresponds to the main menu
     *
     * @param stage is the menu window
     * @return the main scene
     */
    private Scene mainScene(Stage stage){
        Button localGame = new Button();
        localGame.setText("Start Local Game");
        localGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        stage.setScene(localSceneSelectController(stage));
                    }
                });
            }
        });

        Button onlineGame = new Button();
        onlineGame.setText("Start Online Game");
        onlineGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(onlineScene(stage));
            }
        });
        Button optionButton = new Button();
        optionButton.setText("Option");
        optionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(sceneOption(stage));
            }
        });
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(localGame,0,0);
        grid.add(onlineGame,0,1);
        grid.add(optionButton,0,2);
        return new Scene(grid, MENU_WIDTH, MENU_HEIGHT);
    }

    /**
     *
     * Is the waiting for connection between the server and the client when the users decide to play online
     *
     * @param stage is the menu window
     * @return the wait for connection scene
     */
    private Scene waitConnection(Stage stage){
        Text waitText = new Text();
        waitText.setText("Wait connection");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(waitText,0,0);
        return new Scene(grid, MENU_WIDTH, MENU_HEIGHT);
    }


    /**
     *
     * It is possible that some feature (as music maybe) are (probably) defined here
     *
     * @param stage is the menu window
     * @return the option scene
     */
    private Scene sceneOption(Stage stage){


        CheckBox music = new CheckBox("Music");
        music.setSelected(!mediaPlayer.isMute());


        music.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (music.isSelected()){
                            mediaPlayer.play();
                        } else {
                            mediaPlayer.pause();
                        }
                    }
                });
            }
        });


        Button back = new Button();
        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        stage.setScene(mainScene(stage));
                    }
                });
            }
        });


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(music,0,0);
        grid.add(back,0,1);
        return new Scene(grid, MENU_WIDTH, MENU_HEIGHT);
    }

    /**
     *
     * Create the online scene. The user can choose between creating a Server or a Client Game
     *
     * @param stage is the menu window
     * @return the online scene
     */
    private Scene onlineScene(Stage stage){
        Button asServer = new Button();
        asServer.setText("Start the server");
        asServer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        stage.setScene(waitConnection(stage));
                        Platform.runLater(new Runnable() {
                            public void run() {
                                try {
                                    // Establish the connection between server and client
                                    ServerSocket listenSocket = new ServerSocket(SnakeMenu.SERVER_PORT);
                                    Socket socket = listenSocket.accept();
                                    new SnakeGameServer(new SuperSmartController(), socket).start(new Stage());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
        });

        Button asClient = new Button();
        asClient.setText("Start the client");
        asClient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(waitConnection(stage));
                Platform.runLater(new Runnable() {
                    public void run() {
                        try {
                            // Establish the connection between server and client
                            Socket socket = new Socket (SnakeMenu.SERVER, SnakeMenu.SERVER_PORT);
                            new SnakeGameClient(new SuperSmartController(), socket).start(new Stage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        Button back = new Button();
        back.setText("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        stage.setScene(mainScene(stage));
                    }
                });
            }
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(asClient,0,0);
        grid.add(asServer,0,1);
        grid.add(back, 0,2);
        return new Scene(grid, MENU_WIDTH, MENU_HEIGHT);
    }

    /**
     *
     * This method creates the corresponding controller from its name
     *
     * @param name the name of the controller which will be created
     * @return the chosen controller
     */
    private Controller createControllerFromString(String name){
        switch (name) {
            case "RandomController":
                return new RandomController();
            case "KeyboardController":
                return new KeyboardController();
            case "SmartController":
                return new SmartController();
            case "StupidController":
                return new StupidController();
            case "SuperSmartController":
                return new SuperSmartController();
            default:
                return new SmartController();
        }
    }
}
