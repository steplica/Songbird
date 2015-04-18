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

	private SpriteBatch batch;
    private Texture img;
    private ShapeRenderer shapeRenderer;

	@Override
	public void create () {
        this.shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
        // SET BACKGROUND COLOR
        System.out.println(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(0,0, 600, 600);
        shapeRenderer.end();

        // DRAW
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
}
