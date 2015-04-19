package brickhack.songbird.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * An interface that represents the contract for what Blobs that need to be drawn need to do.
 *
 * Created by fifthparallel on 4/18/2015.
 */
public interface Blob {

    public void draw( SpriteBatch batch );

}
