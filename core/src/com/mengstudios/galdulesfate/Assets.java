package com.mengstudios.galdulesfate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {
    private AssetManager assetManager;

    public static Texture TITLE_SCREEN_BACKGROUND;
    public static Texture SKY;

    public static Texture PLAYER;

    public static Texture COPPER_PICKAXE;

    public static Texture DIRT_TILE;
    public static Texture GRASS_TILE;

    public static Texture ABILITY_BUTTON_DOWN;
    public static Texture ABILITY_BUTTON_UP;
    public static Texture BUTTON_DOWN;
    public static Texture BUTTON_UP;
    public static Texture HEALTH_BAR_BACK;
    public static Texture HEALTH_BAR_FRONT;
    public static Texture INVENTORY;
    public static Texture MANA_BAR_BACK;
    public static Texture MANA_BAR_FRONT;
    public static Texture STATUS_TABLE;
    public static Texture HIGHLIGHT;

    public static Music BACKGROUD_MUSIC_1;

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    public static BitmapFont TITLE_FONT;
    public static BitmapFont BUTTON_FONT;
    public static BitmapFont ABILITY_BUTTON_FONT;
    public static BitmapFont SLIDER_FONT;

    public Assets() {
        assetManager = new AssetManager();
        assetManager.load("title_screen_background.jpg", Texture.class);
        assetManager.load("sky.png", Texture.class);
        assetManager.load("entity/player.png", Texture.class);
        assetManager.load("item/copper_pickaxe.png", Texture.class);
        assetManager.load("tile/dirt_tile.png", Texture.class);
        assetManager.load("tile/grass_tile.png", Texture.class);
        assetManager.load("ui/ability_button_down.png", Texture.class);
        assetManager.load("ui/ability_button_up.png", Texture.class);
        assetManager.load("ui/button_down.png", Texture.class);
        assetManager.load("ui/button_up.png", Texture.class);
        assetManager.load("ui/health_bar_back.png", Texture.class);
        assetManager.load("ui/health_bar_front.png", Texture.class);
        assetManager.load("ui/inventory.png", Texture.class);
        assetManager.load("ui/mana_bar_back.png", Texture.class);
        assetManager.load("ui/mana_bar_front.png", Texture.class);
        assetManager.load("ui/status_table.png", Texture.class);
        assetManager.load("ui/highlight.png", Texture.class);
        assetManager.load("music/background_music_1.mp3", Music.class);
        assetManager.finishLoading();

        TITLE_SCREEN_BACKGROUND = assetManager.get("title_screen_background.jpg");
        SKY = assetManager.get("sky.png");

        PLAYER = assetManager.get("entity/player.png");

        COPPER_PICKAXE = assetManager.get("item/copper_pickaxe.png");

        DIRT_TILE = assetManager.get("tile/dirt_tile.png");
        GRASS_TILE = assetManager.get("tile/grass_tile.png");

        ABILITY_BUTTON_DOWN = assetManager.get("ui/ability_button_down.png");
        ABILITY_BUTTON_UP = assetManager.get("ui/ability_button_up.png");
        BUTTON_DOWN = assetManager.get("ui/button_down.png");
        BUTTON_UP = assetManager.get("ui/button_up.png");
        HEALTH_BAR_BACK = assetManager.get("ui/health_bar_back.png");
        HEALTH_BAR_FRONT = assetManager.get("ui/health_bar_front.png");
        HIGHLIGHT = assetManager.get("ui/highlight.png");
        INVENTORY = assetManager.get("ui/inventory.png");
        MANA_BAR_BACK = assetManager.get("ui/mana_bar_back.png");
        MANA_BAR_FRONT = assetManager.get("ui/mana_bar_front.png");
        STATUS_TABLE = assetManager.get("ui/status_table.png");

        BACKGROUD_MUSIC_1 = assetManager.get("music/background_music_1.mp3");

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("OldWizard.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.color = new Color(Color.WHITE);
        fontParameter.size = 24;

        BUTTON_FONT = fontGenerator.generateFont(fontParameter);

        fontParameter.color = new Color(Color.BLACK);
        fontParameter.size = 72;

        TITLE_FONT = fontGenerator.generateFont(fontParameter);

        fontParameter.size = 10;

        ABILITY_BUTTON_FONT = fontGenerator.generateFont(fontParameter);

        fontParameter.size = 12;

        SLIDER_FONT = fontGenerator.generateFont(fontParameter);
    }
}
