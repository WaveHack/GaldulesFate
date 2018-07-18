package com.mengstudios.galdulesfate.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.item.CopperOre;
import com.mengstudios.galdulesfate.item.tool.CopperPickaxe;
import com.mengstudios.galdulesfate.world.World;

public class Player extends Mob {
    public enum State {STANDING, WALKING, JUMPING}
    protected State previousState = State.STANDING;
    protected State state = State.STANDING;

    protected Inventory inventory;

    protected Animation<TextureRegion> walk;
    protected TextureRegion standTexture;
    protected TextureRegion jumpTexture;

    protected float jumpHeight = 500;
    protected int maxMana;
    protected int mana;

    protected float stateTimer;

    public Player(World world, float x, float y) {
        super(world, x, y);

        Array<TextureRegion> frames = new Array<>();
        for(int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(Assets.PLAYER, i * 27 * 2, 0, 27 * 2, 50 * 2));
        }
        walk = new Animation<>(1/10f, frames);
        walk.setPlayMode(Animation.PlayMode.LOOP);

        standTexture = frames.get(0);
        jumpTexture = frames.get(0);

        setRegion(standTexture);
        setBounds(x, y, 54, 100);

        maxHealth = 100;
        maxMana = 100;

        canFall = true;

        stateTimer = 0;

        inventory = new Inventory();
        inventory.add(new CopperPickaxe());
        inventory.addResource(new CopperOre());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        setRegion(getFrame(delta));

        inventory.update(delta);
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
                Gdx.app.log("Player", "Frame null!");
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

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void changeMana(int mana) {
        this.mana += mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public State getState() {
        if(velocityY > 0 || (velocityY < 0 && previousState == State.JUMPING)) {
            return State.JUMPING;
        } else if(velocityX != 0) {
            return State.WALKING;
        }
        return State.STANDING;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void jump() {
        if(!grounded) {
            return;
        }
        velocityY += jumpHeight;
        grounded = false;
    }
}
