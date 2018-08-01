package com.mengstudios.galdulesfate.item.tool.weapon;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.item.tool.weapon.Sword;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class CopperSword extends Sword {
    public CopperSword(PlayScreen playScreen) {
        super(playScreen);

        damage = 10;

        region = new TextureRegion(Assets.COPPER_SWORD);
        initBoundingPolygon();
    }
}
