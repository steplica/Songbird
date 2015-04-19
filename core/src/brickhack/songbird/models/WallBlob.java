package brickhack.songbird.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Vector;

/**
 * Class representing a generated wall blob that a PlayerBoB cannot pass through.
 *
 * Created by fifthparallel on 4/18/2015.
 */
public class WallBlob implements Model, Blob {

    public static final int WALL_WIDTH = 25;

    private Rectangle wall;

    public WallBlob(int x_pos, int y_pos, int height) {

        wall = new Rectangle();
        wall.x = x_pos;
        wall.y = y_pos;
        wall.width = WALL_WIDTH;
        wall.height = height;
    }

    public void draw( SpriteBatch batch ) {}
    public void update() {}
}
