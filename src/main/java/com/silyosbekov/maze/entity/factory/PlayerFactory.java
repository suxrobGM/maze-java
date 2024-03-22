package com.silyosbekov.maze.entity.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.silyosbekov.maze.entity.EntityType;
import com.silyosbekov.maze.entity.Player;

public class PlayerFactory implements EntityFactory {
    public Player createPlayer(double x, double y) {
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        var playerEntity = FXGL.entityBuilder()
            .type(EntityType.PLAYER)
            .at(x, y)
            .viewWithBBox("player.png")
            .collidable()
            .with(physics)
            .buildAndAttach();

        return new Player(playerEntity);
    }
}
