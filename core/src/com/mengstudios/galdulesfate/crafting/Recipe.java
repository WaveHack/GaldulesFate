package com.mengstudios.galdulesfate.crafting;

import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.entity.mob.Player;
import com.mengstudios.galdulesfate.item.Item;
import com.mengstudios.galdulesfate.item.resourceitem.ResourceItem;

public class Recipe {
    Array<Item> costs = new Array<>();
    boolean canCraft;
    Item output;

    public Recipe addCost(ResourceItem resourceItem, int count) {
        try {
            ResourceItem item = resourceItem.getClass().newInstance();
            item.setCount(count);
            costs.add(item);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean canCraft(Player player) {
        for(int i = 0; i < costs.size; i++) {
            if(costs.get(i) instanceof ResourceItem) {
                ResourceItem resourceItem = ((ResourceItem) costs.get(i));
                if(!player.getInventory().hasResourceItem(resourceItem, resourceItem.getCount())) {
                    canCraft = false;
                    return false;
                }
            }
        }
        canCraft = true;
        return true;
    }

    public void deductCosts(Player player) {
        for(int i = 0; i < costs.size; i++) {
            if(costs.get(i) instanceof ResourceItem) {
                ResourceItem resourceItem = ((ResourceItem) costs.get(i));
                player.getInventory().removeResource(resourceItem, resourceItem.getCount());
            }
        }
    }

    public void setOutput(Item output) {
        this.output = output;
    }

    public void craft(Player player) {
        if(output instanceof ResourceItem) {
            player.getInventory().addResource((ResourceItem) output);
        } else {
            player.getInventory().add(output);
        }
    }

    public Array<Item> getCosts() {
        return costs;
    }
}
