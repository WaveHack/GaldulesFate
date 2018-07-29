package com.mengstudios.galdulesfate.item.tool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.Assets;

public class CopperPickaxe extends Pickaxe {
    public CopperPickaxe() {
        damage = 3;

        region = new TextureRegion(Assets.COPPER_PICKAXE);
        initBoundingPolygon();
    }
}
