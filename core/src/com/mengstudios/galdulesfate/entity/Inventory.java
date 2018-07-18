package com.mengstudios.galdulesfate.entity;

import com.badlogic.gdx.Gdx;
import com.mengstudios.galdulesfate.item.Item;
import com.mengstudios.galdulesfate.item.ResourceItem;

public class Inventory {
    private Item[] items = new Item[36];

    public void update(float delta) {
        for(int i = 0; i < items.length; i++) {
            if(items[i] instanceof ResourceItem) {
                if(((ResourceItem) items[i]).getCount() == 0) {
                    items[i] = null;
                }
            }
        }
    }

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
                if(item.getClass() == resourceItem.getClass() && ((ResourceItem) item).getCount() > 0) {
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
            try {
                ResourceItem resourceItemTemp = resourceItem.getClass().newInstance();
                resourceItem.changeCount(resourceItem.getCount());
                add(resourceItemTemp);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeResource(ResourceItem resourceItem, int count) {
        while(findResourceItem(resourceItem) != null) {
            ResourceItem has = findResourceItem(resourceItem);
            has.remove(count);
            Gdx.app.log("Inventory", "resourceItem found: " + has.toString());
            if(has.getCount() != 0) {
                return;
            }
        }
    }

    public Item[] getItems() {
        return items;
    }
}
