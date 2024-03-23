package com.silyosbekov.maze.entity.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.silyosbekov.maze.core.GameManager;
import com.silyosbekov.maze.entity.EntityType;

/**
 * Handles collision between player and coin.
 * When player collides with a coin, the coin is removed from the world and the player's score is incremented.
 */
public class PlayerCoinCollisionHandler extends CollisionHandler {
    public PlayerCoinCollisionHandler() {
        super(EntityType.PLAYER, EntityType.COIN);
    }

    @Override
    protected void onCollisionBegin(Entity player, Entity coin) {
        GameManager.collectCoin();
        coin.removeFromWorld();
    }
}
