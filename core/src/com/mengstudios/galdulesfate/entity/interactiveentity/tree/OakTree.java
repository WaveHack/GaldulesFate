package com.mengstudios.galdulesfate.entity.interactiveentity.tree;

import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.item.OakWood;
import com.mengstudios.galdulesfate.world.World;

public class OakTree extends Tree {
    public OakTree(World world, float x, float y) {
        super(world, x, y);

        texture = Assets.OAK_TREE;
        choppedTexture = Assets.OAK_TREE_STUMP;
        dropWood = new OakWood();
        setRegion(Assets.OAK_TREE);
        setBounds(x, y, Assets.OAK_TREE.getWidth(), Assets.OAK_TREE.getHeight());
    }
}
