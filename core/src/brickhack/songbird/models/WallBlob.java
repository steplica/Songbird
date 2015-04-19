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

    public static final int WALL_WIDTH = 64;

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

        Pixmap pixmap = new Pixmap(WALL_WIDTH, (int)height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(
            0, 0,
            WALL_WIDTH,
            (int) height
        );
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    public void draw( SpriteBatch batch ) {
        batch.draw(texture, wallBody.getPosition().x, wallBody.getPosition().y);
    }
    public void update() {}
}
