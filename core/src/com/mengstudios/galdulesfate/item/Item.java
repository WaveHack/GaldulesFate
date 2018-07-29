package com.mengstudios.galdulesfate.item;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Item {
    protected TextureRegion region;

    protected boolean swinging;
    protected float rotation = 0;

    protected float x;
    protected float y;

    final float ROTATION_SPEED = 500;

    public void update(float delta) {
        if(swinging) {
            rotation += ROTATION_SPEED * delta;
        } else {
            rotation = -90;
        }
    }

    public void renderWorld(SpriteBatch batch, float x, float y, boolean clockwise) {
        this.x = x;
        this.y = y;

        if(swinging) {
            if(clockwise && region.isFlipX()) {
                region.flip(true, false);
            } else if(!clockwise && !region.isFlipX()) {
                region.flip(true, false);
            }

            if(clockwise) {
                batch.draw(region, x, y, 0, 0, region.getRegionWidth(), region.getRegionHeight(),
                        1, 1,
                        -rotation);
            } else {
                batch.draw(region, x - region.getRegionWidth(), y, region.getRegionWidth(), 0, region.getRegionWidth(), region.getRegionHeight(),
                        1, 1,
                        rotation);
            }
            /*batch.draw(region, x, y, 0, 0, region.getRegionWidth(), region.getRegionHeight(),
                    1, 1,
                    clockwise ? rotation : -rotation,
                    0, 0, region.getRegionWidth(), region.getRegionHeight(),
                    false, false);*/
        }
    }

    public boolean isSwinging() {
        return swinging;
    }

    public void setSwinging(boolean swinging) {
        this.swinging = swinging;
    }

    public void renderUi(SpriteBatch batch, float x, float y) {
        batch.draw(region, x, y);
    }

    public void renderInventory(SpriteBatch batch, float inventoryX, float inventoryY, int row, int column) {
        if(region.isFlipX()) {
            region.flip(true, false);
        }
        batch.draw(region, inventoryX + column * 64, inventoryY + row * 64);
    }

    public TextureRegion getRegion() {
        return region;
    }
}
