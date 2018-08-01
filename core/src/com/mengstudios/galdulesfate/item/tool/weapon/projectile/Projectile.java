package com.mengstudios.galdulesfate.item.tool.weapon.projectile;

import com.mengstudios.galdulesfate.item.resourceitem.ResourceItem;

public class Projectile extends ResourceItem {
    int rangedDamage;
    int projectileDamage;

    public Projectile() {
    }

    public Projectile(int count) {
        super(count);
    }

    public void setRangedDamage(int rangedDamage) {
        this.rangedDamage = rangedDamage;
    }

    public int getDamage() {
        return projectileDamage + rangedDamage;
    }
}
