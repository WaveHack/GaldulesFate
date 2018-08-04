package com.mengstudios.galdulesfate.hud.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mengstudios.galdulesfate.Assets;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.hud.AbilityButton;
import com.mengstudios.galdulesfate.hud.slider.HealthBar;
import com.mengstudios.galdulesfate.hud.Hud;
import com.mengstudios.galdulesfate.hud.slider.ManaBar;

public class StatusDisplay extends Ui {
    private static final float PADDING_X = 10;
    private static final float PADDING_X2 = 20;
    private static final float PADDING_Y = 10;

    private HealthBar healthBar;
    private ManaBar manaBar;

    private Array<AbilityButton> abilityButtons;

    public StatusDisplay(Hud hud) {
        super(hud);

        backgroundTexture = Assets.STATUS_TABLE;
        
        x = GaldulesFate.WIDTH / 2 - Assets.STATUS_TABLE.getWidth() / 2;
        y = 20;

        healthBar = new HealthBar(hud.getPlayScreen().getWorld().getPlayer().getMaxHealth());
        healthBar.setHealth(hud.getPlayScreen().getWorld().getPlayer().getHealth());
        healthBar.setPosition(x + PADDING_X, y + backgroundTexture.getHeight() - PADDING_Y - healthBar.getHeight());

        manaBar = new ManaBar(hud.getPlayScreen().getWorld().getPlayer().getMaxMana());
        manaBar.setMana(hud.getPlayScreen().getWorld().getPlayer().getMana());
        manaBar.setPosition(x + backgroundTexture.getWidth() - PADDING_X - manaBar.getWidth(), y + backgroundTexture.getHeight() - PADDING_Y - healthBar.getHeight());

        abilityButtons = new Array<>();
        for(int i = 0; i < 10; i++) {
            abilityButtons.add(new AbilityButton());
            abilityButtons.get(i).setText(Integer.toString((i + 1) % 10));
            abilityButtons.get(i).setPosition(x + PADDING_X + i * (abilityButtons.get(i).getWidth() + PADDING_X2), y + PADDING_Y);
        }
    }

    @Override
    public void update(float delta) {
        healthBar.setHealth(hud.getPlayScreen().getWorld().getPlayer().getHealth());
        healthBar.update(delta);

        manaBar.setMana(hud.getPlayScreen().getWorld().getPlayer().getMana());
        manaBar.update(delta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(new Color().set(1f, 1f, 1f, 0.5f));
        super.draw(batch);
        healthBar.draw(batch);
        manaBar.draw(batch);

        for(int i = 0; i < 10; i++) {
            abilityButtons.get(i).draw(batch);
        }
        batch.setColor(Color.WHITE);
    }

    public Array<AbilityButton> getAbilityButtons() {
        return abilityButtons;
    }

    public void touchDown(int screenX, int screenY, int pointer, int button) {
        for(AbilityButton abilityButton: abilityButtons) {
            abilityButton.touchDown(screenX, screenY);
        }
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for(AbilityButton abilityButton: abilityButtons) {
            abilityButton.touchUp(screenX, screenY);
        }
        return false;
    }
}
