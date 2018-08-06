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
    private static final float PADDING_Y = 10;
    private static final float PADDING_Y_SMALL = 5;

    private HealthBar healthBar;
    private ManaBar manaBar;

    private Array<AbilityButton> abilityButtons;

    public StatusDisplay(Hud hud) {
        super(hud);
        
        x = 20;
        y = GaldulesFate.HEIGHT - 20;

        healthBar = new HealthBar(hud.getPlayScreen().getWorld().getPlayer().getMaxHealth());
        healthBar.setHealth(hud.getPlayScreen().getWorld().getPlayer().getHealth());
        healthBar.setPosition(x, y - PADDING_Y - healthBar.getHeight());

        manaBar = new ManaBar(hud.getPlayScreen().getWorld().getPlayer().getMaxMana());
        manaBar.setMana(hud.getPlayScreen().getWorld().getPlayer().getMana());
        manaBar.setPosition(x, y - PADDING_Y - healthBar.getHeight() - PADDING_Y - manaBar.getHeight());

        abilityButtons = new Array<>();
        for(int i = 0; i < 9; i++) {
            abilityButtons.add(new AbilityButton());
            abilityButtons.get(i).setText(Integer.toString((i + 1) % 10));
            abilityButtons.get(i).setPosition(
                    GaldulesFate.WIDTH - PADDING_X - ((64 + PADDING_X) * 9) + ((64 + PADDING_X) * i),
                    y - 64);
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

        for(int i = 0; i < abilityButtons.size; i++) {
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

    public void touchUp(int screenX, int screenY, int pointer, int button) {
        for(AbilityButton abilityButton: abilityButtons) {
            abilityButton.touchUp(screenX, screenY);
        }
    }
}
