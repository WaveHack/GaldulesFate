package com.mengstudios.galdulesfate.entity;

import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.item.CopperBar;
import com.mengstudios.galdulesfate.item.CopperOre;
import com.mengstudios.galdulesfate.world.World;

public class Furnace extends InteractiveEntity {

    public Furnace(World world, float x, float y) {
        super(world, x, y);
        solid = false;
        setRegion(Assets.FURNACE);
        setBounds(x, y, Assets.FURNACE.getWidth(), Assets.FURNACE.getHeight());
    }

    @Override
    public void touchDown() {
        if(world.getPlayScreen().getHud().getInventoryDisplay().getSelectedItem() instanceof CopperOre) {
            try {
                world.getPlayer().getInventory().removeResource(CopperOre.class.newInstance(), 1);
                world.getPlayer().getInventory().addResource(new CopperBar());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
