package com.mengstudios.galdulesfate.entity.mob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.world.World;

public abstract class Mob extends Entity {
    public enum MobType {PIG, COW}
    MobType mobType;

    public enum State {STANDING, WALKING, JUMPING, DYING}
    State previousState = State.STANDING;
    State state = State.STANDING;

    Ai ai;

    float stateTimer;

    Animation<TextureRegion> walk;
    TextureRegion standTexture;
    TextureRegion jumpTexture;

    Polygon boundingPolygon;

    int maxHealth;
    int health;

    boolean hurt;

    public Mob(World world, float x, float y) {
        super(world, x, y);
        solid = true;
        ai = new Ai(this);

        if(this instanceof Pig) {
            mobType = MobType.PIG;
        } else if(this instanceof Cow) {
            mobType = MobType.COW;
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        setRegion(getFrame(delta));
        if(ai != null) {
            ai.update(delta);
        }
        boundingPolygon.setPosition(getX(), getY());

        if(health <= 0 && !isRemoved()) {
            die();
        }
    }

    @Override
    public void draw(Batch batch) {
        if(hurt) {
            setColor(new Color().set(1, 0.3f, 0.3f, 1));
        } else {
            setColor(new Color(Color.WHITE));
        }
        super.draw(batch);
    }

    public State getState() {
        if(state == State.DYING) {
            return State.DYING;
        } else if(velocityY > 0 || (velocityY < 0 && previousState == State.JUMPING)) {
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
            case DYING:
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

    public void takeDamage(int damage) {
        if(hurt) {
            return;
        }

        this.health -= damage;
        hurt = true;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                hurt = false;
            }
        }, 0.5f);
    }

    public void die() {
        if(state == State.DYING) {
            return;
        }

        state = State.DYING;

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                remove();
            }
        }, 1f);
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

    public Polygon getBoundingPolygon() {
        return boundingPolygon;
    }

    public void setBoundingPolygon(Rectangle rectangle) {
        boundingPolygon = new Polygon(new float[]{0, 0, rectangle.width, 0, rectangle.width, rectangle.height, 0, rectangle.height});
        boundingPolygon.setPosition(rectangle.getX(), rectangle.getY());
    }

    public MobType getMobType() {
        return mobType;
    }
}
