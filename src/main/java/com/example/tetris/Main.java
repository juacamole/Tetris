package com.example.tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private TetrisGame tetrisGame;

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean downPressed = false;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setStyle("-fx-background-color: black;"); // Set background color

        tetrisGame = new TetrisGame(root);

        Scene scene = new Scene(root, Block.SIZE * TetrisGame.WIDTH, Block.SIZE * TetrisGame.HEIGHT);

        // Set up key event handling
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    leftPressed = true;
                    tetrisGame.moveLeft();
                    break;
                case RIGHT:
                    rightPressed = true;
                    tetrisGame.moveRight();
                    break;
                case DOWN:
                    downPressed = true;
                    tetrisGame.setFastDrop(true);
                    break;
                case UP:
                    tetrisGame.rotate();
                    break;
                // Add any other controls you need here
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:
                    leftPressed = false;
                    break;
                case RIGHT:
                    rightPressed = false;
                    break;
                case DOWN:
                    downPressed = false;
                    tetrisGame.setFastDrop(false);
                    break;
                // Handle other key releases if needed
            }
        });

        primaryStage.setTitle("Tetris");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start the game loop (if you have one)
        // Example: AnimationTimer or Timeline for game updates
    }

    public static void main(String[] args) {
        launch(args);
    }
}
