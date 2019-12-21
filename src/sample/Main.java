package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import static sample.Food.*;
import static sample.Snake.*;
import static sample.Game.*;


public class Main extends Application {

    static int width = 20;
    static int height = 20;
    static int cornersize = 25;







    public void start(Stage primaryStage) {

        try {
            newFood();
            Group root = new Group();
            Canvas c = new Canvas(width * cornersize, height * cornersize);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

            new AnimationTimer() {
                long lastTick = 0;

                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        tick(gc);
                        return;
                    }

                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        tick(gc);
                    }
                }

            }.start();

            Scene scene = new Scene(root, width * cornersize, height * cornersize);

            takeControl(scene);
            addBeginSnake();


            primaryStage.setScene(scene);
            primaryStage.setTitle("Моя семестровка");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        launch(args);
    }
}
