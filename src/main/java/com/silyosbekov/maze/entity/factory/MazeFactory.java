package com.silyosbekov.maze.entity.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.silyosbekov.maze.core.GameManager;
import com.silyosbekov.maze.entity.Maze;
import com.silyosbekov.maze.entity.MazeCell;

/**
 * Factory for creating a maze.
 * The maze is created cell by cell with walls and coins.
 */
public class MazeFactory {
    private final WallFactory wallFactory = new WallFactory();
    private final CoinFactory coinFactory = new CoinFactory();

    public Maze createRandomMaze(int mazeSize, int cellSize) {
        var newMaze = new Maze(mazeSize, mazeSize);
        drawMaze(newMaze, cellSize);
        return newMaze;
    }

    /**
     * Draw the maze, cell by cell with walls and place coins randomly
     */
    public void drawMaze(Maze maze, int cellSize) {
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                var cell = maze.getCell(x, y);
                drawCell(cell, cellSize);
            }
        }
    }

    /**
     * Draw a maze cell with its walls
     * @param cell cell to draw
     */
    private void drawCell(MazeCell cell, int cellSize) {
        var offsetX = 5;
        var offsetY = 5;
        var x = cell.getX() * cellSize + offsetX;
        var y = cell.getY() * cellSize + offsetY;

        //addCellTexture(x, y, cellSize);

        if (cell.hasTopWall()) {
            wallFactory.createWall(x, y, cellSize, 0);
        }
        if (cell.hasBottomWall()) {
            wallFactory.createWall(x, y + cellSize, cellSize, 0);
        }
        if (cell.hasLeftWall()) {
            wallFactory.createWall(x, y, 0, cellSize);
        }
        if (cell.hasRightWall()) {
            wallFactory.createWall(x + cellSize, y, 0, cellSize);
        }

        if (!cell.hasAnyWall()) {
            addCoinRandomly(x, y);
        }
    }

    private void addCellTexture(double x, double y, int cellSize) {
        FXGL.entityBuilder()
            .at(x, y)
            .viewWithBBox(FXGL.texture("maze-cell.png", cellSize, cellSize))
            .buildAndAttach();
    }

    /**
     * Add a coin to the cell with a 30% chance
     * @param x X pixel coordinate
     * @param y Y pixel coordinate
     */
    private void addCoinRandomly(double x, double y) {
        var chanceAddingCoin = 0.3;

        if (Math.random() < chanceAddingCoin) {
            coinFactory.createCoin(x, y);
            GameManager.incrementTotalCoins();
        }
    }
}
