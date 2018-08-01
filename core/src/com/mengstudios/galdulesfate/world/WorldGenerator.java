package com.mengstudios.galdulesfate.world;

import com.badlogic.gdx.Gdx;
import com.mengstudios.galdulesfate.entity.interactiveentity.Anvil;
import com.mengstudios.galdulesfate.entity.interactiveentity.Campfire;
import com.mengstudios.galdulesfate.entity.interactiveentity.Furnace;
import com.mengstudios.galdulesfate.entity.interactiveentity.Workbench;
import com.mengstudios.galdulesfate.entity.interactiveentity.mineralrock.CopperRock;
import com.mengstudios.galdulesfate.entity.interactiveentity.tree.GiantTree;
import com.mengstudios.galdulesfate.entity.interactiveentity.tree.OakTree;
import com.mengstudios.galdulesfate.entity.mob.Cow;
import com.mengstudios.galdulesfate.entity.mob.Mob;
import com.mengstudios.galdulesfate.entity.mob.Pig;
import com.mengstudios.galdulesfate.entity.mob.Spawner;
import com.mengstudios.galdulesfate.entity.tile.DirtTile;
import com.mengstudios.galdulesfate.entity.tile.GrassTile;

public class WorldGenerator {
    private World world;

    private int[] tiles = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                           0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                           0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                           0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                           0, 0, 10, 10, 0, 11, 0, 0, 4, 0, 3, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 110, 111, 0, 0, 0, 0, 0, 0, 0, 0,
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
                        world.getEntityManager().addEntity(new Workbench(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j)));
                        break;
                    case 9:
                        world.getEntityManager().addEntity(new Campfire(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j)));
                        break;
                    case 10:
                        world.getEntityManager().addEntity(new CopperRock(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j)));
                        break;
                    case 11:
                        world.getEntityManager().addEntity(new GiantTree(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j)));
                        break;
                    case 30:
                        world.getEntityManager().addEntity(new Pig(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j) + 64));
                        break;
                    case 31:
                        world.getEntityManager().addEntity(new Cow(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j) + 64));
                        break;
                    case 110:
                        world.getEntityManager().addEntity(new Spawner(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j), Mob.MobType.PIG));
                        break;
                    case 111:
                        world.getEntityManager().addEntity(new Spawner(world, 64 * i, 64 * (tiles.length / CHUNK_SIZE - j), Mob.MobType.COW));
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
