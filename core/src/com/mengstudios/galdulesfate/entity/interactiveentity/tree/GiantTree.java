package com.mengstudios.galdulesfate.entity.interactiveentity.tree;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.item.resourceitem.OakWood;
import com.mengstudios.galdulesfate.world.World;

public class GiantTree extends Tree {
    public GiantTree(World world, float x, float y) {
        super(world, x, y);

        texture = Assets.GIANT_TREE;
        choppedTexture = new TextureRegion(Assets.GIANT_TREE, 0, Assets.GIANT_TREE.getHeight() - 20, Assets.GIANT_TREE.getWidth(), 20);
        dropWood = new OakWood();
        setRegion(Assets.GIANT_TREE);
        setBounds(x, y, Assets.GIANT_TREE.getWidth(), Assets.GIANT_TREE.getHeight());
    }
}
