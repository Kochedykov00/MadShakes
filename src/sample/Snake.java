package sample;

import java.util.ArrayList;
import java.util.List;
import static sample.Main.*;



public class Snake {

    static List<Corner> snake = new ArrayList<>();


    public static class Corner {
        int x;
        int y;

        public Corner(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }


    static void addBeginSnake() {


        snake.add(new Corner(width / 2, height / 2));
        snake.add(new Corner(width / 2, height / 2));
        snake.add(new Corner(width / 2, height / 2));

    }
}

