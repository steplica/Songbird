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
public class PlayerBlob implements Model, Blob {

    private BodyDef playerBodyDef;
    private Body playerBody;
    private Texture texture;
    private Sprite sprite;

    public PlayerBlob(World world, Vector2 center, float radius) {
        /*
         * Body definitions for the creation and use of the object in the game world
         */
        playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.KinematicBody;
        playerBodyDef.position.set(center);
        playerBody = world.createBody(playerBodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(radius/2); // set to radius of the width of the player object
        playerBody.createFixture(circle, 0.5f);

        circle.dispose();

        /*
         * Definitions for drawing the object
         */
        Pixmap pixmap = new Pixmap(128,128, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.drawCircle((int)center.x, (int)center.y, (int)(radius / 2));
        texture = new Texture(pixmap);
        pixmap.dispose();

        // sprite object of texture with lower left corner vector at the center of the circle minus
        // the radius.
        sprite = new Sprite(
                texture,
                (int) (center.x - radius),
                (int) (center.y - radius),
                (int) (2*radius),
                (int) (2*radius)
        );
        sprite.setPosition(center.x - radius, center.y - radius);

    }

    /**
     * Gets the pitch the user is currently singing and sets the height to what pitch they're
     * currently singing, then draws the player blob
     */
    public void update() {

    }
    public void draw( SpriteBatch batch) {
        sprite.draw(batch);
    }
}
