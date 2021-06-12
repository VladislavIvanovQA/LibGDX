package ru.gb.sprite;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.gb.base.ScaledButton;
import ru.gb.math.Rect;
import ru.gb.screen.GameScreen;

public class NewGameButton extends ScaledButton {

    private static final float HEIGHT = 0.08f;
    private static final float BLINK_DELAY = 0.1f;
    private float blinkTimer;
    private boolean alphaScale;

    private final GameScreen game;

    public NewGameButton(TextureAtlas atlas, GameScreen game) {
        super(atlas.findRegion("button_new_game"));
        this.game = game;
    }

    @Override
    public void draw(SpriteBatch batch) {
        Color color = batch.getColor();
        float oldAlpha = color.a;
        float scale = alphaScale ? 1f : 0.5f;
        color.a = oldAlpha*scale;
        batch.setColor(color);
        super.draw(batch);
        color.a = oldAlpha;
        batch.setColor(color);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        blinkTimer += delta;
        if (blinkTimer >= BLINK_DELAY) {
            blinkTimer = 0;
            alphaScale = !alphaScale;
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
        setTop(worldBounds.pos.y - 0.1f);
    }

    @Override
    protected void action() {
        game.startNewGame();
    }
}
