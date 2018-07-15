package com.mengstudios.galdulesfate.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mengstudios.galdulesfate.screen.PlayScreen;
import com.mengstudios.galdulesfate.world.World;

public abstract class Entity extends Sprite {
    protected PlayScreen playScreen;

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
    protected boolean hasAnimation;

    public Entity(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    public Entity(PlayScreen playScreen, float x, float y) {
        this.playScreen = playScreen;
        setPosition(x, y);
    }

    public void update(float delta) {
        if(getX() > playScreen.getCamera().position.x - playScreen.getCamera().viewportWidth &&
                getX() < playScreen.getCamera().position.x + playScreen.getCamera().viewportWidth &&
                getY() > playScreen.getCamera().position.y - playScreen.getCamera().viewportHeight &&
                getY() < playScreen.getCamera().position.y + playScreen.getCamera().viewportHeight) {
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

        if(!hasAnimation) {
            if (velocityX > 0 && direction == Direction.LEFT) {
                flip(true, false);
                direction = Direction.RIGHT;
            } else if (velocityX < 0 && direction == Direction.RIGHT) {
                flip(true, false);
                direction = Direction.LEFT;
            }
        }

        translate(velocityX * delta, velocityY * delta);
    }

    @Override
    public void draw(Batch batch) {
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
}
