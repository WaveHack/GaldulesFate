package com.mengstudios.galdulesfate.entity;

import com.mengstudios.galdulesfate.screen.PlayScreen;
import com.mengstudios.galdulesfate.world.World;

public abstract class Mob extends Entity {
    protected int maxHealth;
    protected int health;

    public Mob(PlayScreen playScreen) {
        super(playScreen);
    }

    public Mob(PlayScreen playScreen, float x, float y) {
        super(playScreen, x, y);
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
