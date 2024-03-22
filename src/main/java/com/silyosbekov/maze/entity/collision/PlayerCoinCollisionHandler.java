package com.silyosbekov.maze.entity.collision;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.silyosbekov.maze.core.GameManager;
import com.silyosbekov.maze.entity.EntityType;

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
