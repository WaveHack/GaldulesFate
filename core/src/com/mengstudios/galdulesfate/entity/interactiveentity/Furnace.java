package com.mengstudios.galdulesfate.entity.interactiveentity;

import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.hud.FurnaceUi;
import com.mengstudios.galdulesfate.world.World;

public class Furnace extends InteractiveEntity {
    FurnaceUi furnaceUi;

    public Furnace(World world, float x, float y) {
        super(world, x, y);
        solid = false;
        setRegion(Assets.FURNACE);
        setBounds(x, y, Assets.FURNACE.getWidth(), Assets.FURNACE.getHeight());
    }

    @Override
    public void create() {
        furnaceUi = new FurnaceUi(world.getPlayScreen().getHud());
        furnaceUi.hide();
        world.getPlayScreen().getHud().addUi(furnaceUi);
    }

    @Override
    public void touchDown() {
        furnaceUi.show();
        /*if(world.getPlayScreen().getHud().getInventoryDisplay().getSelectedItem() instanceof CopperOre) {
            try {
                world.getPlayer().getInventory().removeResource(CopperOre.class.newInstance(), 1);
                world.getPlayer().getInventory().addResource(new CopperBar());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }*/

    }
}
