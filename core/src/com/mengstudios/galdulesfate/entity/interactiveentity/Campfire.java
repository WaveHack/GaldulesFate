package com.mengstudios.galdulesfate.entity.interactiveentity;


import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.hud.ui.CampfireUi;
import com.mengstudios.galdulesfate.world.World;

public class Campfire extends InteractiveEntity {

    public Campfire(World world, float x, float y) {
        super(world, x, y);
        setRegion(Assets.CAMPFIRE);
        setBounds(x, y, Assets.CAMPFIRE.getWidth(), Assets.CAMPFIRE.getHeight());
    }

    @Override
    public void create() {
        setUi(new CampfireUi(world.getPlayScreen().getHud()));
        super.create();
    }

    @Override
    public void touchDown() {
        ui.show();
    }
}
