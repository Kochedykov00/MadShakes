package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


import java.io.IOException;
import java.net.Socket;




public class Main extends Application {

    private static Stage stage;


    static int width = 20;
    static int height = 20;
    static int cornersize = 25;


    @Override
    public void start(Stage primaryStage) throws IOException {

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
        stage = primaryStage;
        primaryStage.setTitle("Game");
        setStartScene();
        primaryStage.show();
    }

    public static void setStartScene() {


        Pane startRoot = new Pane();
        Scene startScene = new Scene(startRoot, width * cornersize, height * cornersize);

        Canvas c = new Canvas(width * cornersize, height * cornersize);

        GraphicsContext gc = c.getGraphicsContext2D();

        TextField ipTextField = new TextField();
        ipTextField.setText("192.168.0.101");
        ipTextField.setTranslateX((float) (width * cornersize / 2) - 45);
        ipTextField.setTranslateY((float) (height * cornersize / 2) + 45);

        startRoot.getChildren().addAll(ipTextField);


        Button button = new Button();
        button.setText("START");
        button.setTranslateX((float) width * cornersize / 2);
        button.setTranslateY((float) height * cornersize / 2);

        System.out.println(ipTextField.getText());

        button.setOnAction(event -> {
            try {
                setGameScene(ipTextField.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        startRoot.getChildren().addAll(button);
        stage.setScene(startScene);
    }


    public static void setGameScene(String ip) throws IOException {

        Pane gameRoot = new Pane();
        Scene gameScene = new Scene(gameRoot, width * cornersize, height * cornersize);

        Food food = new Food();

        Client client = new Client(ip);

        Socket socket = client.connectingWithServer();
        System.out.println(client.getId());

        Snake snake = new Snake(width/2 , height / 2);
        Snake snake1 = new Snake(15, 15);



        //DataOutputStream dataOutputStream = socket.getOutputStream();
        //dataOutputStream.write(1);


        GameController gameController = new GameController(gameRoot, gameScene, food, socket, client.getId(), snake, snake1);
        gameController.startGame();


        Button but = new Button();
        but.setText("game over");
        but.setTranslateX(width * cornersize - 120);
        but.setTranslateY(0);

        but.setOnAction(event -> {
                setGameOverScene();


        });
        gameRoot.getChildren().addAll(but);
        stage.setScene(gameScene);
        stage.setTitle("Моя семестровка");
        stage.show();
    }


    public static void setGameOverScene() {
        Pane gameOverRoot = new Pane();
        Scene gameOverScene = new Scene(gameOverRoot, width * cornersize, height * cornersize);
        //GameController gameController = new GameController(gameOverRoot, gameOverScene);
        //gameController.gameOver();

        Button but = new Button();
        but.setText("begin again ");
        but.setTranslateX(width * cornersize / 2);
        but.setTranslateY(width * cornersize / 2);

        but.setOnAction(event -> {

        });


        GameController gameOverController;
        gameOverController = new GameController(gameOverRoot, gameOverScene);
        gameOverController.gameOver();
        gameOverRoot.setPrefSize(width * cornersize, height * cornersize);
        gameOverRoot.getChildren().addAll(but);
        stage.setScene(gameOverScene);
        stage.setTitle("Моя семестровка");
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}



