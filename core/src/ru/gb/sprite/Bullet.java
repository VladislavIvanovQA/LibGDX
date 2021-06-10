package ru.gb.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.gb.base.Sprite;
import ru.gb.math.Rect;

public class Bullet extends Sprite {

    private final Sound shootSound;
    private Rect worldBounds;
    private final Vector2 v;
    private int damage;
    private Sprite owner;

    public Bullet() {
        regions = new TextureRegion[1];
        v = new Vector2();
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
    }

    public void set(
            Sprite owner,
            TextureRegion region,
            Vector2 pos0,
            Vector2 v0,
            Rect worldBounds,
            int damage,
            float height
    ) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.v.set(v0);
        this.worldBounds = worldBounds;
        this.damage = damage;
        setHeightProportion(height);

        shootSound.play(1.0f);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public Sprite getOwner() {
        return owner;
    }

    @Override
    public void dispose() {
        shootSound.dispose();
        super.dispose();
    }
}
