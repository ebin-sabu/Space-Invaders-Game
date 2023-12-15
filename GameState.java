/**
 * I implement a singleton pattern to manage the game's state, It includes:
 * A private static instance of GameState to ensure only one instance exists.
 * A private constructor to prevent external instantiation.
 * Static method getInstance to get the instance of the class.
 * Methods to manipulate game state, like increaseScore and decreaseLives.
 * Getters (and potentially setters) for state variables like score and lives.
 * This class is focused on maintaining and providing access to the state of the
 * game.
 * It acts as a central point for other parts of the game to query and modify
 * game-related data.
 * 
 */
public final class GameState {
    private static GameState instance = null;

    // Game state attributes
    private int score;
    private int lives;

    // Private constructor to prevent instantiation
    private GameState() {
        score = 0;
        lives = 3;
    }

    public static final GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    /**
     * Increases the player's score by a specified amount.
     * 
     * @param points The number of points to add to the current score.
     */

    public final void increaseScore(int points) {
        score += points;
    }

    /**
     * Decreases the number of lives the player has by one.
     * This method should be called when the player loses a life.
     */

    public final void decreaseLives() {
        lives--;
    }

    public final int getScore() {
        return score;
    }

    public final int getLives() {
        return lives;
    }
}
