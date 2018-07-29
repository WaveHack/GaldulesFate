package com.mengstudios.galdulesfate.entity.mob;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.MathExtended;
import com.mengstudios.galdulesfate.world.World;

public class Pig extends Mob {
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
        setBoundingPolygon(getBoundingRectangle());

        maxHealth = 80;
        health = maxHealth;

        canFall = true;

        stateTimer = 0;
    }

    @Override
    public void takeDamage(int damage) {
        if(hurt || state == State.DYING) {
            return;
        }

        if(Math.random() < 0.5) {
            Assets.PIG_HURT_1_SOUND.play();
        } else {
            Assets.PIG_HURT_2_SOUND.play();
        }
        super.takeDamage(damage);
    }

    @Override
    public void die() {
        if(state == State.DYING) {
            return;
        }

        super.die();
        Assets.PIG_DEATH_SOUND.play(0.5f);
    }
}
