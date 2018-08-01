package com.mengstudios.galdulesfate.item;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class Campfire extends Item {

    public Campfire(PlayScreen playScreen) {
        super(playScreen);

        region = new TextureRegion(Assets.CAMPFIRE);
    }

    @Override
    public void touchDown(int screenX, int screenY) {
        super.touchDown(screenX, screenY);

        playScreen.getWorld().getEntityManager().addEntity(
                new com.mengstudios.galdulesfate.entity.interactiveentity.Campfire(playScreen.getWorld(), screenX, screenY));
        playScreen.getPlayer().getInventory().remove(this);
    }
}
