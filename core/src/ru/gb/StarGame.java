package ru.gb;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class StarGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture img;
    private TextureRegion region;

    private float x;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        region = new TextureRegion(img, 10, 10, 150, 150);

		Vector2 v1 = new Vector2(2, 2);
		Vector2 v2 = new Vector2(2, 1);
		v1.add(v2);
		System.out.println("v1.add(v2) v1.x = " + v1.x + " v1.y = " + v1.y);

		v1.set(5, 5);
		v2.set(3, 1);
		v1.sub(v2);
		System.out.println("v1.sub(v2) v1.x = " + v1.x + " v1.y = " + v1.y);

		System.out.println("v1.len() = " + v1.len());

		v1.setLength(100);
		v1.scl(0.9f);
		System.out.println("v1.len() = " + v1.len());
		v1.scl(2);
		System.out.println("v1.len() = " + v1.len());

		v1.nor();
		System.out.println("v1.len() = " + v1.len());

		v1.set(1, 1);
		v2.set(-1, 1);
		v1.nor();
		v2.nor();
		System.out.println(Math.acos(v1.dot(v2)));

		v1.set(5, 5);
		v2.set(3, 1);
		Vector2 v3 = v1.cpy().add(v2);
		System.out.println("v1.add(v2) v1.x = " + v1.x + " v1.y = " + v1.y);
		System.out.println("v3.x = " + v3.x + " v3.y = " + v3.y);
    }

    @Override
    public void render() {
        x++;
        ScreenUtils.clear(0.33f, 0.45f, 0.68f, 1);
        batch.begin();
        batch.draw(img, x, 0);
        batch.draw(region, 300, 300, 150, 150);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
