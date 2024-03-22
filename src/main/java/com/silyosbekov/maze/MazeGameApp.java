package com.silyosbekov.maze;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.silyosbekov.maze.core.GameManager;
import com.silyosbekov.maze.core.GameConstants;
import com.silyosbekov.maze.entity.collision.PlayerCoinCollisionHandler;
import com.silyosbekov.maze.entity.factory.MazeFactory;
import com.silyosbekov.maze.entity.factory.PlayerFactory;
import javafx.scene.paint.Color;
import java.util.Arrays;

public class MazeGameApp extends GameApplication {
    private final MazeFactory mazeFactory = new MazeFactory();
    private final PlayerFactory playerFactory = new PlayerFactory();

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(810);
        settings.setHeight(810);
        settings.setTitle("Maze Game");
        settings.setVersion("0.1");
        settings.setFullScreenAllowed(false);
        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(true);
        settings.setDeveloperMenuEnabled(true);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
        settings.setCredits(Arrays.asList("Sukhrob Ilyosbekov - Lead Programmer", "2024"));
    }

    @Override
    protected void initGame() {
        clearGameState(); // Clear existing game state

        var player = playerFactory.createPlayer(100, 200);
        player.enableInputActions();
        mazeFactory.createRandomMaze(GameConstants.MAZE_SIZE, GameConstants.CELL_SIZE);
        GameManager.startGame();
    }

    @Override
    protected void initUI() {
        var scoreLabel = FXGL.addText("Score:", 10, 25);
        scoreLabel.setFill(Color.RED);

        GameManager.onScoreChanged((value) -> scoreLabel.setText("Score: %d".formatted(value)));
    }

    @Override
    protected void initPhysics() {
        var physicsWorld = FXGL.getPhysicsWorld();
        physicsWorld.setGravity(0, 0);
        physicsWorld.addCollisionHandler(new PlayerCoinCollisionHandler());
    }

    private void clearGameState() {
        FXGL.getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
        FXGL.getInput().clearAll();
        GameManager.reset();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
