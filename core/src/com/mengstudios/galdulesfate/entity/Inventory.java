package com.mengstudios.galdulesfate.entity;

import com.mengstudios.galdulesfate.item.Item;
import com.mengstudios.galdulesfate.item.ResourceItem;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items = new ArrayList<>(36);

    public void add(Item item) {
        items.add(item);
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public ResourceItem findResourceItem(ResourceItem resourceItem) {
        for(Item item: items) {
            if(item instanceof ResourceItem) {
                if(item == resourceItem && resourceItem.getCount() != 0) {
                    return (ResourceItem) item;
                }
            }
        }
        return null;
    }

    public void removeResource(ResourceItem resourceItem, int count) {
        while(findResourceItem(resourceItem) != null) {
            ResourceItem has = findResourceItem(resourceItem);
            has.remove(count);
            if(has.getCount() != 0) {
                return;
            }
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
