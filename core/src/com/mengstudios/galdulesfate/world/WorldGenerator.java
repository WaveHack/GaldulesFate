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

    private final int CHUNK_SIZE = 32;

    private int startX;
    private int endX;

    public WorldGenerator(World world) {
        this.world = world;

        startX = 0;

        generate(0);
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

    public void generate(int position) {
        if(position < startX) {
            startX = position;
        }
        if(position + 64 * CHUNK_SIZE > endX) {
            endX = position + 64 * CHUNK_SIZE;
        }

        for(int i = 0; i < CHUNK_SIZE; i++) {
            world.getEntityManager().addEntity(new GrassTile(world, position + 64 * i, 64 * (CHUNK_SIZE / 2 - 1)));
            for(int j = 0; j < CHUNK_SIZE / 2 - 1; j++) {
                world.getEntityManager().addEntity(new DirtTile(world, position + 64 * i, 64 * j));
            }
        }

        world.getEntityManager().addEntity(new GiantTree(world, position + 64, 64 * CHUNK_SIZE / 2));
        world.getEntityManager().addEntity(new CopperRock(world, position, 64 * CHUNK_SIZE / 2));
        Furnace furnace = new Furnace(world, position + 64 * 4, 64 * CHUNK_SIZE / 2);
        if(world.isCreated()) {
            furnace.create();
        }
        world.getEntityManager().addEntity(furnace);

        Anvil anvil = new Anvil(world, position + 64 * 6, 64 * CHUNK_SIZE / 2);
        if(world.isCreated()) {
            anvil.create();
        }
        world.getEntityManager().addEntity(anvil);
    }
}
