package com.mengstudios.galdulesfate.item.tool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.Assets;

public class CopperAxe extends Axe {
    public CopperAxe() {
        damage = 6;

        region = new TextureRegion(Assets.COPPER_AXE);
        initBoundingPolygon();
    }
}
