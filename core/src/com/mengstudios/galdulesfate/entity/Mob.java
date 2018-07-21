package com.mengstudios.galdulesfate.entity;

import com.mengstudios.galdulesfate.world.World;

public abstract class Mob extends Entity {
    int maxHealth;
    int health;

    public Mob(World world) {
        super(world);
    }

    public Mob(World world, float x, float y) {
        super(world, x, y);
        solid = true;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void changeHealth(int health) {
        this.health += health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }
}
