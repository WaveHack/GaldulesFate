package com.mengstudios.galdulesfate.entity.interactiveentity.mineralrock;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.entity.interactiveentity.InteractiveEntity;
import com.mengstudios.galdulesfate.entity.interactiveentity.ItemEntity;
import com.mengstudios.galdulesfate.item.Ore;
import com.mengstudios.galdulesfate.item.tool.Pickaxe;
import com.mengstudios.galdulesfate.world.World;

public class MineralRock extends InteractiveEntity {
    protected Texture texture;
    protected int minimumLevel;
    protected boolean mined;
    protected Ore dropOre;
    protected float timer;
    protected boolean mining;
    protected boolean soundIsPlaying;

    public MineralRock(World world, float x, float y) {
        super(world, x, y);
        solid = false;
    }

    @Override
    public void touchDown() {

    }

    @Override
    public void touchHeld(float delta) {
        if(world.getPlayScreen().getHud().getInventoryDisplay().getSelectedItem() instanceof Pickaxe) {
            ((Pickaxe) world.getPlayScreen().getHud().getInventoryDisplay().getSelectedItem()).mine(this, delta);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if(!mined) {
            setRegion(texture);
        } else {
            setRegion(Assets.EMPTY_ROCKS);
        }

        if(!mining) {
            timer = 0;
            if(soundIsPlaying) {
                soundIsPlaying = false;
                Assets.PICKAXE_SOUND.stop();
            }
        }
        mining = false;

        if(timer > 2 && !mined) {
            mined = true;
            try {
                world.getEntityManager().addEntity(new ItemEntity(world, dropOre.getClass().newInstance(), getX(), getY()));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            timer = 0;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    mined = false;
                }
            }, 2f);
            soundIsPlaying = false;
            Assets.PICKAXE_SOUND.stop();
        }
    }

    public void mine(float delta) {
        if(mined)
            return;

        mining = true;
        if(!soundIsPlaying) {
            Assets.PICKAXE_SOUND.loop();
            soundIsPlaying = true;
        }

        timer += delta;
    }

    public int getMinimumLevel() {
        return minimumLevel;
    }
}
