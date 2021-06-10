package ru.gb.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.gb.base.Sprite;
import ru.gb.math.Rect;

public class Rocket extends Sprite {

    private final static float SPEED = 0.01f;
    private final static float HEIGHT = 0.08f;
    private final static float PADDING = 0.03f;

    private final Vector2 direction = new Vector2();
    private final TextureRegion normalRocket;
    private final TextureRegion damagedRocket;
    private Rect worldBounds;

    public Rocket(TextureAtlas atlas) {
        super(new TextureRegion());

        TextureAtlas.AtlasRegion a = atlas.findRegion("main_ship");
        normalRocket = new TextureRegion(a, 0, 0,
                a.getRegionWidth() / 2, a.getRegionHeight());
        damagedRocket = new TextureRegion(a, a.getRegionWidth() / 2, 0,
                a.getRegionWidth() / 2, a.getRegionHeight());

        regions[0] = normalRocket;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (direction.x > 0 && (pos.x + getHalfWidth()) < worldBounds.getHalfWidth() - PADDING) {
            pos.add(SPEED, 0);
        } else if (direction.x < 0 && (pos.x - getHalfWidth()) > -worldBounds.getHalfWidth() + PADDING) {
            pos.add(-SPEED, 0);
        }

        super.draw(batch);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
        pos.set(worldBounds.pos);
        setBottom(worldBounds.getBottom() + PADDING);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        direction.set((touch.x > 0) ? 1 : -1, 0);

        if (isMe(touch)) {
            regions[0] = damagedRocket;
        }

        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        direction.setZero();
        regions[0] = normalRocket;
        return super.touchUp(touch, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case 21: {
                direction.set(-1, 0);
                break;
            }
            case 22: {
                direction.set(1, 0);
                break;
            }
        }

        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        direction.setZero();
        return super.keyUp(keycode);
    }
}
