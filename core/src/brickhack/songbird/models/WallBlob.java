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

public class WallBlob {

    public static final int WALL_WIDTH = 90;

    BodyDef wallBodyDef;
    Body wallBody;
    Texture texture;
    Sprite sprite;
    EdgeShape wall;
    Pixmap pixmap;
    boolean isUpperWall;

    public WallBlob(World world, Vector2 referencePoint, boolean isUpper) {
        wallBodyDef = new BodyDef();
        wallBodyDef.type = BodyDef.BodyType.KinematicBody;
        wallBodyDef.position.set(referencePoint);
        wallBody = world.createBody(wallBodyDef);
        isUpperWall = isUpper;

        pixmap = new Pixmap(WALL_WIDTH, 1080, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(0, 0, WALL_WIDTH, 1080);
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    public void draw( SpriteBatch batch ) {
        batch.draw(texture, wallBody.getPosition().x, wallBody.getPosition().y);
    }

    public void update() {

    }
}
