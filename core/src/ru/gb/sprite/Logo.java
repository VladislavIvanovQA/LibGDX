package ru.gb.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.Sprite;
import ru.gb.math.Rect;

public class Logo extends Sprite {

    private static final float LOGO_SPEED = 0.05f;
    private static final float HEIGHT = 0.3f;

    private final Vector2 logoCurrentSpeed = new Vector2();
    private float logoLenToFinish;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        this.pos.set(worldBounds.pos);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (logoLenToFinish >= LOGO_SPEED) {
            pos.add(logoCurrentSpeed);
            logoLenToFinish -= LOGO_SPEED;

        } else if (logoLenToFinish > 0) {
            logoCurrentSpeed.setLength(logoLenToFinish);
            pos.add(logoCurrentSpeed);
            logoLenToFinish = 0;
        }

        super.draw(batch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {

        logoLenToFinish = pos.dst(touch);

        logoCurrentSpeed.set(touch);
        logoCurrentSpeed.sub(pos);
        logoCurrentSpeed.setLength(LOGO_SPEED);

        return super.touchDown(touch, pointer, button);

    }
}
