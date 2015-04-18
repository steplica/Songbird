package brickhack.songbird.models;

/**
 * Stores state for the current User such as their difficulty level and
 * how many times they've attempted to pass at the current difficulty level.
 *
 * UserModel is a singleton instance to make sure that the same user is used between runs.
 *
 * Created by fifthparallel on 4/18/2015.
 */
public class UserModel implements Model {

    /**
     * The number of attempts until lowering the difficulty
     */
    public static final int MAX_ATTEMPTS = 5;

    /**
     * The instance of the user
     */
    private static UserModel instance;
    /**
     * The player blob drawn on the screen that represents the user's position
     */
    private static PlayerBlob playerBlob;
    /**
     * The current level of difficulty the user is at, representative of how many walls they
     * have made it through.
     */
    private static long difficulty = 0;
    /**
     * The number of attempts at a difficulty
     */
    private static int num_attempts = 0;

    /**
     * Hidden constructor for the singleton user instance.
     */
    protected UserModel() {
        playerBlob = new PlayerBlob();
    }

    /**
     * The method to get the singleton user instance.
     *
     * if null, create a new one
     *
     * @return  instance    the current instance of the GameModel to user
     */
    public UserModel getUser() {
        if( instance == null ) {
            instance = new UserModel();
        }

        return instance;
    }

    /**
     * @return  the user's current difficulty level
     */
    public long getDifficulty() {
        return difficulty;
    }

    /**
     * Call update every time going through the game loop and update state
     *
     * update checks to see if the user has passed through a Wall, increments
     * the difficulty, then calls the playerBlob update method to redraw wherever the
     * new user is.
     */
    public void update() {

        playerBlob.update();

    }
}
