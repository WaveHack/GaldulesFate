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
        neverFlip = true;
        this.projectile = projectile;
        setRegion(projectile.getRegion());
        setBounds(x, y, projectile.getRegion().getRegionWidth(), projectile.getRegion().getRegionHeight());
        setVelocity(velocityX, velocityY);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        setRotation((float) Math.toDegrees(Math.atan2(velocityY, velocityX) - 45));
    }

    public void hurt(Mob mob) {
        mob.takeDamage(projectile.getDamage());
        remove();
    }

    public Projectile getProjectile() {
        return projectile;
    }
}
