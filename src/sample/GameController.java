package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

import static sample.Food.newFood;
import static sample.Food.speed;
import static sample.Game.takeControl;
import static sample.Game.tick;
import static sample.Main.setStartScene;
import static sample.Main.width;
import static sample.Snake.addBeginSnake;
import static sample.Snake.deleteSnake;

public class GameController {

    static int width = 20;
    static int height = 20;
    static int cornersize = 25;
    GraphicsContext graphicsContext;
    private AnimationTimer timer;

    private Pane gameRoot;
    private Scene gameScene;
    Main main = new Main();



    public GameController(Pane gameRoot, Scene gameScene) {
        this.gameRoot = gameRoot;
        this.gameScene = gameScene;
    }


    public void startGame() {

        gameRoot.setPrefSize(width * cornersize, height * cornersize);
        Canvas c = new Canvas(width * cornersize, height * cornersize);
        GraphicsContext gc = c.getGraphicsContext2D();
        gameRoot.getChildren().addAll(c);
        addBeginSnake();


        timer = new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    tick(gc);
                }

                if (now - lastTick > 1000000000 / speed) {
                    lastTick = now;
                    tick(gc);
                }


            }

        };
        timer.start();


        takeControl(gameScene);


    }



    public void gameOver() throws NullPointerException  {

        timer.stop();
        deleteSnake();
        setStartScene();

        gameRoot.getChildren().removeAll();

    }
}








