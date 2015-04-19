package brickhack.songbird.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Contains related data and methods for a corresponding
 * visual locked 'gate' the user needs to unlock by getting
 * to that pitch level.
 *
 * Stores state of an instance of a Gate and data for how to display it.
 *
 * Created by fifthparallel on 4/18/2015.
 */
public class LockBlob implements Blob {

    private static final int SIDE_LENGTH = WallBlob.WALL_WIDTH;

    /**
     * boolean that represents whether the lock is unlocked
     */
    private boolean locked = true;

    BodyDef lockBodyDef;
    Body lockBody;
    Texture texture;
    Sprite sprite;

    public LockBlob(World world, Vector2 lower_left_vertex) {
        lockBodyDef = new BodyDef();
        lockBodyDef.type = BodyDef.BodyType.KinematicBody;
        lockBodyDef.position.set(lower_left_vertex);
        lockBody = world.createBody(lockBodyDef);

        EdgeShape lockRect = new EdgeShape();
        lockRect.set(
                lower_left_vertex,
                new Vector2(
                        lower_left_vertex.x + SIDE_LENGTH,
                        lower_left_vertex.y + SIDE_LENGTH
                )
        );
        lockBody.createFixture(lockRect, 0.5f);

        lockRect.dispose();

        Pixmap pixmap = new Pixmap(SIDE_LENGTH, SIDE_LENGTH, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.YELLOW);
        pixmap.drawRectangle(
                0, 0,
                SIDE_LENGTH,
                SIDE_LENGTH
        );
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    public void new_value(int y_pos) {
        lockBody.setTransform(1920 - 128, y_pos - (SIDE_LENGTH / 2), 0.0f);
    }

    public boolean hasPassedBar() {
        return ((lockBody.getPosition().x) <= (0 - SIDE_LENGTH));
    }

    public boolean toggleLock(boolean on) {
        locked = on;
        return locked;
    }

    public void update() {

    }

    public void draw( SpriteBatch batch ) {
        batch.draw(texture, lockBody.getPosition().x, lockBody.getPosition().y);
    }

}
