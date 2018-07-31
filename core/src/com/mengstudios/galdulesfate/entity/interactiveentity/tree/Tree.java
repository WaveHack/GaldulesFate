package com.mengstudios.galdulesfate.entity.interactiveentity.tree;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.entity.interactiveentity.ItemEntity;
import com.mengstudios.galdulesfate.entity.interactiveentity.InteractiveEntity;
import com.mengstudios.galdulesfate.item.resourceitem.Wood;
import com.mengstudios.galdulesfate.item.tool.Axe;
import com.mengstudios.galdulesfate.world.World;

public class Tree extends InteractiveEntity {
    protected Texture texture;
    protected TextureRegion choppedTexture;
    protected int minimumLevel;
    protected boolean chopped;
    protected Wood dropWood;
    protected float timer;
    protected boolean chopping;
    protected boolean soundIsPlaying;
    
    public Tree(World world, float x, float y) {
        super(world, x, y);
    }

    @Override
    public void touchHeld(float delta) {
        if(world.getPlayScreen().getHud().getInventoryDisplay().getSelectedItem() instanceof Axe) {
            ((Axe) world.getPlayScreen().getHud().getInventoryDisplay().getSelectedItem()).chop(this, delta);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if(!chopped) {
            setRegion(texture);
            setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
        } else {
            setRegion(choppedTexture);
            setBounds(getX(), getY(), choppedTexture.getRegionWidth(), choppedTexture.getRegionHeight());
        }

        if(!chopping) {
            timer = 0;
            if(soundIsPlaying) {
                soundIsPlaying = false;
                Assets.AXE_SOUND.stop();
            }
        }
        chopping = false;

        if(timer > 2 && !chopped) {
            chopped = true;
            try {
                world.getEntityManager().addEntity(new ItemEntity(world, dropWood.getClass().newInstance(), getX(), getY()));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            timer = 0;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    chopped = false;
                }
            }, 2f);
            soundIsPlaying = false;
            Assets.AXE_SOUND.stop();
        }
    }

    public void chop(float delta) {
        if(chopped)
            return;

        chopping = true;
        if(!soundIsPlaying) {
            Assets.AXE_SOUND.loop(0.5f);
            soundIsPlaying = true;
        }

        timer += delta;
    }

    @Override
    public void touchDown() {
        
    }

    public int getMinimumLevel() {
        return minimumLevel;
    }
}
