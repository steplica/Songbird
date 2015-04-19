package brickhack.songbird.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * The class that represents the blob the player is controlling using their voice.
 *
 * Created by fifthparallel on 4/18/2015.
 */
public class PlayerBlob implements Blob {

    private BodyDef playerBodyDef;
    private Body playerBody;
    private Texture texture;

    public PlayerBlob(World world, Vector2 lower_left_vertex, int radius) {
        /*
         * Body definitions for the creation and use of the object in the game world
         */
        playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.KinematicBody;
        playerBodyDef.position.set(lower_left_vertex);
        playerBody = world.createBody(playerBodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(radius); // set to radius of the width of the player object
        playerBody.createFixture(circle, 0.5f);

        circle.dispose();

        /*
         * Definitions for drawing the object
         */
         Pixmap pixmap = new Pixmap( (2*radius), (2*radius), Pixmap.Format.RGBA8888 );
         pixmap.setColor( Color.GREEN );
         pixmap.fillCircle( radius, radius, radius );
         texture = new Texture( pixmap );
         pixmap.dispose();
    }

    /**
     * Gets the pitch the user is currently singing and sets the height to what pitch they're
     * currently singing, then draws the player blob
     */
    public void update(int y) {
        playerBody.setTransform(100, y, 0);
    }
    public void draw( SpriteBatch batch) {
        batch.draw(texture, playerBody.getPosition().x, playerBody.getPosition().y);
    }
}
