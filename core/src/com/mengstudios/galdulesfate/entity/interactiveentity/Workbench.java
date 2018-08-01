package com.mengstudios.galdulesfate.entity.interactiveentity;

import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.hud.ui.WorkbenchUi;
import com.mengstudios.galdulesfate.world.World;

public class Workbench extends InteractiveEntity {

    public Workbench(World world, float x, float y) {
        super(world, x, y);
        setRegion(Assets.WORKBENCH);
        setBounds(x, y, Assets.WORKBENCH.getWidth(), Assets.WORKBENCH.getHeight());
    }

    @Override
    public void create() {
        setUi(new WorkbenchUi(world.getPlayScreen().getHud()));
        super.create();
    }

    @Override
    public void touchDown() {
        ui.show();
    }
}
