package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;

import static sample.Food.*;


public class Main extends Application {

    private static Stage stage;


    static int width = 20;
    static int height = 20;
    static int cornersize = 25;


    @Override
    public void start(Stage primaryStage) {

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

        Food food = new Food();




        GameController gameController = new GameController(gameRoot, gameScene, food);
        gameController.startGame();


        Button but = new Button();
        but.setText("game over");
        but.setTranslateX(width * cornersize - 120);
        but.setTranslateY(0);

        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                setGameOverScene();
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
        //GameController gameController = new GameController(gameOverRoot, gameOverScene);
        //gameController.gameOver();

        Button but = new Button();
        but.setText("begin again ");
        but.setTranslateX(width * cornersize / 2);
        but.setTranslateY(width * cornersize / 2);

        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setStartScene();

            }
        });


        GameController gameOverController;
        gameOverController = new GameController(gameOverRoot, gameOverScene);
        gameOverController.gameOver();
        gameOverRoot.setPrefSize(width * cornersize, height * cornersize);
        gameOverRoot.getChildren().addAll(but);
        stage.setScene(gameOverScene);stage.setTitle("Моя семестровка");
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

