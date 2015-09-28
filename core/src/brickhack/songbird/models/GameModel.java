package brickhack.songbird.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Vector2;
import brickhack.songbird.util.Notes;
import brickhack.songbird.SongBird;

public class GameModel {

    private SpriteBatch spriteBatch;
    private PlayerBlob player;
    private WallBlob topWall;
    private WallBlob botWall;
    private LockBlob gate;
    private World world;

    private int xReset = 1920;
    private int horizontalSpeed = -4;

    public double targetFreq = Notes.getRandomFreq();
    private int currX = xReset;
    private int score = 0;

    private boolean easyMode = false;
    private double slack = 5;


    public GameModel() {
        world = new World(new Vector2(0,0), true);
        player = new PlayerBlob(world, new Vector2(50, 1080/2),32);
        gate = new LockBlob(world, new Vector2(xReset, (int)(Notes.freqToAltitude(targetFreq) * 1080)));
        topWall = new WallBlob(world, new Vector2(xReset, gate.lockBodyDef.position.y+90), true);
        botWall = new WallBlob(world, new Vector2(xReset, 0), false);
        spriteBatch = new SpriteBatch();
    }

    public void update() {
        double currFreq = SongBird.getPitchEngineInstance().getPitch();
        if((currX += horizontalSpeed) < -WallBlob.WALL_WIDTH){
            currX = xReset;
        }
        if(currFreq >= Notes.freqs[0]){
            if(easyMode){
                player.update((int)(1080 * Notes.freqToAltitude(Notes.autoTune(currFreq))));
            } else {
                player.update((int)(1080 * Notes.freqToAltitude(currFreq)));
            }
            // WHEN THE PLAYER SINGS THE RIGHT NOTE
            if(Notes.freqMatches(currFreq, targetFreq, slack)){
                System.out.println("SCORE! " + (++score));
                drawNewWall();
            }
        }

        gate.lockBody.setTransform(currX, gate.lockBody.getPosition().y, 0);
        topWall.wallBody.setTransform(currX, topWall.wallBody.getPosition().y, -1);
        botWall.wallBody.setTransform(currX, botWall.wallBody.getPosition().y, -1);

        draw();
    }

    public void draw() {
        spriteBatch.begin();

        player.draw(spriteBatch);
        topWall.draw(spriteBatch);
        botWall.draw(spriteBatch);
        gate.draw(spriteBatch);

        spriteBatch.end();
    }

    public void drawNewWall(){
        currX = xReset;
        targetFreq = Notes.getRandomFreq();
        gate.lockBody.setTransform(xReset, (int) (Notes.freqToAltitude(targetFreq) * 1080), 0);
        topWall.wallBody.setTransform(xReset, gate.lockBody.getPosition().y+90, -1);
        botWall.wallBody.setTransform(xReset, gate.lockBody.getPosition().y-1080, -1);
    }
}
