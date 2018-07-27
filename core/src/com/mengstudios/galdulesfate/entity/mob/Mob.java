package com.mengstudios.galdulesfate.entity.mob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.world.World;

public abstract class Mob extends Entity {
    public enum State {STANDING, WALKING, JUMPING}
    State previousState = State.STANDING;
    State state = State.STANDING;

    float stateTimer;

    Animation<TextureRegion> walk;
    TextureRegion standTexture;
    TextureRegion jumpTexture;

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
        setRegion(getFrame(delta));
    }

    public State getState() {
        if(velocityY > 0 || (velocityY < 0 && previousState == State.JUMPING)) {
            return State.JUMPING;
        } else if(velocityX != 0) {
            return State.WALKING;
        }
        return State.STANDING;
    }

    public TextureRegion getFrame(float delta) {
        state = getState();

        TextureRegion region;
        switch (state) {
            case JUMPING:
                region = jumpTexture;
                break;
            case STANDING:
                region = standTexture;
                break;
            case WALKING:
                region = walk.getKeyFrame(stateTimer);
                break;
            default:
                region = null;
                Gdx.app.log(getClass().getName(), "Frame null!");
                break;
        }

        if((velocityX < 0 || direction == Direction.LEFT) && !region.isFlipX()) {
            region.flip(true, false);
            direction = Direction.LEFT;
        } else if((velocityX > 0 || direction == Direction.RIGHT) && region.isFlipX()) {
            region.flip(true, false);
            direction = Direction.RIGHT;
        }

        stateTimer = previousState == state ? stateTimer + delta : 0;
        previousState = state;

        return region;
    }

    public void setState(State state) {
        this.state = state;
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
