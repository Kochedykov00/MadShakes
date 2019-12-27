package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;


import static sample.Food.*;
import static sample.Game.gameOver;
import static sample.Main.*;



public class Snake extends Pane {

    static Dir direction = Dir.left;

    public enum Dir {
        left, right, up, down, update
    }

    int xPos;
    int yPos;


    List<Corner> snake = new ArrayList<>();

    public Snake(int x, int y) {
        this.xPos = x;
        this.yPos = y;
        snake.add(new Corner(xPos, yPos));
        snake.add(new Corner(xPos, yPos));
        snake.add(new Corner(xPos, yPos));
    }



    public class Corner {
        int x;
        int y;

        public Corner(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }


    public static void takeControl (Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.W) {
                direction = Snake.Dir.up;
            }
            if (key.getCode() == KeyCode.A) {
                direction = Snake.Dir.left;
            }
            if (key.getCode() == KeyCode.S) {
                direction = Snake.Dir.down;
            }
            if (key.getCode() == KeyCode.D) {
                direction = Snake.Dir.right;
            }
            if (key.getCode() == KeyCode.K) {
                direction = Snake.Dir.update;
            }
        });
    }




    public void tick(GraphicsContext gc) {

        if (gameOver) {
            setGameOverScene();
            return;
        }

        for (int i = this.snake.size() - 1; i >= 1; i--) {
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

    void check (Pane pane, Food food) {

        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            snake.add(new Snake.Corner(-1, -1));
            newFood(food, pane, snake);
        }

    }





    //public int headSnakeOfx() {
      //  return snake.get(0).x;
    //}

    //public int headSnakeOfy() {
      //  return snake.get(0).y;
    //}


     void deleteSnake() {
        snake.clear();
    }


    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}






