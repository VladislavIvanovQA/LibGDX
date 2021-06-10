package ru.gb.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.gb.base.Sprite;
import ru.gb.math.Rect;
import ru.gb.utils.Regions;

public class EnemyShip extends Sprite {

    private final Vector2 v = new Vector2(0f, -0.5f);
    private final TextureAtlas atlas;
    private int damage;
    private Rect worldBounds;

    public EnemyShip(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    public void set(
            String atlasRegion,
            Vector2 pos0,
            Vector2 v0,
            Rect worldBounds,
            int damage,
            float height
    ) {
        this.regions = Regions.split(atlas.findRegion(atlasRegion), 1, 2, 2);
        this.pos.set(pos0);
        this.v.set(v0);
        this.worldBounds = worldBounds;
        this.damage = damage;
        setHeightProportion(height);
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
}
