package com.mengstudios.galdulesfate.item.tool.weapon;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.item.tool.weapon.projectile.Arrow;
import com.mengstudios.galdulesfate.item.tool.weapon.projectile.Projectile;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class Bow extends RangedWeapon {

    public Bow(PlayScreen playScreen) {
        super(playScreen);

        rangedDamage = 5;
        damage = 2;

        projectile = new Arrow();

        region = new TextureRegion(Assets.BOW);
        initBoundingPolygon();
    }

}
