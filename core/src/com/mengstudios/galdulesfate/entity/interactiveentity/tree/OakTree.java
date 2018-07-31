package com.mengstudios.galdulesfate.entity.interactiveentity.tree;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.item.resourceitem.OakWood;
import com.mengstudios.galdulesfate.world.World;

public class OakTree extends Tree {
    public OakTree(World world, float x, float y) {
        super(world, x, y);

        texture = Assets.OAK_TREE;
        choppedTexture = new TextureRegion(Assets.OAK_TREE, 0, Assets.OAK_TREE.getHeight() - 20, Assets.OAK_TREE.getWidth(), 20);
        dropWood = new OakWood();
        setRegion(Assets.OAK_TREE);
        setBounds(x, y, Assets.OAK_TREE.getWidth(), Assets.OAK_TREE.getHeight());
    }
}
