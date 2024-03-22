package com.silyosbekov.maze.entity;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;

public class Coin {
    // private final int value = 1;
    private final Entity entity;
    private final PhysicsComponent physics;

    public Coin(Entity entity) {
        this.entity = entity;
        this.physics = this.entity.getComponent(PhysicsComponent.class);
        // this.physics.setBodyType(BodyType.STATIC);
    }

    public Entity getEntity() {
        return entity;
    }

    public PhysicsComponent getPhysics() {
        return physics;
    }
}
