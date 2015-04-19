package brickhack.songbird.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Contains related data and methods for a corresponding
 * visual locked 'gate' the user needs to unlock by getting
 * to that pitch level.
 *
 * Stores state of an instance of a Gate and data for how to display it.
 *
 * Created by fifthparallel on 4/18/2015.
 */
public class LockBlob implements Model, Blob {

    private int width = WallBlob.WALL_WIDTH;

    /**
     * boolean that represents whether the lock is unlocked
     */
    private boolean locked = true;

    public boolean toggleLock() {
        locked = !locked;
        return locked;
    }

    public void update() {}

    public void draw( SpriteBatch batch ) {}

}
