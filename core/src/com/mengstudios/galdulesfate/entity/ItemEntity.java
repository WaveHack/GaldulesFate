package com.mengstudios.galdulesfate.entity;

import com.mengstudios.galdulesfate.item.Item;
import com.mengstudios.galdulesfate.item.ResourceItem;
import com.mengstudios.galdulesfate.world.World;

public class ItemEntity extends Entity {
    Item item;

    public ItemEntity(World world, Item item, float x, float y) {
        super(world, x, y);
        solid = false;
        this.item = item;
        setRegion(item.getTexture());
        setBounds(x, y, item.getTexture().getWidth(), item.getTexture().getHeight());
    }

    public void onClicked() {
        if(item instanceof ResourceItem) {
            world.getPlayer().getInventory().addResource((ResourceItem) item);
        } else {
            world.getPlayer().getInventory().add(item);
        }
        remove();
    }

}
