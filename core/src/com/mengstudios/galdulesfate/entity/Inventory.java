package com.mengstudios.galdulesfate.entity;

import com.mengstudios.galdulesfate.item.Item;
import com.mengstudios.galdulesfate.item.ResourceItem;

public class Inventory {
    private Item[] items = new Item[36];

    public void add(Item item) {
        for(int i = 0; i < items.length; i++) {
            if(items[i] == null) {
                items[i] = item;
                break;
            }
        }
    }

    public void remove(Item item) {
        for(int i = 0; i < items.length; i++) {
            if(items[i] == item) {
                items[i] = null;
            }
        }
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

    public void addResource(ResourceItem resourceItem) {
        boolean hasResource = false;
        for(int i = 0; i < items.length; i++) {
            if(items[i] == null)
                continue;

            if(resourceItem.getClass() == items[i].getClass()) {
                ((ResourceItem) items[i]).changeCount(resourceItem.getCount());
                hasResource = true;
            }
        }
        if(!hasResource) {
            add(resourceItem);
        }
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

    public Item[] getItems() {
        return items;
    }
}
