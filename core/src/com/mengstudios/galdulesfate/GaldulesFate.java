package com.mengstudios.galdulesfate;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mengstudios.galdulesfate.screen.TitleScreen;

public class GaldulesFate extends Game {
	public static final String TITLE = "Galdule's Fate";
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static boolean mobile;

	public SpriteBatch batch;

	FPSLogger fpsLogger = new FPSLogger();
	
	@Override
	public void create () {
        new Assets();

		batch = new SpriteBatch();
		setScreen(new TitleScreen(this));
	}

	@Override
	public void render () {
        super.render();
        fpsLogger.log();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}
