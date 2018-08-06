package com.mengstudios.galdulesfate.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.MathExtended;
import com.mengstudios.galdulesfate.entity.interactiveentity.InteractiveEntity;
import com.mengstudios.galdulesfate.entity.interactiveentity.ItemEntity;
import com.mengstudios.galdulesfate.entity.mob.Mob;
import com.mengstudios.galdulesfate.entity.mob.Player;
import com.mengstudios.galdulesfate.entity.tile.Tile;
import com.mengstudios.galdulesfate.item.Item;
import com.mengstudios.galdulesfate.item.tool.Tool;
import com.mengstudios.galdulesfate.item.tool.weapon.RangedWeapon;
import com.mengstudios.galdulesfate.item.tool.weapon.projectile.Projectile;
import com.mengstudios.galdulesfate.screen.PlayScreen;
import com.mengstudios.galdulesfate.world.World;

public class EntityManager {
    private PlayScreen playScreen;
    private World world;

    private Player player;
    private Array<Entity> entities;
    private Array<Entity> entitiesToRemove;
    private Array<Entity> inactiveEntities;
    private Array<Tile> tiles;
    private Array<Tile> tilesToRemove;
    private Array<InteractiveEntity> interactiveEntities;
    private Array<InteractiveEntity> interactiveEntitiesToRemove;
    private Array<Mob> mobs;
    private Array<Mob> mobsToRemove;

    private int activeMobCount;

    public EntityManager(World world) {
        this.world = world;
        playScreen = world.getPlayScreen();

        player = new Player(world, 16 * 64, 16 * 64);
        player.setHealth(player.getMaxHealth());
        player.setMana(player.getMaxMana());

        entities = new Array<>();
        entitiesToRemove = new Array<>();
        inactiveEntities = new Array<>();
        tiles = new Array<>();
        tilesToRemove = new Array<>();
        interactiveEntities = new Array<>();
        interactiveEntitiesToRemove = new Array<>();
        mobs = new Array<>();
        mobsToRemove = new Array<>();
    }

    public void create() {
        for(InteractiveEntity entity: interactiveEntities) {
            entity.create();
        }
    }

    public void updateEntities(float delta) {
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
    }

    public void updateTiles(float delta) {
        for(Tile tile: tiles) {
            tile.update(delta);
            if(!tile.isActive()) {
                inactiveEntities.add(tile);
                entitiesToRemove.add(tile);
            }
            if(tile.isRemoved())
                entitiesToRemove.add(tile);
        }
        tiles.removeAll(tilesToRemove, true);
        tilesToRemove.clear();
    }

    public void updateInteractiveEntities(float delta) {
        for(InteractiveEntity interactiveEntity: interactiveEntities) {
            interactiveEntity.update(delta);
            if(!interactiveEntity.isActive()) {
                inactiveEntities.add(interactiveEntity);
                interactiveEntitiesToRemove.add(interactiveEntity);
            }
            if(interactiveEntity.isRemoved()) {
                interactiveEntitiesToRemove.add(interactiveEntity);
            }
        }
        interactiveEntities.removeAll(interactiveEntitiesToRemove, true);
        interactiveEntitiesToRemove.clear();
    }

    public void updateMobs(float delta) {
        for(Mob mob: mobs) {
            mob.update(delta);
            if(!mob.isActive()) {
                inactiveEntities.add(mob);
                mobsToRemove.add(mob);
            }
            if(mob.isRemoved()) {
                mobsToRemove.add(mob);
            }
        }
        mobs.removeAll(mobsToRemove, true);
        mobsToRemove.clear();

        activeMobCount = mobs.size;
    }

