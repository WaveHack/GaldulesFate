package com.mengstudios.galdulesfate.entity.mob;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.Assets;
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

        canFall = true;

        stateTimer = 0;
    }
}
