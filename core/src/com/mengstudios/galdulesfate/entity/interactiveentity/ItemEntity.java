package com.mengstudios.galdulesfate.entity.interactiveentity;

import com.mengstudios.galdulesfate.item.Item;
import com.mengstudios.galdulesfate.item.ResourceItem;
import com.mengstudios.galdulesfate.world.World;

public class ItemEntity extends InteractiveEntity {
    Item item;

    public ItemEntity(World world, Item item, float x, float y) {
        super(world, x, y);
        solid = false;
        this.item = item;
        setRegion(item.getRegion());
        setBounds(x, y, item.getRegion().getRegionWidth(), item.getRegion().getRegionHeight());
    }

    @Override
    public void touchDown() {
        if(item instanceof ResourceItem) {
            world.getPlayer().getInventory().addResource((ResourceItem) item);
        } else {
            world.getPlayer().getInventory().add(item);
        }
        remove();
    }
}
