package com.mengstudios.galdulesfate.item.tool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class CopperAxe extends Axe {
    public CopperAxe(PlayScreen playScreen) {
        super(playScreen);
        damage = 6;

        region = new TextureRegion(Assets.COPPER_AXE);
        initBoundingPolygon();
    }
}
