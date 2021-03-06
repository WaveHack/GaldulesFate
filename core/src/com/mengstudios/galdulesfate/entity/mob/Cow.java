package com.mengstudios.galdulesfate.entity.mob;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.entity.interactiveentity.ItemEntity;
import com.mengstudios.galdulesfate.item.resourceitem.food.RawBeef;
import com.mengstudios.galdulesfate.world.World;

public class Cow extends Mob {
    boolean hurtSoundIsPlaying;

    public Cow(World world, float x, float y) {
        super(world, x, y);
        
        Array<TextureRegion> frames = new Array<>();
        for(int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(Assets.COW, Assets.COW.getWidth() / 4 * i, 0, Assets.COW.getWidth() / 4, Assets.COW.getHeight()));
        }
        walk = new Animation<>(1/10f, frames);
        walk.setPlayMode(Animation.PlayMode.LOOP);

        standTexture = frames.get(0);
        jumpTexture = frames.get(0);

        setRegion(standTexture);
        setBounds(x, y, Assets.COW.getWidth() / 4, Assets.COW.getHeight());
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

        if(!hurtSoundIsPlaying) {
            Assets.COW_HURT_SOUND.play(0.3f);
            hurtSoundIsPlaying = true;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    hurtSoundIsPlaying = false;
                }
            }, 1.45f);
        }
        super.takeDamage(damage);
    }

    @Override
    public void die() {
        if(state == State.DYING) {
            return;
        }

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                world.getEntityManager().addEntity(new ItemEntity(world, new RawBeef(), getX(), getY()));
            }
        }, 1f);
        super.die();
        //Assets.COW_DEATH_SOUND.play(0.5f);
    }
}
