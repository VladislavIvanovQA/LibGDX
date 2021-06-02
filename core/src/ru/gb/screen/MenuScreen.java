package ru.gb.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.gb.base.BaseScreen;
import ru.gb.math.Rect;
import ru.gb.sprite.Background;
import ru.gb.sprite.Logo;

public class MenuScreen extends BaseScreen {

    private Texture bgImg;
    private Background background;

    private Texture rocketImg;
    private Logo logo;

    @Override
    public void show() {

        super.show();

        bgImg = new Texture("back.jpg");
        background = new Background(bgImg);

        rocketImg = new Texture("badlogic.jpg");
        logo = new Logo(rocketImg);

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        batch.end();
    }

    @Override
    public void dispose () {
        super.dispose();
        rocketImg.dispose();
        bgImg.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        logo.touchDown(touch, pointer, button);
        return false;
    }
}
