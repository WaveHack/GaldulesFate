package com.mengstudios.galdulesfate.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.entity.interactiveentity.InteractiveEntity;
import com.mengstudios.galdulesfate.entity.interactiveentity.ItemEntity;
import com.mengstudios.galdulesfate.screen.PlayScreen;
import com.mengstudios.galdulesfate.world.World;

import java.util.Arrays;

public class EntityManager {
    private PlayScreen playScreen;
    private World world;

    private Player player;
    private Array<Entity> entities;
    private Array<Entity> entitiesToRemove;
    private Array<Entity> inactiveEntities;
    private Array<InteractiveEntity> interactiveEntities;
    private Array<InteractiveEntity> interactiveEntitiesToRemove;

    public EntityManager(World world) {
        this.world = world;
        playScreen = world.getPlayScreen();

        player = new Player(world, 16 * 64, 16 * 64);
        player.setHealth(player.getMaxHealth());
        player.setMana(player.getMaxMana());

        entities = new Array<>();
        entitiesToRemove = new Array<>();
        inactiveEntities = new Array<>();
        interactiveEntities = new Array<>();
        interactiveEntitiesToRemove = new Array<>();
    }

    public void create() {
        for(InteractiveEntity entity: interactiveEntities) {
            entity.create();
        }
    }

    public void update(float delta) {
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

        for(InteractiveEntity interactiveEntity: interactiveEntities) {
            interactiveEntity.update(delta);
            if(!interactiveEntity.isActive()) {
                inactiveEntities.add(interactiveEntity);
                interactiveEntitiesToRemove.add(interactiveEntity);
                System.out.println(interactiveEntity);
            }
            if(interactiveEntity.isRemoved()) {
                interactiveEntitiesToRemove.add(interactiveEntity);
            }
        }
        interactiveEntities.removeAll(interactiveEntitiesToRemove, true);
        interactiveEntitiesToRemove.clear();

        for(Entity entity: inactiveEntities) {
            entity.checkIfActive();
            if(entity.isActive()) {
                if(entity instanceof InteractiveEntity) {
                    interactiveEntities.add((InteractiveEntity) entity);
                } else {
                    entities.add(entity);
                }
                entitiesToRemove.add(entity);
            }
        }
        inactiveEntities.removeAll(entitiesToRemove, true);
        entitiesToRemove.clear();

        checkCollisions();
    }

    public void render(SpriteBatch batch) {
        for(Entity entity: entities) {
            entity.draw(batch);
        }
        for(InteractiveEntity entity: interactiveEntities) {
            entity.draw(batch);
        }
        player.draw(batch);

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
            } else if(player.getPx() >= entity.getX() + entity.getWidth()) {
                player.setX(entity.getX() + entity.getWidth());
            } else if(player.getPy() + player.getHeight() <= entity.getY()) {
                player.setY(entity.getY() - player.getHeight());
                player.setVelocityY(0);
            } else if(player.getPy() >= entity.getY() + entity.getHeight()) {
                player.setY(entity.getY() + entity.getHeight());
                //player.setVelocity(player.getVelocityX(), 0f);
                player.setGrounded(true);
            }
        }

        for (InteractiveEntity entity: interactiveEntities) {
            if(!collides(player, entity) || !entity.isSolid())
                continue;

            if(player.getPx() + player.getWidth() <= entity.getX()) {
                player.setX(entity.getX() - player.getWidth());
            } else if(player.getPx() >= entity.getX() + entity.getWidth()) {
                player.setX(entity.getX() + entity.getWidth());
            } else if(player.getPy() + player.getHeight() <= entity.getY()) {
                player.setY(entity.getY() - player.getHeight());
            } else if(player.getPy() >= entity.getY() + entity.getHeight()) {
                player.setY(entity.getY() + entity.getHeight());
                //player.setVelocity(player.getVelocityX(), 0f);
                player.setGrounded(true);
            }
        }
    }

    public boolean touchingEntity(int screenX, int screenY, Entity entity) {
        return screenX > entity.getX() && screenX < entity.getX() + entity.getWidth()
                && screenY > entity.getY() && screenY < entity.getY() + entity.getHeight();
    }

    public void keyDown(int keycode) {
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
    }

    public void keyHeld(int keycode) {
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
    }

    public void keyUp(int keycode) {
        if(keycode == Input.Keys.A ||  keycode == Input.Keys.LEFT || keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            player.setVelocityX(0);
        }
    }

    public void touchDown(int screenX, int screenY, int pointer, int button) {
        for(InteractiveEntity entity: interactiveEntities) {
            if(!entity.isActive())
                continue;

            if (touchingEntity(screenX, screenY, entity)) {
                entity.touchDown();
            }
        }
    }

    public void touchHeld(int screenX, int screenY, float delta) {
        for(InteractiveEntity entity: interactiveEntities) {
            if(!entity.isActive())
                continue;

            if (touchingEntity(screenX, screenY, entity)) {
                entity.touchHeld(delta);
            }
        }
    }

    public void touchUp(int screenX, int screenY, int pointer, int button) {

    }

    public void addEntity(Entity entity) {
        if(entity instanceof InteractiveEntity) {
            if(world.isCreated()) {
                ((InteractiveEntity) entity).create();
            }
            interactiveEntities.add((InteractiveEntity) entity);
        } else {
            entities.add(entity);
        }
    }

    public Player getPlayer() {
        return player;
    }
}
