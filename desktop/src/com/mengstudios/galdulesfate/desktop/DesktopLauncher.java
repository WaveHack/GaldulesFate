package com.mengstudios.galdulesfate.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mengstudios.galdulesfate.GaldulesFate;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = GaldulesFate.TITLE;
		config.width = GaldulesFate.WIDTH;
		config.height = GaldulesFate.HEIGHT;

		new LwjglApplication(new GaldulesFate(), config);
	}
}
