package com.silyosbekov.maze.entity;

import com.silyosbekov.maze.core.Direction;

/**
 * Represents a cell in the maze.
 */
public class MazeCell {
    private final int x;
    private final int y;
    private boolean hasTopWall = true;
    private boolean hasBottomWall = true;
    private boolean hasLeftWall = true;
    private boolean hasRightWall = true;
    private boolean initial = true;

    /**
     * Initialize a new Maze Cell with the given coordinates and all sides walled
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public MazeCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Check if the cell has a wall in the top side
     */
    public boolean hasTopWall() {
        return hasTopWall;
    }

    /**
     * Check if the cell has a wall in the bottom side
     */
    public boolean hasBottomWall() {
        return hasBottomWall;
    }

    /**
     * Check if the cell has a wall on the left side
     */
    public boolean hasLeftWall() {
        return hasLeftWall;
    }

    /**
     * Check if the cell has a wall on the right side
     */
    public boolean hasRightWall() {
        return hasRightWall;
    }

    /**
     * Check if the cell is the initial cell
     */
    public boolean isInitial() {
        return initial;
    }

    /**
     * Check if the cell has any wall in any direction
     */
    public boolean hasAnyWall() {
        return hasTopWall || hasBottomWall || hasLeftWall || hasRightWall;
    }

    /**
     * Remove the wall in the given direction
     * @param direction direction of the wall to remove
     */
    public void removeWall(Direction direction) {
        initial = false;
        switch (direction) {
            case UP:
                this.hasTopWall = false;
                break;
            case DOWN:
                this.hasBottomWall = false;
                break;
            case RIGHT:
                this.hasRightWall = false;
                break;
            case LEFT:
                this.hasLeftWall = false;
                break;
        }
    }

    /**
     * Remove the opposite wall of the given direction
     * @param direction direction of the wall to remove
     */
    public void removeOppositeWall(Direction direction) {
        switch (direction) {
            case UP:
                this.hasBottomWall = false;
                break;
            case DOWN:
                this.hasTopWall = false;
                break;
            case RIGHT:
                this.hasLeftWall = false;
                break;
            case LEFT:
                this.hasRightWall = false;
                break;
        }
    }
}
