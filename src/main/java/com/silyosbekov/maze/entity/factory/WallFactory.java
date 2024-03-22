package com.silyosbekov.maze.entity.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.silyosbekov.maze.entity.EntityType;
import com.silyosbekov.maze.entity.Wall;
import javafx.scene.shape.Line;

public class WallFactory {
    public Wall createWall(double x, double y, double lengthX, double lengthY) {
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        var entity = FXGL.entityBuilder()
                .type(EntityType.WALL)
                .at(x, y)
                .viewWithBBox(new Line(0, 0, lengthX, lengthY))
                //.viewWithBBox(FXGL.texture("wall.png", 32, 32))
                .collidable()
                .with(physics)
                .buildAndAttach();

        return new Wall(entity);
    }
}
