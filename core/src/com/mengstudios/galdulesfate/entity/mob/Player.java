package com.mengstudios.galdulesfate.entity.mob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.entity.Inventory;
import com.mengstudios.galdulesfate.item.tool.CopperAxe;
import com.mengstudios.galdulesfate.item.tool.CopperPickaxe;
import com.mengstudios.galdulesfate.world.World;

public class Player extends Mob {
    Inventory inventory;

    float jumpHeight = 500;
    int maxMana;
    int mana;

    boolean footstepsDirtPlaying;

    public Player(World world, float x, float y) {
        super(world, x, y);

        Array<TextureRegion> frames = new Array<>();
        for(int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(Assets.PLAYER, i * 27 * 2, 0, 27 * 2, 50 * 2));
        }
        walk = new Animation<>(1/10f, frames);
        walk.setPlayMode(Animation.PlayMode.LOOP);

        standTexture = frames.get(0);
        jumpTexture = frames.get(0);

        setRegion(standTexture);
        setBounds(x, y, 54, 100);

        maxHealth = 100;
        maxMana = 100;

        canFall = true;

        stateTimer = 0;

        inventory = new Inventory(36);
        inventory.add(new CopperPickaxe());
        inventory.add(new CopperAxe());
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        inventory.update(delta);

        if((velocityX == 0 && footstepsDirtPlaying)/* || (!grounded && footstepsDirtPlaying)*/) {
            Assets.FOOTSTEP_DIRT_SOUND.stop();
            footstepsDirtPlaying = false;
        }
        if (velocityX != 0 && grounded && !footstepsDirtPlaying) {
            Assets.FOOTSTEP_DIRT_SOUND.loop(0.5f);
            footstepsDirtPlaying = true;
        }
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void changeMana(int mana) {
        this.mana += mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void jump() {
        if(!grounded) {
            return;
        }
        velocityY += jumpHeight;
        grounded = false;

        Assets.FOOTSTEP_DIRT_SOUND.stop();
        footstepsDirtPlaying = false;
    }
}
