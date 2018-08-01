package com.mengstudios.galdulesfate.item.tool.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mengstudios.galdulesfate.entity.ProjectileEntity;
import com.mengstudios.galdulesfate.item.tool.weapon.projectile.Projectile;
import com.mengstudios.galdulesfate.screen.PlayScreen;

public class RangedWeapon extends Weapon {
    Projectile projectile;
    int rangedDamage;
    float unprojectedRotation;

    public RangedWeapon(PlayScreen playScreen) {
        super(playScreen);
    }

    @Override
    public void update(float delta) {
        if(playScreen.getWorld().isTouchHeld()) {
            float centerX = playScreen.getWorld().getCamera().position.x;
            float centerY = playScreen.getWorld().getCamera().position.y - 50;
            if(playScreen.getPlayer().isFlipX()) {
                if (playScreen.getWorld().getTouchHeldY() >= centerY) {
                    unprojectedRotation = (float) Math.toDegrees(Math.acos((playScreen.getWorld().getTouchHeldX() - centerX)
                            / (Vector2.dst(centerX, centerY, playScreen.getWorld().getTouchHeldX(), playScreen.getWorld().getTouchHeldY()))));
                    rotation = unprojectedRotation + 225;
                } else {
                    unprojectedRotation = (float) Math.toDegrees(-Math.acos((playScreen.getWorld().getTouchHeldX() - centerX)
                            / (Vector2.dst(centerX, centerY, playScreen.getWorld().getTouchHeldX(), playScreen.getWorld().getTouchHeldY()))));
                    rotation = unprojectedRotation + 225;
                }
            } else {
                if (playScreen.getWorld().getTouchHeldY() >= centerY) {
                    unprojectedRotation = (float) Math.toDegrees(Math.acos((playScreen.getWorld().getTouchHeldX() - centerX)
                            / (Vector2.dst(centerX, centerY, playScreen.getWorld().getTouchHeldX(), playScreen.getWorld().getTouchHeldY()))));
                    rotation = -unprojectedRotation + 45;
                } else {
                    unprojectedRotation = (float) Math.toDegrees(-Math.acos((playScreen.getWorld().getTouchHeldX() - centerX)
                            / (Vector2.dst(centerX, centerY, playScreen.getWorld().getTouchHeldX(), playScreen.getWorld().getTouchHeldY()))));
                    rotation = -unprojectedRotation + 45;
                }
            }
        } else {
            //super.update(delta);
        }
    }

    public void shoot() {
        update(Gdx.graphics.getDeltaTime());

        if(playScreen.getPlayer().getInventory().hasResourceItem(projectile, 1)) {
            ((Projectile) playScreen.getPlayer().getInventory().findResourceItem(projectile)).setRangedDamage(rangedDamage);
            playScreen.getWorld().getEntityManager().addEntity(
                    new ProjectileEntity(playScreen.getWorld(),
                            (Projectile) playScreen.getPlayer().getInventory().findResourceItem(projectile),
                            playScreen.getPlayer().getX(),
                            playScreen.getPlayer().getY(),
                            (float) (Math.cos(Math.toRadians(unprojectedRotation)) * 600),
                            (float) (Math.sin(Math.toRadians(unprojectedRotation)) * 600)));
            playScreen.getPlayer().getInventory().removeResource(projectile, 1);
        }
    }
}
