package com.mengstudios.galdulesfate.world;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.entity.Player;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class World implements InputProcessor {
    private PlayScreen playScreen;

    private StretchViewport viewport;
    private OrthographicCamera camera;

    private Player player;
    private Array<Entity> entities;

    private WorldGenerator generator;

    public static final float GRAVITY = 8;

    private static final int TOTAL_KEYS = 255;
    private boolean[] keysHeld = new boolean[TOTAL_KEYS];

    public World(PlayScreen playScreen) {
        this.playScreen = playScreen;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new StretchViewport(GaldulesFate.WIDTH, GaldulesFate.HEIGHT, camera);

        player = new Player(this, 16 * 64, 16 * 64 + 64);
        player.setHealth(player.getMaxHealth());
        player.setMana(player.getMaxMana());

        entities = new Array<>();

        generator = new WorldGenerator(this);
    }

    public void update(float delta) {
        for (int i = 0; i < keysHeld.length; i++) {
            if(keysHeld[i]) {
                keyHeld(i);
            }
        }

        player.update(delta);
        for(Entity entity: entities) {
            entity.update(delta);
        }

        checkCollisions();

        generator.update(delta);

        camera.position.set(getPlayer().getX(), getPlayer().getY() + 100, 0);
        camera.update();
    }

    public void render(SpriteBatch batch) {
        batch.draw(Assets.SKY, camera.position.x - camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2);

        player.draw(batch);
        for(Entity entity: entities) {
            entity.draw(batch);
        }

        if(playScreen.getHud().getInventoryDisplay().getSelectedItem() != null) {
            playScreen.getHud().getInventoryDisplay().getSelectedItem().renderWorld(playScreen.getGame().batch,
                    getPlayer().getX() + getPlayer().getWidth() / 2, getPlayer().getY() + getPlayer().getHeight() / 2, getPlayer().isFlipX());
        }
    }

    public boolean collides(Entity entity1, Entity entity2){
        return entity1.getBoundingRectangle().overlaps(entity2.getBoundingRectangle());
    }

    public void checkCollisions() {
        player.setGrounded(false);
        for (Entity entity: entities) {
            if(!collides(player, entity) || !entity.isSolid())
                continue;

            if(player.getPx() + player.getWidth() <= entity.getX()) {
                player.setX(entity.getX() - player.getWidth());
            }
            if(player.getPx() >= entity.getX() + entity.getWidth()) {
                player.setX(entity.getX() + entity.getWidth());
            }
            if(player.getPy() + player.getHeight() <= entity.getY()) {
                player.setY(entity.getY() - player.getHeight());
            }
            if(player.getPy() >= entity.getY() + entity.getHeight()) {
                player.setY(entity.getY() + entity.getHeight());
                //player.setVelocity(player.getVelocityX(), 0f);
                player.setGrounded(true);
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Array<Entity> getEntities() {
        return entities;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public StretchViewport getViewport() {
        return viewport;
    }

    @Override
    public boolean keyDown(int keycode) {
        float velocityX = 0;

        if(keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            velocityX -= 200;
            getPlayer().setVelocityX(velocityX);
        }
        if(keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            velocityX += 200;
            getPlayer().setVelocityX(velocityX);
        }
        if(keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            getPlayer().jump();
        }

        keysHeld[keycode] = true;

        return false;
    }

    public boolean keyHeld(int keycode) {
        float velocityX = 0;

        if(keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            velocityX -= 200;
            getPlayer().setVelocityX(velocityX);
        }
        if(keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            velocityX += 200;
            getPlayer().setVelocityX(velocityX);
        }
        if(keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            getPlayer().jump();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.A ||  keycode == Input.Keys.LEFT || keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            getPlayer().setVelocityX(0);
        }

        keysHeld[keycode] = false;

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 screenCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        screenX = Math.round(screenCoords.x);
        screenY = Math.round(screenCoords.y);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 screenCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        screenX = Math.round(screenCoords.x);
        screenY = Math.round(screenCoords.y);

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
