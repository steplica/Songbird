package brickhack.songbird.models;

/**
 * The class that represents the blob the player is controlling using their voice.
 *
 * Created by fifthparallel on 4/18/2015.
 */
public class PlayerBlob implements Model, Blob {

    private int height = GameModel.MAX_HEIGHT / 2;

    /**
     * Gets the pitch the user is currently singing and sets the height to what pitch they're
     * currently singing, then draws the player blob
     */
    public void update() {}
    public void draw() {}
}
