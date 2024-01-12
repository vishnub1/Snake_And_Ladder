package org.example.snakeladder;

import javafx.util.Pair;
import java.nio.channels.Pipe;
import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer, Integer>> posCoordinates;

    ArrayList<Integer> snake_ladder;

    public Board() {
        posCoordinates = new ArrayList<>();
        populate_snakeLadder();
        posCoordinates.add(new Pair<>(0, 0));
        for (int i = 0; i < SnakeAndLadder.height; i++) {
            for (int j = 0; j < SnakeAndLadder.width; j++) {
                int yCord = SnakeAndLadder.tile_size * SnakeAndLadder.height - (i * SnakeAndLadder.tile_size)
                        - (SnakeAndLadder.tile_size / 2);
                int xCord = 0;
                if ((i & 1) == 0) {
                    xCord = (j * SnakeAndLadder.tile_size) + SnakeAndLadder.tile_size / 2;
                } else {
                    xCord = SnakeAndLadder.tile_size * SnakeAndLadder.width - (j * SnakeAndLadder.tile_size)
                            - (SnakeAndLadder.tile_size / 2);
                }

                posCoordinates.add(new Pair<>(xCord, yCord));
            }
        }
    }

    public void populate_snakeLadder() {
        snake_ladder = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snake_ladder.add(i);
        }

        snake_ladder.set(4, 25);
        snake_ladder.set(13, 46);
        snake_ladder.set(27, 5);
        snake_ladder.set(33, 49);
        snake_ladder.set(40, 3);
        snake_ladder.set(42, 63);
        snake_ladder.set(43, 18);
        snake_ladder.set(50, 69);
        snake_ladder.set(54, 31);
        snake_ladder.set(62, 81);
        snake_ladder.set(66, 45);
        snake_ladder.set(74, 92);
        snake_ladder.set(76, 58);
        snake_ladder.set(89, 53);
        snake_ladder.set(99, 41);
    }

    public int get_x(int pos) {
        if (pos >= 1 && pos <= 100) {
            return posCoordinates.get(pos).getKey();
        }
        return -1;
    }

    public int get_y(int pos) {
        if (pos >= 1 && pos <= 100) {
            return posCoordinates.get(pos).getValue();
        }
        return -1;
    }

    // public static void main(String[] args) {
    // Board board = new Board();
    // for(int i = 0; i < board.posCoordinates.size(); i++)
    // {
    // System.out.println(i + " $"+" x : "+ board.posCoordinates.get(i).getKey()+" y
    // : "+board.posCoordinates.get(i).getValue());
    // }
    // }
}