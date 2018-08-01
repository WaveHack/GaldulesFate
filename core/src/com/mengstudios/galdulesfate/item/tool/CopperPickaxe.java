package com.mengstudios.galdulesfate.item.tool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class CopperPickaxe extends Pickaxe {
    public CopperPickaxe(PlayScreen playScreen) {
        super(playScreen);

        damage = 3;

        region = new TextureRegion(Assets.COPPER_PICKAXE);
        initBoundingPolygon();
    }
}
