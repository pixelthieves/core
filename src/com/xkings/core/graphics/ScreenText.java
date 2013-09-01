package com.xkings.core.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class ScreenText {
    private static final int OFFSET = 30;
    private final BitmapFont.HAlignment orientation;
    private BitmapFont font;
    private List<String> buffer = new ArrayList<String>();

    public ScreenText(BitmapFont font, BitmapFont.HAlignment orientation) {
        this.font = font;
        this.orientation = orientation;
        font.setColor(Color.BLACK);
    }

    public void addInfo(String info) {
        this.buffer.add(info);
    }

    public void render(SpriteBatch sb) {
        sb.begin();
        for (int i = 0; i < buffer.size(); i++) {
            float x = orientation == BitmapFont.HAlignment.RIGHT ? Gdx.graphics.getWidth() - OFFSET : OFFSET;
            font.drawMultiLine(sb, buffer.get(i), x, Gdx.graphics.getHeight() - OFFSET * (i + 1), 0, orientation);
        }
        buffer.clear();
        sb.end();
    }
}
