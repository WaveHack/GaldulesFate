package com.mengstudios.galdulesfate.entity.mob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.MathExtended;
import com.mengstudios.galdulesfate.world.World;

import java.util.Random;

public class Pig extends Mob {
    Random random = new Random();
    float timerMax;

    public Pig(World world, float x, float y) {
        super(world, x, y);

        Array<TextureRegion> frames = new Array<>();
        for(int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(Assets.PIG, Assets.PIG.getWidth() / 4 * i, 0, Assets.PIG.getWidth() / 4, Assets.PIG.getHeight()));
        }
        walk = new Animation<>(1/10f, frames);
        walk.setPlayMode(Animation.PlayMode.LOOP);

        standTexture = frames.get(0);
        jumpTexture = frames.get(0);

        setRegion(standTexture);
        setBounds(x, y, Assets.PIG.getWidth() / 4, Assets.PIG.getHeight());

        canFall = true;

        stateTimer = 0;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if(stateTimer > timerMax) {
            if(state == State.STANDING) {
                if(Math.random() < 0.5f) {
                    setVelocityX(100);
                } else {
                    setVelocityX(-100);
                }
                timerMax = MathExtended.getFloatBetween(0.1f, 1) * 5;
            } else {
                state = State.STANDING;
                setVelocityX(0);
                timerMax = MathExtended.getFloatBetween(0.1f, 1) * 5;
            }
        }
    }
}
