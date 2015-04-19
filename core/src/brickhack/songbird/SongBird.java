package brickhack.songbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import brickhack.songbird.models.GameModel;

public class SongBird extends ApplicationAdapter {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private GameModel gameModel;
    private int boxWidth = 400;

    private PitchInterface pitchInterface;

    public SongBird(PitchInterface pitchInterface) {
        this.pitchInterface = pitchInterface;
    }

    public PitchInterface getPitchEngineInstance() {
        return pitchInterface;
    }

    @Override
	public void create () {
        this.shapeRenderer = new ShapeRenderer();
        this.spriteBatch = new SpriteBatch();
        this.gameModel = new GameModel();
	}

	@Override
	public void render () {
        // SET BACKGROUND COLOR
        //System.out.println(Gdx.graphics.getDeltaTime());

        System.out.println(this.pitchInterface.getPitch());

        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 0.4f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        gameModel.draw(spriteBatch);
        spriteBatch.end();

	}
}
