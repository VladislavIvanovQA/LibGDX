package ru.gb.pool;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.gb.base.SpritesPool;
import ru.gb.sprite.EnemyShip;

public class EnemyShipPool extends SpritesPool<EnemyShip> {

    private final TextureAtlas atlas;

    public EnemyShipPool(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(atlas);
    }
}
