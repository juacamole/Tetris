package com.example.tetris;

import javafx.scene.shape.Rectangle;

public class Tetromino {
    private Rectangle[] blocks;

    public Tetromino(Rectangle[] blocks) {
        this.blocks = blocks;
    }

    public Rectangle[] getBlocks() {
        return blocks;
    }
}
