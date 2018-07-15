package com.mengstudios.galdulesfate.item;

public class ResourceItem extends Item {
    private int count;

    public void remove(int count) {
        if(count > this.count) {
            this.count = 0;
        } else {
            this.count -= count;
        }
    }

    public int getCount() {
        return count;
    }
}
