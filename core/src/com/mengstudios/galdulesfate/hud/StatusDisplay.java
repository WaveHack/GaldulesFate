package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;

public class StatusDisplay {
    private Hud hud;

    private Texture statusTable;

    private static final float STATUS_TABLE_X = GaldulesFate.WIDTH / 2 - Assets.STATUS_TABLE.getWidth() / 2;
    private static final float STATUS_TABLE_Y = 20;
    private static final float PADDING_X = 10;
    private static final float PADDING_X2 = 20;
    private static final float PADDING_Y = 10;

    private HealthBar healthBar;
    private ManaBar manaBar;

    private Array<AbilityButton> abilityButtons;

    public StatusDisplay(Hud hud) {
        this.hud = hud;

        statusTable = Assets.STATUS_TABLE;

        healthBar = new HealthBar(hud.getPlayScreen().getWorld().getPlayer().getMaxHealth());
        healthBar.setHealth(hud.getPlayScreen().getWorld().getPlayer().getHealth());
        healthBar.setPosition(STATUS_TABLE_X + PADDING_X, STATUS_TABLE_Y + statusTable.getHeight() - PADDING_Y - healthBar.getHeight());

        manaBar = new ManaBar(hud.getPlayScreen().getWorld().getPlayer().getMaxMana());
        manaBar.setMana(hud.getPlayScreen().getWorld().getPlayer().getMana());
        manaBar.setPosition(STATUS_TABLE_X + statusTable.getWidth() - PADDING_X - manaBar.getWidth(), STATUS_TABLE_Y + statusTable.getHeight() - PADDING_Y - healthBar.getHeight());

        abilityButtons = new Array<>();
        for(int i = 0; i < 10; i++) {
            abilityButtons.add(new AbilityButton());
            abilityButtons.get(i).setText(Integer.toString((i + 1) % 10));
            abilityButtons.get(i).setPosition(STATUS_TABLE_X + PADDING_X + i * (abilityButtons.get(i).getWidth() + PADDING_X2), STATUS_TABLE_Y + PADDING_Y);
        }
    }

    public void update(float delta) {
        healthBar.setHealth(hud.getPlayScreen().getWorld().getPlayer().getHealth());
        healthBar.update(delta);

        manaBar.setMana(hud.getPlayScreen().getWorld().getPlayer().getMana());
        manaBar.update(delta);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(statusTable, STATUS_TABLE_X, STATUS_TABLE_Y);
        healthBar.draw(batch);
        manaBar.draw(batch);

        for(int i = 0; i < 10; i++) {
            abilityButtons.get(i).draw(batch);
        }
    }

    public Array<AbilityButton> getAbilityButtons() {
        return abilityButtons;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for(AbilityButton abilityButton: abilityButtons) {
            abilityButton.touchDown(screenX, screenY);
        }
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for(AbilityButton abilityButton: abilityButtons) {
            abilityButton.touchUp(screenX, screenY);
        }
        return false;
    }
}
