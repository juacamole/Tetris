package com.example.tetris;

import java.util.Random;
public class TetrominoFactory {

    private static final Random random = new Random();

    public static Tetromino createLine() {
        Block[] blocks = {new Block(), new Block(), new Block(), new Block()};
        // Example positions for a vertical line
        blocks[0].setX(0);
        blocks[0].setY(0);
        blocks[1].setX(Block.SIZE);
        blocks[1].setY(0);
        blocks[2].setX(2 * Block.SIZE);
        blocks[2].setY(0);
        blocks[3].setX(3 * Block.SIZE);
        blocks[3].setY(0);

        return new Tetromino(blocks);
    }

    public static Tetromino createSquare() {
        Block[] blocks = {new Block(), new Block(), new Block(), new Block()};
        // Example positions for a square
        blocks[0].setX(0);
        blocks[0].setY(0);
        blocks[1].setX(Block.SIZE);
        blocks[1].setY(0);
        blocks[2].setX(0);
        blocks[2].setY(Block.SIZE);
        blocks[3].setX(Block.SIZE);
        blocks[3].setY(Block.SIZE);

        return new Tetromino(blocks);
    }

    public static Tetromino createL() {
        Block[] blocks = { new Block(), new Block(), new Block(), new Block() };
        // Example positions for L shape
        for (int i = 0; i < 3; i++) {
            blocks[i].setX(i * Block.SIZE);
            blocks[i].setY(0);
        }
        blocks[3].setX(2 * Block.SIZE);
        blocks[3].setY(Block.SIZE);

        return new Tetromino(blocks);
    }

    public static Tetromino createJ() {
        Block[] blocks = { new Block(), new Block(), new Block(), new Block() };
        // Example positions for J shape
        for (int i = 0; i < 3; i++) {
            blocks[i].setX(i * Block.SIZE);
            blocks[i].setY(0);
        }
        blocks[3].setX(0);
        blocks[3].setY(Block.SIZE);

        return new Tetromino(blocks);
    }

    public static Tetromino createT() {
        Block[] blocks = { new Block(), new Block(), new Block(), new Block() };
        // Example positions for T shape
        for (int i = 0; i < 3; i++) {
            blocks[i].setX(i * Block.SIZE);
            blocks[i].setY(0);
        }
        blocks[3].setX(Block.SIZE);
        blocks[3].setY(Block.SIZE);

        return new Tetromino(blocks);
    }

    public static Tetromino createS() {
        Block[] blocks = { new Block(), new Block(), new Block(), new Block() };
        // Example positions for S shape
        blocks[0].setX(Block.SIZE); blocks[0].setY(0);
        blocks[1].setX(2 * Block.SIZE); blocks[1].setY(0);
        blocks[2].setX(0); blocks[2].setY(Block.SIZE);
        blocks[3].setX(Block.SIZE); blocks[3].setY(Block.SIZE);

        return new Tetromino(blocks);
    }

    public static Tetromino createZ() {
        Block[] blocks = { new Block(), new Block(), new Block(), new Block() };
        // Example positions for Z shape
        blocks[0].setX(0); blocks[0].setY(0);
        blocks[1].setX(Block.SIZE); blocks[1].setY(0);
        blocks[2].setX(Block.SIZE); blocks[2].setY(Block.SIZE);
        blocks[3].setX(2 * Block.SIZE); blocks[3].setY(Block.SIZE);

        return new Tetromino(blocks);
    }
    public static Tetromino createRandomTetromino() {
        int shape = random.nextInt(7); // There are 7 Tetris shapes
        switch (shape) {
            case 0:
                return createLine();
            case 1:
                return createSquare();
            case 2:
                return createL();
            case 3:
                return createJ();
            case 4:
                return createT();
            case 5:
                return createS();
            case 6:
                return createZ();
            default:
                throw new IllegalStateException("Unexpected value: " + shape);
        }
    }

}