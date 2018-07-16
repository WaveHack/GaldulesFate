package com.mengstudios.galdulesfate.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mengstudios.galdulesfate.world.World;

public abstract class Entity extends Sprite {
    protected World world;

    protected float px;
    protected float py;

    protected float velocityX;
    protected float velocityY;

    public enum Direction {LEFT, RIGHT}
    Direction direction = Direction.RIGHT;

    protected boolean canFall;
    protected boolean solid = true;
    protected boolean grounded;

    protected boolean active = true;
    protected boolean removed;

    public Entity(World world) {
        this.world = world;
    }

    public Entity(World world, float x, float y) {
        this.world = world;
        setPosition(x, y);
    }

    public void update(float delta) {
        if(getX() > world.getCamera().position.x - world.getCamera().viewportWidth &&
                getX() < world.getCamera().position.x + world.getCamera().viewportWidth &&
                getY() > world.getCamera().position.y - world.getCamera().viewportHeight &&
                getY() < world.getCamera().position.y + world.getCamera().viewportHeight) {
            active = true;
        } else {
            active = false;
            return;
        }

        px = getX();
        py = getY();

        if(canFall) {
            velocityY -= World.GRAVITY;
        }

        if(grounded) {
            velocityY = 0;
        }

        if (velocityX > 0 && direction == Direction.LEFT) {
            flip(true, false);
            direction = Direction.RIGHT;
        } else if (velocityX < 0 && direction == Direction.RIGHT) {
            flip(true, false);
            direction = Direction.LEFT;
        }

        translate(velocityX * delta, velocityY * delta);
    }

    @Override
    public void draw(Batch batch) {
        if(removed)
            return;
        if(!active)
            return;

        super.draw(batch);
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public void setVelocity(float velocityX, float velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public float getPx() {
        return px;
    }

    public float getPy() {
        return py;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }
}
