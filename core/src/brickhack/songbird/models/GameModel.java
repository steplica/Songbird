package brickhack.songbird.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;
import brickhack.songbird.util.Notes;
import brickhack.songbird.SongBird;

/**
 * A model that represents the entire game state, contains methods
 * the main application can call to run the game through an instance
 * of a run through a generated song.
 *
 * Created by fifthparallel on 4/18/2015.
 */
public class GameModel {

    private PlayerBlob player;
    private WallBlob topWall;
    private WallBlob botWall;
    private LockBlob gate;
    private World world;

    public GameModel() {
        world = new World(new Vector2(0,0), true);
        player = new PlayerBlob(world, new Vector2(50, 1080/2),32);
    }

    public void update() {

        double currFreq = SongBird.getPitchEngineInstance().getPitch();
        if(currFreq >= Notes.freqs[0]){
            System.out.println(currFreq);
            player.update((int)(1080 * Notes.freqToAltitude(currFreq)));
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        player.draw(spriteBatch);
    }
}
