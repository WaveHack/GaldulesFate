package com.mengstudios.galdulesfate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {
    private AssetManager assetManager;

    public static Texture TITLE_SCREEN_BACKGROUND;
    public static Texture SKY;

    public static Texture ANVIL;
    public static Texture CAMPFIRE;
    public static Texture COPPER_ORE_ROCKS;
    public static Texture COW;
    public static Texture EMPTY_ROCKS;
    public static Texture FURNACE;
    public static Texture GIANT_TREE;
    public static Texture OAK_TREE;
    public static Texture PIG;
    public static Texture PLAYER;
    public static Texture WORKBENCH;

    public static Texture BOW;
    public static Texture COOKED_BACON;
    public static Texture COOKED_MEAT;
    public static Texture COPPER_BAR;
    public static Texture COPPER_ORE;
    public static Texture COPPER_AXE;
    public static Texture COPPER_PICKAXE;
    public static Texture COPPER_SWORD;
    public static Texture OAK_WOOD;
    public static Texture RAW_BACON;
    public static Texture RAW_BEEF;
    public static Texture WOODEN_ARROW;

    public static Texture DIRT_TILE;
    public static Texture GRASS_TILE;

    public static Texture ABILITY_BUTTON_DOWN;
    public static Texture ABILITY_BUTTON_UP;
    public static Texture BUTTON_DOWN;
    public static Texture BUTTON_UP;
    public static Texture UI_BOX;
    public static Texture FURNACE_UI;
    public static Texture HEALTH_BAR_BACK;
    public static Texture HEALTH_BAR_FRONT;
    public static Texture HIGHLIGHT;
    public static Texture INVENTORY_BUTTON;
    public static Texture MANA_BAR_BACK;
    public static Texture MANA_BAR_FRONT;
    public static Texture PROGRESS_BAR_SHORT_BACK;
    public static Texture PROGRESS_BAR_SHORT_FRONT;
    public static Texture STATUS_TABLE;
    public static Texture TOUCHPAD_BACKGROUND;
    public static Texture TOUCHPAD_KNOB;

    public static Music BACKGROUD_MUSIC_1;
    public static Music SOLILOQUY;

    public static Sound ANVIL_SOUND;
    public static Sound AXE_SOUND;
    public static Sound COW_HURT_SOUND;
    public static Sound FOOTSTEP_DIRT_SOUND;
    public static Sound FURNACE_SOUND;
    public static Sound WORKBENCH_SOUND;
    public static Sound PICKAXE_SOUND;
    public static Sound PIG_DEATH_SOUND;
    public static Sound PIG_HURT_1_SOUND;
    //public static Sound PIG_HURT_2_SOUND;

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    public static BitmapFont TITLE_FONT;
    public static BitmapFont BUTTON_FONT;
    public static BitmapFont ABILITY_BUTTON_FONT;
    public static BitmapFont SLIDER_FONT;
    public static BitmapFont STACK_FONT;

    public Assets() {
        assetManager = new AssetManager();
        assetManager.load("title_screen_background.png", Texture.class);
        assetManager.load("sky.png", Texture.class);

        assetManager.load("entity/interactiveentity/anvil.png", Texture.class);
        assetManager.load("entity/interactiveentity/campfire.png", Texture.class);
        assetManager.load("entity/interactiveentity/copper_ore_rocks.png", Texture.class);
        assetManager.load("entity/interactiveentity/empty_rocks.png", Texture.class);
        assetManager.load("entity/interactiveentity/furnace.png", Texture.class);
        assetManager.load("entity/interactiveentity/giant_tree.png", Texture.class);
        assetManager.load("entity/interactiveentity/oak_tree.png", Texture.class);
        assetManager.load("entity/interactiveentity/workbench.png", Texture.class);
        assetManager.load("entity/mob/cow.png", Texture.class);
        assetManager.load("entity/mob/pig.png", Texture.class);
        assetManager.load("entity/mob/player.png", Texture.class);

        assetManager.load("item/bow.png", Texture.class);
        assetManager.load("item/cooked_bacon.png", Texture.class);
        assetManager.load("item/cooked_meat.png", Texture.class);
        assetManager.load("item/copper_bar.png", Texture.class);
        assetManager.load("item/copper_ore.png", Texture.class);
        assetManager.load("item/copper_axe.png", Texture.class);
        assetManager.load("item/copper_pickaxe.png", Texture.class);
        assetManager.load("item/copper_sword.png", Texture.class);
        assetManager.load("item/oak_wood.png", Texture.class);
        assetManager.load("item/raw_bacon.png", Texture.class);
        assetManager.load("item/raw_beef.png", Texture.class);
        assetManager.load("item/wooden_arrow.png", Texture.class);

        assetManager.load("tile/dirt_tile.png", Texture.class);
        assetManager.load("tile/grass_tile.png", Texture.class);

        assetManager.load("ui/button/ability_button_down.png", Texture.class);
        assetManager.load("ui/button/ability_button_up.png", Texture.class);
        assetManager.load("ui/button/button_down.png", Texture.class);
        assetManager.load("ui/button/button_up.png", Texture.class);
        assetManager.load("ui/slider/health_bar_back.png", Texture.class);
        assetManager.load("ui/slider/health_bar_front.png", Texture.class);
        assetManager.load("ui/slider/mana_bar_back.png", Texture.class);
        assetManager.load("ui/slider/mana_bar_front.png", Texture.class);
        assetManager.load("ui/slider/progress_bar_short_back.png", Texture.class);
        assetManager.load("ui/slider/progress_bar_short_front.png", Texture.class);
        assetManager.load("ui/ui_box.png", Texture.class);
        assetManager.load("ui/furnace_ui.png", Texture.class);
        assetManager.load("ui/highlight.png", Texture.class);
        assetManager.load("ui/inventory_button.png", Texture.class);
        assetManager.load("ui/status_table.png", Texture.class);
        assetManager.load("ui/touchpad_background.png", Texture.class);
        assetManager.load("ui/touchpad_knob.png", Texture.class);

        assetManager.load("music/background_music_1.mp3", Music.class);
        assetManager.load("music/soliloquy.mp3", Music.class);

        assetManager.load("sound/anvil_sound.ogg", Sound.class);
        assetManager.load("sound/axe_sound.ogg", Sound.class);
        assetManager.load("sound/cow_hurt.ogg", Sound.class);
        assetManager.load("sound/footstep_dirt_sound.ogg", Sound.class);
        assetManager.load("sound/furnace_sound.ogg", Sound.class);
        assetManager.load("sound/workbench_sound.ogg", Sound.class);
        assetManager.load("sound/pickaxe_sound.ogg", Sound.class);
        assetManager.load("sound/pig_death.ogg", Sound.class);
        assetManager.load("sound/pig_hurt_1.ogg", Sound.class);
        //assetManager.load("sound/pig_hurt_2.ogg", Sound.class);
        assetManager.finishLoading();

        TITLE_SCREEN_BACKGROUND = assetManager.get("title_screen_background.png");
        SKY = assetManager.get("sky.png");

        ANVIL = assetManager.get("entity/interactiveentity/anvil.png");
        CAMPFIRE = assetManager.get("entity/interactiveentity/campfire.png");
        COPPER_ORE_ROCKS = assetManager.get("entity/interactiveentity/copper_ore_rocks.png");
        EMPTY_ROCKS = assetManager.get("entity/interactiveentity/empty_rocks.png");
        FURNACE = assetManager.get("entity/interactiveentity/furnace.png");
        GIANT_TREE = assetManager.get("entity/interactiveentity/giant_tree.png");
        OAK_TREE = assetManager.get("entity/interactiveentity/oak_tree.png");
        WORKBENCH = assetManager.get("entity/interactiveentity/workbench.png");
        COW = assetManager.get("entity/mob/cow.png");
        PIG = assetManager.get("entity/mob/pig.png");
        PLAYER = assetManager.get("entity/mob/player.png");

        BOW = assetManager.get("item/bow.png");
        COOKED_BACON = assetManager.get("item/cooked_bacon.png");
        COOKED_MEAT = assetManager.get("item/cooked_meat.png");
        COPPER_BAR = assetManager.get("item/copper_bar.png");
        COPPER_ORE = assetManager.get("item/copper_ore.png");
        COPPER_AXE = assetManager.get("item/copper_axe.png");
        COPPER_PICKAXE = assetManager.get("item/copper_pickaxe.png");
        COPPER_SWORD = assetManager.get("item/copper_sword.png");
        OAK_WOOD = assetManager.get("item/oak_wood.png");
        RAW_BACON = assetManager.get("item/raw_bacon.png");
        RAW_BEEF = assetManager.get("item/raw_beef.png");
        WOODEN_ARROW = assetManager.get("item/wooden_arrow.png");

        DIRT_TILE = assetManager.get("tile/dirt_tile.png");
        GRASS_TILE = assetManager.get("tile/grass_tile.png");

        ABILITY_BUTTON_DOWN = assetManager.get("ui/button/ability_button_down.png");
        ABILITY_BUTTON_UP = assetManager.get("ui/button/ability_button_up.png");
        BUTTON_DOWN = assetManager.get("ui/button/button_down.png");
        BUTTON_UP = assetManager.get("ui/button/button_up.png");
        HEALTH_BAR_BACK = assetManager.get("ui/slider/health_bar_back.png");
        HEALTH_BAR_FRONT = assetManager.get("ui/slider/health_bar_front.png");
        MANA_BAR_BACK = assetManager.get("ui/slider/mana_bar_back.png");
        MANA_BAR_FRONT = assetManager.get("ui/slider/mana_bar_front.png");
        PROGRESS_BAR_SHORT_BACK = assetManager.get("ui/slider/progress_bar_short_back.png");
        PROGRESS_BAR_SHORT_FRONT = assetManager.get("ui/slider/progress_bar_short_front.png");
        UI_BOX = assetManager.get("ui/ui_box.png");
        FURNACE_UI = assetManager.get("ui/furnace_ui.png");
        HIGHLIGHT = assetManager.get("ui/highlight.png");
        INVENTORY_BUTTON = assetManager.get("ui/inventory_button.png");
        STATUS_TABLE = assetManager.get("ui/status_table.png");
        TOUCHPAD_BACKGROUND = assetManager.get("ui/touchpad_background.png");
        TOUCHPAD_KNOB = assetManager.get("ui/touchpad_knob.png");

        BACKGROUD_MUSIC_1 = assetManager.get("music/background_music_1.mp3");
        SOLILOQUY = assetManager.get("music/soliloquy.mp3");

        ANVIL_SOUND = assetManager.get("sound/anvil_sound.ogg");
        AXE_SOUND = assetManager.get("sound/axe_sound.ogg");
        COW_HURT_SOUND = assetManager.get("sound/cow_hurt.ogg");
        FOOTSTEP_DIRT_SOUND = assetManager.get("sound/footstep_dirt_sound.ogg");
        FURNACE_SOUND = assetManager.get("sound/furnace_sound.ogg");
        WORKBENCH_SOUND = assetManager.get("sound/workbench_sound.ogg");
        PICKAXE_SOUND = assetManager.get("sound/pickaxe_sound.ogg");
        PIG_DEATH_SOUND = assetManager.get("sound/pig_death.ogg");
        PIG_HURT_1_SOUND = assetManager.get("sound/pig_hurt_1.ogg");
        //PIG_HURT_2_SOUND = assetManager.get("sound/pig_hurt_2.ogg");

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("OldWizard.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.color = new Color(Color.WHITE);
        fontParameter.size = 24;

        BUTTON_FONT = fontGenerator.generateFont(fontParameter);

        fontParameter.color = new Color(Color.BLACK);
        fontParameter.size = 72;

        TITLE_FONT = fontGenerator.generateFont(fontParameter);

        fontParameter.size = 12;

        SLIDER_FONT = fontGenerator.generateFont(fontParameter);
        ABILITY_BUTTON_FONT = SLIDER_FONT;

        fontParameter.size = 14;

        STACK_FONT = fontGenerator.generateFont(fontParameter);
    }
}
