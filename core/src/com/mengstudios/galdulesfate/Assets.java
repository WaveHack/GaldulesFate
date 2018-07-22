package com.mengstudios.galdulesfate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.HashMap;

public class Assets {
    private AssetManager assetManager;

    public static Texture TITLE_SCREEN_BACKGROUND;
    public static Texture SKY;

    public static Texture ANVIL;
    public static Texture COPPER_ORE_ROCKS;
    public static Texture EMPTY_ROCKS;
    public static Texture FURNACE;
    public static Texture PLAYER;

    public static Texture COPPER_BAR;
    public static Texture COPPER_ORE;
    public static Texture COPPER_PICKAXE;

    public static Texture DIRT_TILE;
    public static Texture GRASS_TILE;

    public static Texture ABILITY_BUTTON_DOWN;
    public static Texture ABILITY_BUTTON_UP;
    public static Texture BUTTON_DOWN;
    public static Texture BUTTON_UP;
    public static Texture FURNACE_ARROW;
    public static Texture FURNACE_UI;
    public static Texture HEALTH_BAR_BACK;
    public static Texture HEALTH_BAR_FRONT;
    public static Texture HIGHLIGHT;
    public static Texture INVENTORY;
    public static Texture MANA_BAR_BACK;
    public static Texture MANA_BAR_FRONT;
    public static Texture STATUS_TABLE;
    public static Texture TOUCHPAD_BACKGROUND;
    public static Texture TOUCHPAD_KNOB;

    public static Music BACKGROUD_MUSIC_1;
    public static Sound FOOTSTEP_DIRT_SOUND;
    public static Sound FURNACE_SOUND;
    public static Sound PICKAXE_SOUND;

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    public static BitmapFont TITLE_FONT;
    public static BitmapFont BUTTON_FONT;
    public static BitmapFont ABILITY_BUTTON_FONT;
    public static BitmapFont SLIDER_FONT;
    public static BitmapFont STACK_FONT;

    public Assets() {
        assetManager = new AssetManager();
        assetManager.load("title_screen_background.jpg", Texture.class);
        assetManager.load("sky.png", Texture.class);
        assetManager.load("entity/copper_ore_rocks.png", Texture.class);
        assetManager.load("entity/anvil.png", Texture.class);
        assetManager.load("entity/empty_rocks.png", Texture.class);
        assetManager.load("entity/furnace.png", Texture.class);
        assetManager.load("entity/player.png", Texture.class);
        assetManager.load("item/copper_bar.png", Texture.class);
        assetManager.load("item/copper_ore.png", Texture.class);
        assetManager.load("item/copper_pickaxe.png", Texture.class);
        assetManager.load("tile/dirt_tile.png", Texture.class);
        assetManager.load("tile/grass_tile.png", Texture.class);
        assetManager.load("ui/ability_button_down.png", Texture.class);
        assetManager.load("ui/ability_button_up.png", Texture.class);
        assetManager.load("ui/button_down.png", Texture.class);
        assetManager.load("ui/button_up.png", Texture.class);
        assetManager.load("ui/furnace_arrow.png", Texture.class);
        assetManager.load("ui/furnace_ui.png", Texture.class);
        assetManager.load("ui/health_bar_back.png", Texture.class);
        assetManager.load("ui/health_bar_front.png", Texture.class);
        assetManager.load("ui/highlight.png", Texture.class);
        assetManager.load("ui/inventory.png", Texture.class);
        assetManager.load("ui/mana_bar_back.png", Texture.class);
        assetManager.load("ui/mana_bar_front.png", Texture.class);
        assetManager.load("ui/status_table.png", Texture.class);
        assetManager.load("ui/touchpad_background.png", Texture.class);
        assetManager.load("ui/touchpad_knob.png", Texture.class);
        assetManager.load("music/background_music_1.mp3", Music.class);
        assetManager.load("sound/footstep_dirt_sound.mp3", Sound.class);
        assetManager.load("sound/furnace_sound.ogg", Sound.class);
        assetManager.load("sound/pickaxe_sound.ogg", Sound.class);
        assetManager.finishLoading();

        TITLE_SCREEN_BACKGROUND = assetManager.get("title_screen_background.jpg");
        SKY = assetManager.get("sky.png");

        ANVIL = assetManager.get("entity/anvil.png");
        COPPER_ORE_ROCKS = assetManager.get("entity/copper_ore_rocks.png");
        EMPTY_ROCKS = assetManager.get("entity/empty_rocks.png");
        FURNACE = assetManager.get("entity/furnace.png");
        PLAYER = assetManager.get("entity/player.png");

        COPPER_BAR = assetManager.get("item/copper_bar.png");
        COPPER_ORE = assetManager.get("item/copper_ore.png");
        COPPER_PICKAXE = assetManager.get("item/copper_pickaxe.png");

        DIRT_TILE = assetManager.get("tile/dirt_tile.png");
        GRASS_TILE = assetManager.get("tile/grass_tile.png");

        ABILITY_BUTTON_DOWN = assetManager.get("ui/ability_button_down.png");
        ABILITY_BUTTON_UP = assetManager.get("ui/ability_button_up.png");
        BUTTON_DOWN = assetManager.get("ui/button_down.png");
        BUTTON_UP = assetManager.get("ui/button_up.png");
        FURNACE_ARROW = assetManager.get("ui/furnace_arrow.png");
        FURNACE_UI = assetManager.get("ui/furnace_ui.png");
        HEALTH_BAR_BACK = assetManager.get("ui/health_bar_back.png");
        HEALTH_BAR_FRONT = assetManager.get("ui/health_bar_front.png");
        HIGHLIGHT = assetManager.get("ui/highlight.png");
        INVENTORY = assetManager.get("ui/inventory.png");
        MANA_BAR_BACK = assetManager.get("ui/mana_bar_back.png");
        MANA_BAR_FRONT = assetManager.get("ui/mana_bar_front.png");
        STATUS_TABLE = assetManager.get("ui/status_table.png");
        TOUCHPAD_BACKGROUND = assetManager.get("ui/touchpad_background.png");
        TOUCHPAD_KNOB = assetManager.get("ui/touchpad_knob.png");

        BACKGROUD_MUSIC_1 = assetManager.get("music/background_music_1.mp3");
        FOOTSTEP_DIRT_SOUND = assetManager.get("sound/footstep_dirt_sound.mp3");
        FURNACE_SOUND = assetManager.get("sound/furnace_sound.ogg");
        PICKAXE_SOUND = assetManager.get("sound/pickaxe_sound.ogg");

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

        fontParameter.size = 14;

        STACK_FONT = fontGenerator.generateFont(fontParameter);
    }
}
