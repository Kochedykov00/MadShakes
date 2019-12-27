package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import static sample.Food.*;
import static sample.Main.*;
import static sample.Snake.snake;


public class Game extends Pane {

    static boolean gameOver = false;

    static Dir direction = Dir.left;







    public enum Dir {
        left, right, up, down, update
    }







    public static void takeControl (Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.W) {
                direction = Dir.up;
            }
            if (key.getCode() == KeyCode.A) {
                direction = Dir.left;
            }
            if (key.getCode() == KeyCode.S) {
                direction = Dir.down;
            }
            if (key.getCode() == KeyCode.D) {
                direction = Dir.right;
            }
            if (key.getCode() == KeyCode.K) {
                direction = Dir.update;
            }
        });
    }




    public static void tick(GraphicsContext gc)  {

        if (gameOver) {
            setGameOverScene();
            return;
        }

        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        switch (direction) {
            case up:
                snake.get(0).y--;
                if (snake.get(0).y < 0) {
                    snake.get(0).y = height;
                }
                break;
            case down:
                snake.get(0).y++;
                if (snake.get(0).y > height) {
                    snake.get(0).y = 0;
                }
                break;
            case left:
                snake.get(0).x--;
                if (snake.get(0).x < 0) {
                    snake.get(0).x = width;
                }
                break;
            case right:
                snake.get(0).x++;
                if (snake.get(0).x > width) {
                    snake.get(0).x = 0;
                }
                break;

            case update:
                break;
        }



        //for (int i = 1; i < snake.size(); i++) {
          //  if (snake.get(i).x == snake.get(0).x && snake.get(i).y == snake.get(0).y) {
            //    gameOver = true;
            //}
        //}



        // background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * cornersize, height * cornersize);

        // score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + (speed - 5), 10, 30);

        // random foodcolor
        Color cc = Color.PINK;



        gc.setFill(cc);
        gc.fillOval(foodX * cornersize, foodY * cornersize, cornersize, cornersize);

        // snake
        for (Snake.Corner c : snake) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 1, cornersize - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * cornersize, c.y * cornersize, cornersize - 2, cornersize - 2);

        }

    }

    static void check (Pane pane, Food food) {
        //System.out.println(snake.get(0).x);
        //System.out.println(snake.get(0).y);
        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            snake.add(new Snake.Corner(-1, -1));
            newFood(food, pane);
        }

    }
}
