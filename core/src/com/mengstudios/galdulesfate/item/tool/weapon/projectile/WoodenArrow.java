package com.mengstudios.galdulesfate.item.tool.weapon.projectile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class WoodenArrow extends Arrow {

    public WoodenArrow() {
        this(1);
    }

    public WoodenArrow(int count) {
        super(count);

        projectileDamage = 5;

        region = new TextureRegion(Assets.WOODEN_ARROW);
    }
}
