package com.silyosbekov.maze.entity;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

public class Wall {
    private final Entity entity;
    private final PhysicsComponent physics;

    public Wall(Entity entity) {
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
