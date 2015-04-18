package brickhack.songbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;

public class SongBird extends ApplicationAdapter {

    private ShapeRenderer shapeRenderer;
    private int boxWidth = 400;

	@Override
	public void create () {
        this.shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
        // SET BACKGROUND COLOR
        System.out.println(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.CYAN);

        this.boxWidth += Gdx.graphics.getDeltaTime() * 100.f;

        shapeRenderer.rect(0,0, this.boxWidth, this.boxWidth);
        shapeRenderer.end();
	}
}
