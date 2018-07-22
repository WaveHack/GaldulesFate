package com.mengstudios.galdulesfate.hud;

import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.entity.Inventory;
import com.mengstudios.galdulesfate.item.tool.CopperPickaxe;

public class AnvilUi extends InventoryDisplay {
    private boolean justShown;

    public AnvilUi(Hud hud) {
        super(hud, new Inventory(32));

        inventory.add(new CopperPickaxe());

        rowCount = 4;
        columnCount = 8;

        width = columnCount * 64;
        height = rowCount * 64;

        x = GaldulesFate.WIDTH / 2 - width / 2;
        y = GaldulesFate.HEIGHT / 2 - height / 2;
    }

    @Override
    public void update(float delta) {
        justShown = false;
    }

    @Override
    public void touchDown(int screenX, int screenY, int pointer, int button) {
        if(justShown)
            return;

        if(isTouched(screenX, screenY)) {
            if(getTouchedItem(screenX, screenY) != null) {
                getTouchedItem(screenX, screenY);
            }
        } else {
            hide();
        }
    }

    @Override
    public void show() {
        super.show();
        justShown = true;
    }
}
