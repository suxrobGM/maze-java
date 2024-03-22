package com.silyosbekov.maze.core;

import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.util.Duration;
import java.util.function.Consumer;

/**
 * GameManager class is a singleton class that manages the game state.
 * It keeps track of the total number of coins, the number of coins collected, and the score.
 */
public final class GameManager {
    private static GameManager instance = null; // Singleton instance, lazy initialized
    private final IntegerProperty totalCoins = new SimpleIntegerProperty(this, "totalCoins", 0);
    private final IntegerProperty coinsCollected = new SimpleIntegerProperty(this, "coinsCollected", 0);
    private final IntegerProperty score = new SimpleIntegerProperty(this, "score", 0);
    private final LongProperty startTime = new SimpleLongProperty();
    private final LongProperty endTime = new SimpleLongProperty();
    private boolean isGameStarted = false;

    private GameManager() {
        coinsCollected.addListener((obs, old, newValue) -> {
            if (newValue.intValue() == totalCoins.get()) {
                showCongratulationsDialog();
            }
        });
    }

    /**
     * Get the singleton instance of GameManager, lazy initialized.
     * Shortcut for GameManager.instance with shorter name.
     */
    private static GameManager ins() {
        if (instance == null) {
            instance = new GameManager();
        }

        return instance;
    }

    public static void startGame() {
        ins().startTime.set(System.currentTimeMillis());
        ins().isGameStarted = true;
    }

    public static void incrementTotalCoins() {
        ins().totalCoins.set(ins().totalCoins.get() + 1);
    }

    public static void collectCoin() {
        ins().coinsCollected.set(ins().coinsCollected.get() + 1);
        incrementScore(1);
    }

    public static void incrementScore(int value) {
        ins().score.set(ins().score.get() + value);
    }

    public static void onScoreChanged(Consumer<Integer> listener) {
        ins().score.addListener((obs, old, newValue) ->
            listener.accept(newValue.intValue())
        );
    }

    public static void reset() {
        ins().totalCoins.set(0);
        ins().coinsCollected.set(0);
        ins().score.set(0);
        ins().isGameStarted = false;
    }

    private static void showCongratulationsDialog() {
        ins().endTime.set(System.currentTimeMillis());
        var elapsedTime = ins().endTime.get() - ins().startTime.get();
        var duration = Duration.millis(elapsedTime);
        var seconds = (long) duration.toSeconds();
        var message = "Congratulations!\nAll coins collected!\nYou spent %d seconds!".formatted(seconds);

        FXGL.getDialogService().showMessageBox(message, () -> FXGL.getGameController().gotoMainMenu());
    }
}
