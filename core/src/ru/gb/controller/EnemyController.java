package ru.gb.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import ru.gb.math.Rect;
import ru.gb.pool.EnemyShipPool;
import ru.gb.sprite.EnemyShip;

import java.util.Random;

public class EnemyController {
    private final static float MIN_SPEED = 0.1f;
    private final static float MAX_SPEED = 0.3f;
    private static final Random random = new Random();
    private final static float MAX_SHIPS_DELTA = 2f;
    private final EnemyShipPool enemyShipPool;
    private Rect worldbounds;
    private float shipTimer;

    public EnemyController(TextureAtlas atlas) {
        enemyShipPool = new EnemyShipPool(atlas);
    }

    public void setWorldbounds(Rect worldbounds) {
        this.worldbounds = worldbounds;
    }

    public void launch() {
        ShipType type = ShipType.values()[random.nextInt(3)];
        float speed = MIN_SPEED + random.nextFloat() * (MAX_SPEED - MIN_SPEED);
        launch(type, speed);
    }

    public void launch(ShipType shipType, float speed) {
        EnemyShip ship = enemyShipPool.obtain();
        ship.set(
                shipType.textureName,
                new Vector2(0, worldbounds.getTop()),
                new Vector2(0, -speed),
                worldbounds,
                0,
                shipType.height
        );
        ship.setLeft(worldbounds.getLeft() + random.nextFloat() * (worldbounds.getWidth() - ship.getWidth()));
    }

    public void dispose() {
        enemyShipPool.dispose();
    }

    public void updateActiveSprites(float delta) {
        if (shipTimer <= 0) {
            launch();
            shipTimer = MAX_SHIPS_DELTA * random.nextFloat();
        } else {
            shipTimer -= delta;
        }

        enemyShipPool.updateActiveSprites(delta);
    }

    public void freeAllDestroyed() {
        enemyShipPool.freeAllDestroyed();
    }

    public void drawActiveSprites(SpriteBatch batch) {
        enemyShipPool.drawActiveSprites(batch);
    }

    public enum ShipType {
        ZERO("enemy0", 0.15f),
        FIRST("enemy1", 0.1f),
        SECOND("enemy2", 0.2f);

        private final String textureName;
        private final float height;

        ShipType(String textureName, float height) {
            this.textureName = textureName;
            this.height = height;
        }
    }
}
