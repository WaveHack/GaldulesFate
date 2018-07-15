package com.mengstudios.galdulesfate.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Text implements Disposable {

    private BitmapFont font;
    private GlyphLayout glyphLayout;

    private String text;

    private float x;
    private float y;

    public Text(BitmapFont font) {
        this.font = font;

        glyphLayout = new GlyphLayout();
    }

    public void draw(SpriteBatch batch) {
        if (text != null) {
            font.draw(batch, text, x, y);
        } else {
            Gdx.app.log("Text", "no text string set!");
        }
    }

    public void updateBounds(){
        glyphLayout.setText(font, text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getWidth() {
        return glyphLayout.width;
    }

    public float getHeight() {
        return glyphLayout.height;
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
