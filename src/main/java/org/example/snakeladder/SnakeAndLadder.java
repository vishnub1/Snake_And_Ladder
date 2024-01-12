package org.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeAndLadder extends Application {

    public static final int width = 10, height = 10, tile_size = 40;
    public static final int button_line = height * tile_size + 30, info_line = button_line - 25;

    private static Dice dice = new Dice();
    private Player playerOne, playerTwo;

    private boolean game_started = false, player_one_turn = true, player_two_turn = false;

    private Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(width * tile_size, height * tile_size + 100);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Tile tile = new Tile(tile_size);
                tile.setTranslateX(tile_size * j);
                tile.setTranslateY(tile_size * i);
                root.getChildren().add(tile);
            }
        }

        // Background Image
        Image img = new Image("C:\\Users\\VISHNU\\Desktop\\SnakeLadder\\src\\main\\resources\\img.png");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(tile_size * height);
        board.setFitWidth(tile_size * width);
        root.getChildren().add(board);

        // create buttons
        Button playerOneButton = new Button("Player One ");
        playerOneButton.setDisable(true);
        Button playerTwoButton = new Button("Player Two ");
        playerTwoButton.setDisable(true);
        Button start_button = new Button("Start");

        playerOneButton.setTranslateY(button_line);
        playerOneButton.setTranslateX(30);

        playerTwoButton.setTranslateY(button_line);
        playerTwoButton.setTranslateX(300);

        start_button.setTranslateY(button_line);
        start_button.setTranslateX(180);

        // info display
        Label playerOneLabel = new Label("Your Turn! Vishnu");
        Label playerTwoLabel = new Label("Your Turn! Harsh");
        Label diceLabel = new Label("Start the Game!");

        playerOneLabel.setTranslateY(info_line);
        playerOneLabel.setTranslateX(30);
        playerTwoLabel.setTranslateY(info_line);
        playerTwoLabel.setTranslateX(300);
        diceLabel.setTranslateY(info_line);
        diceLabel.setTranslateX(160);

        // intialise the players
        playerOne = new Player(tile_size, Color.BLACK, "Vishnu");
        playerTwo = new Player(tile_size, Color.WHITE, "Harsh");

        // Player Action
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (player_one_turn && game_started) {
                    int dice_val = dice.roll_dice();
                    diceLabel.setText("Dice Value : " + dice_val);
                    playerOne.move_player(dice_val);
                    if (playerOne.player_won()) {
                        diceLabel.setText(playerOne.getName() + " IS THE WINNER !!");
                        playerTwoButton.setDisable(true);
                        playerOneButton.setDisable(true);
                        player_one_turn = false;
                        playerTwoLabel.setText("");
                        start_button.setDisable(false);
                        diceLabel.setText("Restart");

                    } else {
                        player_two_turn = true;
                        player_one_turn = false;
                        playerTwoLabel.setText("Your Turn : " + playerTwo.getName());
                        playerOneLabel.setText("");
                        playerOneButton.setDisable(true);

                        playerTwoButton.setDisable(false);
                        playerOneButton.setDisable(true);
                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (player_two_turn && game_started) {
                    int dice_val = dice.roll_dice();
                    diceLabel.setText("Dice Value : " + dice_val);
                    playerTwo.move_player(dice_val);
                    if (playerTwo.player_won()) {
                        diceLabel.setText(playerTwo.getName() + " IS WINNER !!");
                        playerTwoButton.setDisable(true);
                        playerOneButton.setDisable(true);
                        player_two_turn = false;
                        playerTwoLabel.setText("");
                        start_button.setDisable(false);
                        diceLabel.setText("Restart");

                    } else {
                        player_two_turn = false;
                        player_one_turn = true;
                        playerOneLabel.setText("Your Turn : " + playerOne.getName());
                        playerTwoLabel.setText("");
                        playerTwoButton.setDisable(true);
                        playerOneButton.setDisable(false);
                    }
                }
            }
        });

        start_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game_started = true;
                diceLabel.setText("Game Started");
                start_button.setDisable(true);
                player_one_turn = true;
                playerOneLabel.setText("Your Turn : " + playerOne.getName());
                player_two_turn = false;
                playerTwoLabel.setText("");
                playerOneButton.setDisable(false);
            }
        });

        // add to root
        root.getChildren().addAll(
                playerOneButton, playerTwoButton, start_button, playerOneLabel, playerTwoLabel, diceLabel,
                playerOne.getCoin(), playerTwo.getCoin());

        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("SnakeAndLadder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
