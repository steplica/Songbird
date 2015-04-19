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

import static com.badlogic.gdx.graphics.Color.WHITE;

/**
 * Class representing a generated wall blob that a PlayerBoB cannot pass through.
 *
 * Created by fifthparallel on 4/18/2015.
 */
public class WallBlob implements Model, Blob {

    public static final int WALL_WIDTH = 25;

    BodyDef wallBodyDef;
    Body wallBody;
    Texture texture;
    Sprite sprite;

    public WallBlob(World world, Vector2 lower_left_vertex, float height) {
        wallBodyDef = new BodyDef();
        wallBodyDef.type = BodyDef.BodyType.KinematicBody;
        wallBodyDef.position.set(lower_left_vertex);
        wallBody = world.createBody(wallBodyDef);

        EdgeShape wall = new EdgeShape();
        wall.set(
                lower_left_vertex,
                new Vector2(
                    lower_left_vertex.x + WALL_WIDTH,
                    lower_left_vertex.y + height
                )
        );
        wallBody.createFixture(wall, 0.5f);

        wall.dispose();

        Pixmap pixmap = new Pixmap(64, 256, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        pixmap.drawRectangle(
            (int) lower_left_vertex.x,
            (int) lower_left_vertex.y,
            WALL_WIDTH,
            (int) height
        );
        texture = new Texture(pixmap);
        pixmap.dispose();

        sprite = new Sprite(
                texture,
                (int) lower_left_vertex.x,
                (int) lower_left_vertex.y,
                WALL_WIDTH,
                (int) height
        );
        sprite.setPosition(lower_left_vertex.x, lower_left_vertex.y);
    }

    public void draw( SpriteBatch batch ) {
        sprite.draw(batch);
    }
    public void update() {}
}
