package com.mengstudios.galdulesfate.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mengstudios.galdulesfate.GaldulesFate;
import com.mengstudios.galdulesfate.entity.mob.Player;
import com.mengstudios.galdulesfate.hud.Hud;
import com.mengstudios.galdulesfate.world.World;

public class PlayScreen implements Screen {
    private GaldulesFate game;

    private Hud hud;

    private World world;

    public PlayScreen(GaldulesFate game) {
        this.game = game;

        world = new World(this);

        hud = new Hud(this);

        world.create();

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        if(GaldulesFate.mobile) {
            inputMultiplexer.addProcessor(hud.getMobileControls().getStage());
        }
        inputMultiplexer.addProcessor(world);
        inputMultiplexer.addProcessor(hud);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show() {

    }

    public void update(float delta) {
        hud.update(delta);

        world.update(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl20.glClearColor(1, 1, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(world.getCamera().combined);
        game.batch.begin();
        world.render(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.getCamera().combined);
        game.batch.begin();
        hud.draw(game.batch);
        game.batch.end();
        if(GaldulesFate.mobile) {
            hud.getMobileControls().draw(game.batch);
        }
    }

    @Override
    public void resize(int width, int height) {
        world.getViewport().update(width, height);
        hud.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public Player getPlayer() {
        return getWorld().getPlayer();
    }

    public World getWorld() {
        return world;
    }

    public Hud getHud() {
        return hud;
    }

    public GaldulesFate getGame() {
        return game;
    }
}
