package com.mengstudios.galdulesfate.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.entity.Entity;
import com.mengstudios.galdulesfate.entity.Player;
import com.mengstudios.galdulesfate.entity.tile.DirtTile;
import com.mengstudios.galdulesfate.entity.tile.GrassTile;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class World {
    private PlayScreen playScreen;

    private Player player;
    private Array<Entity> entities;

    public static final float GRAVITY = 8;

    private final int CHUNK_SIZE = 32;

    private int startX;
    private int endX;

    public World(PlayScreen playScreen) {
        this.playScreen = playScreen;

        player = new Player(playScreen, 16 * 64, 16 * 64 + 64);
        player.setHealth(player.getMaxHealth());
        player.setMana(player.getMaxMana());

        entities = new Array<>();

        startX = 0;

        generate(0);
    }

    public void update(float delta) {
        player.update(delta);
        for(Entity entity: entities) {
            entity.update(delta);
        }

        if(player.getX() < startX + (CHUNK_SIZE / 2) * 64) {
            generate(startX - CHUNK_SIZE * 64);
            Gdx.app.log("World", "generating terrain on the left");
        }
        if(player.getX() > endX - (CHUNK_SIZE / 2) * 64) {
            generate(endX);
            Gdx.app.log("World", "generating terrain on the right");
        }

        checkCollisions();
    }

    public void render(SpriteBatch batch) {
        batch.draw(Assets.SKY, playScreen.getCamera().position.x - playScreen.getCamera().viewportWidth / 2, playScreen.getCamera().position.y - playScreen.getCamera().viewportHeight / 2);

        player.draw(batch);
        for(Entity entity: entities) {
            entity.draw(batch);
        }
    }

    public void generate(int position) {
        if(position < startX) {
            startX = position;
        }
        if(position + 64 * CHUNK_SIZE > endX) {
            endX = position + 64 * CHUNK_SIZE;
        }

        for(int i = 0; i < CHUNK_SIZE; i++) {
            entities.add(new GrassTile(playScreen, position + 64 * i, 64 * (CHUNK_SIZE / 2 - 1)));
            for(int j = 0; j < CHUNK_SIZE / 2 - 1; j++) {
                entities.add(new DirtTile(playScreen, position + 64 * i, 64 * j));
            }
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
}
