package brickhack.songbird.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * The class that represents the blob the player is controlling using their voice.
 *
 * Created by fifthparallel on 4/18/2015.
 */
public class PlayerBlob implements Model, Blob {

    private BodyDef playerBodyDef;
    private Body playerBody;
    private Texture texture;
    private TextureRegion region;
    private Sprite sprite;

    public PlayerBlob(World world, Vector2 center, float diameter) {
        /*
         * Body definitions for the creation and use of the object in the game world
         */
        playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.KinematicBody;
        playerBodyDef.position.set(center);
        playerBody = world.createBody(playerBodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(diameter/2); // set to radius of the width of the player object
        playerBody.createFixture(circle, 0.5f);

        circle.dispose();

        /*
         * Definitions for drawing the object
         */

    }

    /**
     * Gets the pitch the user is currently singing and sets the height to what pitch they're
     * currently singing, then draws the player blob
     */
    public void update() {}
    public void draw( SpriteBatch batch) {}
}
