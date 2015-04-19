package brickhack.songbird.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;

/**
 * A model that represents the entire game state, contains methods
 * the main application can call to run the game through an instance
 * of a run through a generated song.
 *
 * Created by fifthparallel on 4/18/2015.
 */
public class GameModel implements Model {

    private PlayerBlob player;
    private WallBlob topWall;
    private WallBlob botWall;
    private LockBlob gate;
    private World world;

    public GameModel() {
        world = new World(new Vector2(0,0), true);
        player = new PlayerBlob(world, new Vector2(50, 1080/2),WallBlob.WALL_WIDTH - 10);
    }

    /**
     * The maximum height an object's top can be.
     */
    public static final int MAX_HEIGHT = 100;

    public void update() {

    }

    public void draw(SpriteBatch spriteBatch) {
        player.draw(spriteBatch);
    }
}
