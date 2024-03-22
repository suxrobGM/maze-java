package com.silyosbekov.maze.entity.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.BoxShapeData;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.silyosbekov.maze.entity.Coin;
import com.silyosbekov.maze.entity.EntityType;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.ArrayList;

public class CoinFactory {

    public Coin createCoin(double x, double y) {
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        var animatedTexture = createCoinAnimation();

        var entity = FXGL.entityBuilder()
                .type(EntityType.COIN)
                .at(x, y)
                .viewWithBBox(animatedTexture)
                .collidable()
                .with(physics)
                .buildAndAttach();

        return new Coin(entity);
    }

    private AnimatedTexture createCoinAnimation() {
        var frames = new ArrayList<Image>();

        for (int i = 1; i <= 19; i++) {
            var frame = FXGL.image("coin-" + i + ".png");
            frames.add(frame);
        }

        var animChannel = new AnimationChannel(frames, Duration.seconds(1));
        var animatedTexture = new AnimatedTexture(animChannel);
        animatedTexture.loop();
        return animatedTexture;
    }
}
