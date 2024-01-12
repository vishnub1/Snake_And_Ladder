package org.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {

    private Circle coin;
    private int curr_pos;
    private String name;

    private static Board this_board = new Board();

    public Player(int tile_size, Color coin_color, String Player_name) {
        coin = new Circle(tile_size / 2);
        coin.setFill(coin_color);
        coin.setStroke(Color.BLACK);
        curr_pos = 0;
        move_player(1);
        name = Player_name;
    }

    public void move_player(int dice_value) {
        if (curr_pos + dice_value <= 100) {
            curr_pos += dice_value;
            TranslateTransition move1 = translate_animation(dice_value);

            int new_pos = curr_pos;

            new_pos = this_board.snake_ladder.get(new_pos); // check from the snakeladder array if snake or ladder
            // present.

            curr_pos = new_pos;

            TranslateTransition move2 = translate_animation(dice_value);

            SequentialTransition sq = new SequentialTransition(move1, new PauseTransition(Duration.millis(1000)),
                    move2);

            sq.play();
        }
    }

    private TranslateTransition translate_animation(int dice_val) {
        TranslateTransition animate = new TranslateTransition(Duration.millis(dice_val * 200), coin);
        animate.setToX(this_board.get_x(curr_pos));
        animate.setToY(this_board.get_y(curr_pos));
        animate.setAutoReverse(false);
        return animate;
    }

    public boolean player_won() {
        if (curr_pos == 100) {
            return true;
        }
        return false;
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCurr_pos() {
        return curr_pos;
    }

    public String getName() {
        return name;
    }
}
