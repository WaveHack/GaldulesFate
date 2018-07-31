package com.mengstudios.galdulesfate.entity.interactiveentity;

import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.hud.ui.AnvilUi;
import com.mengstudios.galdulesfate.world.World;

public class Anvil extends InteractiveEntity {
    public Anvil(World world, float x, float y) {
        super(world, x, y);
        setRegion(Assets.ANVIL);
        setBounds(x, y, Assets.ANVIL.getWidth(), Assets.ANVIL.getHeight());
    }

    @Override
    public void create() {
        setUi(new AnvilUi(world.getPlayScreen().getHud()));
        super.create();
    }

    @Override
    public void touchDown() {
        ui.show();
    }
}
