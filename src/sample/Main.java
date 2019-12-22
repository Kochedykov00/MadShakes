package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;



public class Main extends Application {

    private static Stage stage;







    static int width = 20;
    static int height = 20;
    static int cornersize = 25;






    @Override
    public void start(Stage primaryStage)  {

        stage = primaryStage;
        primaryStage.setTitle("Game");
        setStartScene();
        primaryStage.show();
    }

    public static void setStartScene () {



            Pane startRoot = new Pane();
            Scene startScene = new Scene(startRoot, width * cornersize, height * cornersize);


            //newFood();

            Canvas c = new Canvas(width * cornersize, height * cornersize);

            GraphicsContext gc = c.getGraphicsContext2D();

            Button button = new Button();
            button.setText("START");
            button.setTranslateX((float) width * cornersize / 2);
            button.setTranslateY((float) height * cornersize / 2);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    setGameScene();
                }
            });
            startRoot.getChildren().addAll(button);
            stage.setScene(startScene);
        }



    public static void setGameScene() {

        Pane gameRoot = new Pane();
        Scene gameScene = new Scene(gameRoot, width * cornersize, height * cornersize);


        GameController gameController = new GameController(gameRoot, gameScene);
        gameController.startGame();


        Button but = new Button();
        but.setText("game over");
        but.setTranslateX(width * cornersize -  120);
        but.setTranslateY(0);

        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                gameController.gameOver();
            }
        });
        gameRoot.getChildren().addAll(but);
        stage.setScene(gameScene);
        stage.setTitle("Моя семестровка");
        stage.show();
    }


    public static void setGameOverScene() {
        Pane gameOverRoot = new Pane();
        Scene gameOverScene = new Scene(gameOverRoot, width * cornersize, height * cornersize);

        GameController gameOverController;
        gameOverController = new GameController(gameOverRoot, gameOverScene);
        gameOverController.gameOver();
        gameOverRoot.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        gameOverRoot.setPrefSize(width * cornersize, height * cornersize);
        stage.setScene(gameOverScene);
    }




    public static void main(String[] args) {
        launch(args);
    }
}
