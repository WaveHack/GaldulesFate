package com.mengstudios.galdulesfate.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.entity.interactiveentity.InteractiveEntity;
import com.mengstudios.galdulesfate.entity.Player;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class World implements InputProcessor {
    private PlayScreen playScreen;

    private ExtendViewport viewport;
    private OrthographicCamera camera;

    private Player player;
    private Array<Entity> entities;
    private Array<Entity> entitiesToRemove;
    private Array<Entity> inactiveEntities;

    private WorldGenerator generator;

    public static final float GRAVITY = 8;

    private static final int TOTAL_KEYS = 255;
    private boolean[] keysHeld = new boolean[TOTAL_KEYS];
    private boolean touchHeld;

    private boolean created;

    public World(PlayScreen playScreen) {
        this.playScreen = playScreen;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new ExtendViewport(GaldulesFate.WIDTH, GaldulesFate.HEIGHT, camera);

        player = new Player(this, 16 * 64, 16 * 64 + 64);
        player.setHealth(player.getMaxHealth());
        player.setMana(player.getMaxMana());

        entities = new Array<>();
        entitiesToRemove = new Array<>();
        inactiveEntities = new Array<>();

        generator = new WorldGenerator(this);
    }

    public void create() {
        for(Entity entity: entities) {
            if(entity instanceof InteractiveEntity) {
                ((InteractiveEntity) entity).create();
            }
        }
        created = true;
    }

    public void update(float delta) {
        handleHeldInput(delta);

        player.update(delta);
        for(Entity entity: entities) {
            entity.update(delta);
            if(!entity.isActive()) {
                inactiveEntities.add(entity);
                entitiesToRemove.add(entity);
            }
            if(entity.isRemoved())
                entitiesToRemove.add(entity);
        }
        entities.removeAll(entitiesToRemove, true);
        entitiesToRemove.clear();

        for(Entity entity: inactiveEntities) {
            entity.checkIfActive();
            if(entity.isActive()) {
                entities.add(entity);
                entitiesToRemove.add(entity);
            }
        }
        inactiveEntities.removeAll(entitiesToRemove, true);
        entitiesToRemove.clear();

        checkCollisions();

        generator.update(delta);

        camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + 100, 0);
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
                    player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, !player.isFlipX());
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

    public boolean isCreated() {
        return created;
    }

    public PlayScreen getPlayScreen() {
        return playScreen;
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

    public ExtendViewport getViewport() {
        return viewport;
    }

    private void handleHeldInput(float delta) {
        for (int i = 0; i < keysHeld.length; i++) {
            if(keysHeld[i]) {
                keyHeld(i);
            }
        }
        if(touchHeld) {
            touchHeld(Gdx.input.getX(), Gdx.input.getY(), delta);
        }
    }

    public boolean touchingEntity(int screenX, int screenY, Entity entity) {
        return screenX > entity.getX() && screenX < entity.getX() + entity.getWidth()
                && screenY > entity.getY() && screenY < entity.getY() + entity.getHeight();
    }

    @Override
    public boolean keyDown(int keycode) {
        float velocityX = 0;

        if(keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            velocityX -= 200;
            player.setVelocityX(velocityX);
        }
        if(keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            velocityX += 200;
            player.setVelocityX(velocityX);
        }
        if(keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            player.jump();
        }

        keysHeld[keycode] = true;

        return false;
    }

    private boolean keyHeld(int keycode) {
        float velocityX = 0;

        if(keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            velocityX -= 200;
            player.setVelocityX(velocityX);
        }
        if(keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            velocityX += 200;
            player.setVelocityX(velocityX);
        }
        if(keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            player.jump();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.A ||  keycode == Input.Keys.LEFT || keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            player.setVelocityX(0);
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

        for(Entity entity: entities) {
            if(!entity.isActive())
                continue;

            if (touchingEntity(screenX, screenY, entity)) {
                if(entity instanceof InteractiveEntity) {
                    ((InteractiveEntity) entity).touchDown();
                }
            }
        }

        touchHeld = true;

        return false;
    }

    private void touchHeld(int screenX, int screenY, float delta) {
        Vector3 screenCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        screenX = Math.round(screenCoords.x);
        screenY = Math.round(screenCoords.y);

        for(Entity entity: entities) {
            if(!entity.isActive())
                continue;

            if (touchingEntity(screenX, screenY, entity)) {
                if(entity instanceof InteractiveEntity) {
                    ((InteractiveEntity) entity).touchHeld(delta);
                }
            }
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 screenCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        screenX = Math.round(screenCoords.x);
        screenY = Math.round(screenCoords.y);

        touchHeld = false;

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
