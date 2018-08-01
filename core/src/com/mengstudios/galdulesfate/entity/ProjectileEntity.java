package com.mengstudios.galdulesfate.entity;

import com.mengstudios.galdulesfate.entity.mob.Mob;
import com.mengstudios.galdulesfate.item.tool.weapon.projectile.Projectile;
import com.mengstudios.galdulesfate.world.World;

public class ProjectileEntity extends Entity {
    Projectile projectile;

    public ProjectileEntity(World world, Projectile projectile, float x, float y, float velocityX, float velocityY) {
        super(world, x, y);
        solid = false;
        canFall = true;
        this.projectile = projectile;
        setRegion(projectile.getRegion());
        setBounds(x, y, projectile.getRegion().getRegionWidth(), projectile.getRegion().getRegionHeight());
        setVelocity(velocityX, velocityY);
    }

    public void hurt(Mob mob) {
        mob.takeDamage(projectile.getDamage());
        remove();
    }
}
