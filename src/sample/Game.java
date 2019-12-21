package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static sample.Food.*;
import static sample.Main.*;
import static sample.Snake.snake;


public class Game {

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
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
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

        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            snake.add(new Snake.Corner(-1, -1));
            newFood();
        }

        // background
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width * cornersize, height * cornersize);

        // score
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + (speed - 6), 10, 30);

        // random foodcolor
        Color cc = Color.PINK;

        switch (foodcolor) {
            case 0:
                cc = Color.PURPLE;
                break;
            case 1:
                cc = Color.LIGHTBLUE;
                break;
            case 2:
                cc = Color.YELLOW;
                break;
            case 3:
                cc = Color.PINK;
                break;
            case 4:
                cc = Color.ORANGE;
                break;
        }
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
}
