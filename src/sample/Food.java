package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

import static sample.Main.*;
import static sample.Snake.*;


public class Food extends Pane {

    private int count = 3;
    private int columns = 3;
    private int offsetX = 0;
    private int offsetY = 168;
    private static int width = 84;
    private static int height = 84;







    static int foodX = 15;
    static int foodY = 15;
    static int foodcolor = 0;
    static int speed = 5;
    static Random rand = new Random();
    private ImageView body;
    private Image squirrelImage = new Image("sample/img/сoins.png");
    Animation animation;

    public Food() {

        this.body = new ImageView(squirrelImage);
        body.setFitHeight(width);
        body.setFitWidth(height);
        Rectangle2D rectangle2D = new Rectangle2D( offsetX, offsetY, width, height);
        body.setViewport(rectangle2D);
        animation = new Animation(this.body, Duration.millis(200), count, columns, offsetX, offsetY, width, height);
        getChildren().addAll(body);
    }

    public static void newFood(Food food, Pane root) {
        start: while (true) {
            foodX = rand.nextInt(20);
            foodY = rand.nextInt(20);

            for (Corner c : snake) {
                if (c.x == foodX && c.y == foodY) {
                    continue start;
                }
            }
            food.setTranslateY(foodY * 24);
            food.setTranslateX(foodX * 24);

            speed++;
            //root.getChildren().addAll(food);
            foodcolor = rand.nextInt(5);

            System.out.println(foodX);
            System.out.println(foodY);
            break;
        }
    }


    public void moveX (int x) {
        this.setTranslateX(x);
    }

    public void moveY (int y) {
        this.setTranslateY(y);
    }

    public static void breakFood() {
        speed = 5;
    }


}
