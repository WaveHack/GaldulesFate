package com.mengstudios.galdulesfate.world;

import com.badlogic.gdx.Gdx;
import com.mengstudios.galdulesfate.entity.interactiveentity.Anvil;
import com.mengstudios.galdulesfate.entity.interactiveentity.Furnace;
import com.mengstudios.galdulesfate.entity.interactiveentity.mineralrock.CopperRock;
import com.mengstudios.galdulesfate.entity.interactiveentity.tree.GiantTree;
import com.mengstudios.galdulesfate.entity.interactiveentity.tree.OakTree;
import com.mengstudios.galdulesfate.entity.tile.DirtTile;
import com.mengstudios.galdulesfate.entity.tile.GrassTile;

public class WorldGenerator {
    private World world;

    private int[] tiles = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                           0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                           0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                           0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                           0, 0, 5, 5, 0, 6, 0, 0, 4, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                           2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                           1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                           1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    private final int CHUNK_SIZE = 32;

    private int startX;
    private int endX;

    public WorldGenerator(World world) {
        this.world = world;

        startX = 0;

        init();
    }

    public void update(float delta) {
        if(world.getPlayer().getX() < startX + (CHUNK_SIZE / 2) * 64) {
            generate(startX - CHUNK_SIZE * 64);
            Gdx.app.log("WorldGenerator", "generating terrain on the left");
        }
        if(world.getPlayer().getX() > endX - (CHUNK_SIZE / 2) * 64) {
            generate(endX);
            Gdx.app.log("WorldGenerator", "generating terrain on the right");
        }
    }

    public void init() {
        startX = 0;
        endX = 64 * CHUNK_SIZE;

        for(int i = 0; i < CHUNK_SIZE; i++) {
            for(int j = 0; j < tiles.length / CHUNK_SIZE; j++) {
                switch (tiles[i + j * CHUNK_SIZE]) {
                    case 0:
                        break;
                    case 1:
                        world.getEntityManager().addEntity(new DirtTile(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j)));
                        break;
                    case 2:
                        world.getEntityManager().addEntity(new GrassTile(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j)));
                        break;
                    case 3:
                        world.getEntityManager().addEntity(new Anvil(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j)));
                        break;
                    case 4:
                        world.getEntityManager().addEntity(new Furnace(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j)));
                        break;
                    case 5:
                        world.getEntityManager().addEntity(new CopperRock(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j)));
                        break;
                    case 6:
                        world.getEntityManager().addEntity(new GiantTree(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j)));
                        break;
                }
            }
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
            world.getEntityManager().addEntity(new GrassTile(world, position + 64 * i, 64 * (tiles.length / CHUNK_SIZE - 5)));
            for(int j = 0; j < tiles.length / CHUNK_SIZE; j++) {
                world.getEntityManager().addEntity(new DirtTile(world, position + 64 * i, 64 * (tiles.length / CHUNK_SIZE - j - 6)));
            }
        }
    }
}
