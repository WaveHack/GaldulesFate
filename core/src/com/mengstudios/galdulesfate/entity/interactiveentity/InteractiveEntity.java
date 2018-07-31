package com.mengstudios.galdulesfate.entity.interactiveentity;

import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.hud.Ui;
import com.mengstudios.galdulesfate.world.World;

public abstract class InteractiveEntity extends Entity {
    Ui ui;

    public InteractiveEntity(World world, float x, float y) {
        super(world, x, y);
        solid = false;
    }

    public void create() {
        if(ui != null) {
            world.getPlayScreen().getHud().addUi(ui);
        }
    }

    public abstract void touchDown();

    public void touchHeld(float delta) {

    }

    public void setUi(Ui ui) {
        this.ui = ui;
        ui.hide();
    }
}
