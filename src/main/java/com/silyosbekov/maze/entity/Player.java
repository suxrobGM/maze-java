package com.silyosbekov.maze.entity;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.silyosbekov.maze.core.Direction;
import javafx.scene.input.KeyCode;

public class Player {
    private final Entity entity;
    private final HealthIntComponent health;
    private final PhysicsComponent physics;

    public Player(Entity entity) {
        this.entity = entity;
        this.physics = this.entity.getComponent(PhysicsComponent.class);
        // this.physics.setBodyType(BodyType.DYNAMIC);
        this.health = new HealthIntComponent(100);
        this.entity.addComponent(this.health);
    }

    public Entity getEntity() {
        return entity;
    }

    public PhysicsComponent getPhysics() {
        return physics;
    }

    public HealthIntComponent getHealth() {
        return health;
    }

    public void move(Direction direction) {
        double moveSpeed = 75;

        switch (direction) {
            case UP:
                physics.setVelocityY(-moveSpeed);
                break;
            case DOWN:
                physics.setVelocityY(moveSpeed);
                break;
            case RIGHT:
                physics.setVelocityX(moveSpeed);
                break;
            case LEFT:
                physics.setVelocityX(-moveSpeed);
                break;
        }
    }

    public void stop() {
        physics.setLinearVelocity(0, 0);
    }

    public void enableInputActions() {
        var input = FXGL.getInput();

        input.addAction(new UserAction("Move Up") {
            @Override
            protected void onActionBegin() {
                move(Direction.UP);
            }

            @Override
            protected void onActionEnd() {
                stop();
            }
        }, KeyCode.W);

        input.addAction(new UserAction("Move Down") {
            @Override
            protected void onActionBegin() {
                move(Direction.DOWN);
            }

            @Override
            protected void onActionEnd() {
                stop();
            }
        }, KeyCode.S);

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onActionBegin() {
                move(Direction.RIGHT);
            }

            @Override
            protected void onActionEnd() {
                stop();
            }
        }, KeyCode.D);

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onActionBegin() {
                move(Direction.LEFT);
            }

            @Override
            protected void onActionEnd() {
                stop();
            }
        }, KeyCode.A);
    }
}
