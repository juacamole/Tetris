package com.example.tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
    public static final int SIZE = 30;

    public Block() {
        super(SIZE, SIZE);
        setFill(Color.SILVER);
        setStroke(Color.WHITE);
    }
}
