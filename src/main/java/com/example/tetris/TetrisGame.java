package com.example.tetris;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class TetrisGame {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;
    private Block[][] grid = new Block[HEIGHT][WIDTH];
    private Tetromino currentTetromino;
    private Pane gamePane;
    private AnimationTimer gameLoop;
    private long lastUpdate = 0;
    private long normalSpeed = 500_000_000; // Normal falling speed in nanoseconds
    private long fastSpeed = 50_000_000; // Fast falling speed when DOWN is held
    private long currentSpeed = normalSpeed;



    public TetrisGame(Pane gamePane) {
        this.gamePane = gamePane;

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (currentTetromino != null && now - lastUpdate >= currentSpeed) {
                    moveDown();
                    lastUpdate = now;
                }
            }
        };
        gameLoop.start();
        spawnTetromino();
    }

    public void setFastDrop(boolean fast) {
        currentSpeed = fast ? fastSpeed : normalSpeed;
    }

    private void spawnTetromino() {
        currentTetromino = TetrominoFactory.createRandomTetromino();
        for (Rectangle block : currentTetromino.getBlocks()) {
            gamePane.getChildren().add(block);
        }
    }

    public void moveDown() {
        if (canMove(currentTetromino, 0, 1)) {
            for (Rectangle block : currentTetromino.getBlocks()) {
                block.setY(block.getY() + Block.SIZE);
            }
        } else {
            placeTetromino();
            spawnTetromino();
        }
    }

    private void placeTetromino() {
        for (Rectangle block : currentTetromino.getBlocks()) {
            int x = (int) block.getX() / Block.SIZE;
            int y = (int) block.getY() / Block.SIZE;
            grid[y][x] = (Block) block;
        }
        checkLines();
    }

    private boolean canMove(Tetromino tetromino, int dx, int dy) {
        for (Rectangle block : tetromino.getBlocks()) {
            int x = (int) block.getX() / Block.SIZE + dx;
            int y = (int) block.getY() / Block.SIZE + dy;

            if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT || grid[y][x] != null) {
                return false;
            }
        }
        return true;
    }

    public void moveLeft() {
        if (canMove(currentTetromino, -1, 0)) {
            for (Rectangle block : currentTetromino.getBlocks()) {
                block.setX(block.getX() - Block.SIZE);
            }
        }
    }

    public void moveRight() {
        if (canMove(currentTetromino, 1, 0)) {
            for (Rectangle block : currentTetromino.getBlocks()) {
                block.setX(block.getX() + Block.SIZE);
            }
        }
    }

    public void rotate() {
        Rectangle[] blocks = currentTetromino.getBlocks();
        int x = (int) blocks[1].getX() / Block.SIZE; // Assuming the second block as pivot
        int y = (int) blocks[1].getY() / Block.SIZE;

        int[][] rotationMatrix = new int[][]{{0, -1}, {1, 0}}; // 90 degrees rotation matrix

        // Calculate new positions based on rotation matrix
        int[][] newPositions = new int[4][2];
        for (int i = 0; i < blocks.length; i++) {
            int localX = (int) blocks[i].getX() / Block.SIZE - x;
            int localY = (int) blocks[i].getY() / Block.SIZE - y;
            newPositions[i][0] = localX * rotationMatrix[0][0] + localY * rotationMatrix[0][1] + x;
            newPositions[i][1] = localX * rotationMatrix[1][0] + localY * rotationMatrix[1][1] + y;
        }

        // Check if the rotation is possible (bounds and collision)
        if (canRotate(newPositions)) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i].setX(newPositions[i][0] * Block.SIZE);
                blocks[i].setY(newPositions[i][1] * Block.SIZE);
            }
        }
    }

    private boolean canRotate(int[][] newPositions) {
        for (int[] position : newPositions) {
            int x = position[0];
            int y = position[1];

            if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT || (grid[y][x] != null && !containsBlock(currentTetromino, grid[y][x]))) {
                return false;
            }
        }
        return true;
    }

    private boolean containsBlock(Tetromino tetromino, Block block) {
        for (Rectangle rect : tetromino.getBlocks()) {
            if (rect == block) {
                return true;
            }
        }
        return false;
    }

    private void checkLines() {
        int line = HEIGHT - 1;
        while (line >= 0) {
            if (isLineComplete(line)) {
                removeLine(line);
                shiftDown(line);
                // Don't decrement line index here because shifting down might create a new complete line at the same index
            } else {
                line--; // Only move to the next line if the current line was not complete
            }
        }
    }

    private boolean isLineComplete(int line) {
        for (int x = 0; x < WIDTH; x++) {
            if (grid[line][x] == null) {
                return false;
            }
        }
        return true;
    }

    private void removeLine(int line) {
        for (int x = 0; x < WIDTH; x++) {
            Block block = grid[line][x];
            gamePane.getChildren().remove(block);
            grid[line][x] = null;
        }
    }

    private void shiftDown(int line) {
        for (int y = line - 1; y >= 0; y--) {
            for (int x = 0; x < WIDTH; x++) {
                Block block = grid[y][x];
                if (block != null) {
                    block.setY(block.getY() + Block.SIZE);
                    grid[y + 1][x] = block;
                    grid[y][x] = null;
                }
            }
        }
    }
}