    public void update(float delta) {
        player.update(delta);

        updateEntities(delta);
        updateTiles(delta);
        updateInteractiveEntities(delta);
        updateMobs(delta);

        for(Entity entity: inactiveEntities) {
            entity.checkIfActive();
            if(entity.isActive()) {
                if(entity instanceof InteractiveEntity) {
                    interactiveEntities.add((InteractiveEntity) entity);
                } else if(entity instanceof Mob) {
                    mobs.add((Mob) entity);
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

    public void drawEntities(SpriteBatch batch, Array entities1) {
        for(int i = 0; i < entities1.size; i++) {
            ((Entity) entities1.get(i)).draw(batch);
        }
    }

    public void render(SpriteBatch batch) {
        drawEntities(batch, tiles);
        drawEntities(batch, interactiveEntities);
        drawEntities(batch, entities);
        drawEntities(batch, mobs);
        player.draw(batch);

        if(playScreen.getHud().getInventoryDisplay().getSelectedItem() != null) {
            playScreen.getHud().getInventoryDisplay().getSelectedItem().renderWorld(playScreen.getGame().batch,
                    player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, !player.isFlipX());
        }
    }

    public boolean collides(Entity entity1, Entity entity2){
        return entity1.getBoundingRectangle().overlaps(entity2.getBoundingRectangle());
    }

    public void checkEntityCollisions(Entity entity) {
        for(Entity entity1: entities) {
            if(!entity1.isActive())
                continue;
            if(entity1 instanceof ProjectileEntity) {
                if(collides(entity1, entity)) {
                    entity1.remove();
                }
            }
            if(!collides(entity1, entity) || !entity.isSolid())
                continue;

            if(entity1.getPx() + entity1.getWidth() <= entity.getX()) {
                entity1.setX(entity.getX() - entity1.getWidth());
            } else if(entity1.getPx() >= entity.getX() + entity.getWidth()) {
                entity1.setX(entity.getX() + entity.getWidth());
            } else if(entity1.getPy() + entity1.getHeight() <= entity.getY()) {
                entity1.setY(entity.getY() - entity1.getHeight());
                entity1.setVelocityY(0);
            } else if(entity1.getPy() >= entity.getY() + entity.getHeight()) {
                entity1.setY(entity.getY() + entity.getHeight());
            }
        }
    }

    public void checkMobCollisions(Entity entity) {
        for(Mob mob: mobs) {
            if(!mob.isActive())
                continue;
            if(entity instanceof ProjectileEntity) {
                if(collides(mob, entity)) {
                    ((ProjectileEntity) entity).hurt(mob);
                    entity.remove();
                }
            }
            if(!collides(mob, entity) || !entity.isSolid())
                continue;

            if(mob.getPx() + mob.getWidth() <= entity.getX()) {
                mob.setX(entity.getX() - mob.getWidth());
            } else if(mob.getPx() >= entity.getX() + entity.getWidth()) {
                mob.setX(entity.getX() + entity.getWidth());
            } else if(mob.getPy() + mob.getHeight() <= entity.getY()) {
                mob.setY(entity.getY() - mob.getHeight());
                mob.setVelocityY(0);
            } else if(mob.getPy() >= entity.getY() + entity.getHeight()) {
                mob.setY(entity.getY() + entity.getHeight());
                //mob.setVelocity(player.getVelocityX(), 0f);
                mob.setGrounded(true);
            }
        }
    }

    public void checkInteractiveEntityCollisions(Entity entity) {
        for(InteractiveEntity interactiveEntity: interactiveEntities) {
            if(!(interactiveEntity instanceof ItemEntity))
                continue;
            if(!interactiveEntity.isActive())
                continue;
            if(!collides(interactiveEntity, entity))
                continue;

            if(interactiveEntity.getPx() + interactiveEntity.getWidth() <= entity.getX()) {
                interactiveEntity.setX(entity.getX() - interactiveEntity.getWidth());
            } else if(interactiveEntity.getPx() >= entity.getX() + entity.getWidth()) {
                interactiveEntity.setX(entity.getX() + entity.getWidth());
            } else if(interactiveEntity.getPy() + interactiveEntity.getHeight() <= entity.getY()) {
                interactiveEntity.setY(entity.getY() - interactiveEntity.getHeight());
                interactiveEntity.setVelocityY(0);
            } else if(interactiveEntity.getPy() >= entity.getY() + entity.getHeight()) {
                interactiveEntity.setY(entity.getY() + entity.getHeight());
                //interactiveEntity.setVelocity(player.getVelocityX(), 0f);
                ((ItemEntity) interactiveEntity).setGrounded(true);
            }
        }
    }

    public void checkCollisions() {
        player.setGrounded(false);
        for (Tile tile: tiles) {
            if(collides(player, tile) && tile.isSolid()) {
                if (player.getPx() + player.getWidth() <= tile.getX()) {
                    player.setX(tile.getX() - player.getWidth());
                } else if (player.getPx() >= tile.getX() + tile.getWidth()) {
                    player.setX(tile.getX() + tile.getWidth());
                } else if (player.getPy() + player.getHeight() <= tile.getY()) {
                    player.setY(tile.getY() - player.getHeight());
                    player.setVelocityY(0);
                } else if (player.getPy() >= tile.getY() + tile.getHeight()) {
                    player.setY(tile.getY() + tile.getHeight());
                    //player.setVelocity(player.getVelocityX(), 0f);
                    player.setGrounded(true);
                }
            }

            checkEntityCollisions(tile);
            checkMobCollisions(tile);
            checkInteractiveEntityCollisions(tile);
        }

        for(Entity entity: entities) {
            checkMobCollisions(entity);
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

        checkItemCollisions();
    }

    public void checkItemCollisions() {
        Item selectedItem = playScreen.getHud().getInventoryDisplay().getSelectedItem();
        if(selectedItem != null) {
            if(selectedItem.isSwinging()) {
                if(selectedItem instanceof Tool) {
                    Tool selectedTool = (Tool) selectedItem;
                    for(Mob mob: mobs) {
                        if(Intersector.overlapConvexPolygons(selectedTool.getBoundingPolygon(), mob.getBoundingPolygon())) {
                            selectedTool.use(mob);
                        }
                    }
                }
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
        Vector3 hudScreenCoords = world.getPlayScreen().getHud().getCamera().unproject(new Vector3(screenX, screenY, 0));

        if(world.getPlayScreen().getHud().getInventoryDisplay().isTouched((int) hudScreenCoords.x, (int) hudScreenCoords.y)) {
            return;
        }

        Vector3 screenCoords = world.getCamera().unproject(new Vector3(screenX, screenY, 0));
        screenX = Math.round(screenCoords.x);
        screenY = Math.round(screenCoords.y);

        if(playScreen.getHud().getInventoryDisplay().getSelectedItem() != null) {
            playScreen.getHud().getInventoryDisplay().getSelectedItem().touchDown(screenX, screenY);
        }

        for(InteractiveEntity entity: interactiveEntities) {
            if(!entity.isActive())
                continue;

            if (touchingEntity(screenX, screenY, entity)) {
                entity.touchDown();
            }
        }

        Item selectedItem = playScreen.getHud().getInventoryDisplay().getSelectedItem();
        if(selectedItem != null) {
            if(selectedItem instanceof RangedWeapon) {
                ((RangedWeapon) selectedItem).shoot();
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
        if(entity instanceof Tile) {
            tiles.add((Tile) entity);
        } else if(entity instanceof InteractiveEntity) {
            if(world.isCreated()) {
                ((InteractiveEntity) entity).create();
            }
            interactiveEntities.add((InteractiveEntity) entity);
        } else if(entity instanceof Mob) {
            mobs.add((Mob) entity);
        } else {
            entities.add(entity);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public int getActiveMobCount() {
        return activeMobCount;
    }
}
