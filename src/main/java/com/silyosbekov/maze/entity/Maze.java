package com.silyosbekov.maze.entity;

import com.silyosbekov.maze.core.Direction;
import java.util.Arrays;
import java.util.Collections;

public class Maze {
    private final int width;
    private final int height;
    private final MazeCell[][] mazeGrid; // 2D array of cells

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.mazeGrid = new MazeCell[width][height];
        initializeCells();
        generateMaze(0, 0);
    }

    public MazeCell getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return mazeGrid[x][y];
        }

        return null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Generate the maze recursively. It generates a maze by removing walls between cells
     * @param currentX current x-coordinate
     * @param currentY current y-coordinate
     */
    private void generateMaze(int currentX, int currentY) {
        var directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);

        for (var direction : directions) {
            var nextX = currentX;
            var nextY = currentY;

            switch (direction) {
                case Direction.UP:
                    nextY -= 1;
                    break;
                case Direction.DOWN:
                    nextY += 1;
                    break;
                case Direction.RIGHT:
                    nextX += 1;
                    break;
                case Direction.LEFT:
                    nextX -= 1;
                    break;
            }

            if (nextX >= 0 &&
                nextX < width &&
                nextY >= 0 &&
                nextY < height &&
                mazeGrid[nextX][nextY].isInitial()
            )
            {
                mazeGrid[currentX][currentY].removeWall(direction);
                mazeGrid[nextX][nextY].removeOppositeWall(direction);
                generateMaze(nextX, nextY);
            }
        }
    }

    /**
     * Initialize maze cells with all sides walled.
     */
    private void initializeCells() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                mazeGrid[x][y] = new MazeCell(x, y);
            }
        }
    }
}
