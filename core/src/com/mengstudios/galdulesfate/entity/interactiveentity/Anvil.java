package com.mengstudios.galdulesfate.entity.interactiveentity;

import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.hud.AnvilUi;
import com.mengstudios.galdulesfate.world.World;

public class Anvil extends InteractiveEntity {
    AnvilUi anvilUi;

    public Anvil(World world, float x, float y) {
        super(world, x, y);
        solid = false;
        setRegion(Assets.ANVIL);
        setBounds(x, y, Assets.ANVIL.getWidth(), Assets.ANVIL.getHeight());
    }

    @Override
    public void create() {
        anvilUi = new AnvilUi(world.getPlayScreen().getHud());
        anvilUi.hide();
        world.getPlayScreen().getHud().addUi(anvilUi);
    }

    @Override
    public void touchDown() {
        anvilUi.show();
    }
}
