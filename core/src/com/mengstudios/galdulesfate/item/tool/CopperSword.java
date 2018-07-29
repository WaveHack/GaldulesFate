package com.mengstudios.galdulesfate.item.tool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.Assets;

public class CopperSword extends Sword {
    public CopperSword() {
        damage = 10;

        region = new TextureRegion(Assets.COPPER_SWORD);
        initBoundingPolygon();
    }
}
