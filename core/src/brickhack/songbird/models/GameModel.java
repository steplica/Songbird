package brickhack.songbird.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;

import java.util.concurrent.locks.Lock;

import brickhack.songbird.util.Notes;
import brickhack.songbird.SongBird;

/**
 * A model that represents the entire game state, contains methods
 * the main application can call to run the game through an instance
 * of a run through a generated song.
 *
 * Created by fifthparallel on 4/18/2015.
 */
public class GameModel {

    private PlayerBlob player;
    private WallBlob topWall;
    private WallBlob botWall;
    private LockBlob gate;
    private World world;

    private boolean easyMode = true;
    private double slack = 5;
    private int horizontalSpeed = -8;
    public double targetFreq = Notes.getRandomFreq();
    private int x = 1920 - 128;
    private float x_coord_start = 1500.0f;

    public GameModel() {
        world = new World(new Vector2(0,0), true);
        player = new PlayerBlob(world, new Vector2(50, 1080/2),32);
        int ran_note = (int) (Notes.freqToAltitude(Notes.getRandomFreq()) * 1080);
        gate = new LockBlob(world, new Vector2(x_coord_start, (float) ran_note ));
        topWall = new WallBlob(
                world,
                new Vector2(x_coord_start,(float) ((0.5 * WallBlob.WALL_WIDTH) + ran_note )),
                (float)(1080 - ran_note)
        );
        botWall = new WallBlob(
                world,
                new Vector2(x_coord_start, 0),
                (float) ( ran_note )
        );
    }

    public void update() {
        double currFreq = SongBird.getPitchEngineInstance().getPitch();
        if(currFreq >= Notes.freqs[0]){
            if(easyMode){
                player.update((int)(1080 * Notes.freqToAltitude(Notes.autoTune(currFreq))));
            } else {
                player.update((int)(1080 * Notes.freqToAltitude(currFreq)));
            }
        }
        if(Notes.freqMatches(currFreq, targetFreq, slack)){
            System.out.println("IT MATCHES!!!");
            targetFreq = Notes.getRandomFreq();
            System.out.println("New Target: " + targetFreq);
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        player.draw(spriteBatch);
    }
}
