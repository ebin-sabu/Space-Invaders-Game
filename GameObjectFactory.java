/**
 * I implement a factory pattern for creating game objects. It contains static
 * methods for creating instances of different game entities
 * Each method takes parameters specific to the type of game object being
 * created and returns a new instance of that object.
 * The Factory Pattern is used here to encapsulate the instantiation logic of
 * game objects, providing a centralized point for object creation.
 */

public final class GameObjectFactory {

    // Method to create a Player object
    /**
     * Creates a new Player object with specified coordinates.
     * 
     * @param x The x-coordinate for the player.
     * @param y The y-coordinate for the player.
     * @return A new instance of Player.
     */

    public static final Player createPlayer(int x, int y) {
        return new Player(x, y);
    }

    // Method to create an Alien object
    /**
     * Creates a new Alien object with specified coordinates.
     * 
     * @param x The x-coordinate for the alien.
     * @param y The y-coordinate for the alien.
     * @return A new instance of Alien.
     */

    public static final Alien createAlien(int x, int y) {
        return new Alien(x, y);
    }

    // Method to create a bullet object
    /**
     * Creates a new bullet object with specified coordinates.
     * 
     * @param x         The x-coordinate for the bullet.
     * @param y         The y-coordinate for the bullet.
     * @param direction The direction of bullet up or down
     * @return A new instance of a bullet.
     */
    public static final Bullet createBullet(int x, int y, int direction) {
        return new Bullet(x, y, direction);
    }

    // Method to create an Obstacle object
    /**
     * @return A new instance of an obstacle.
     */
    public static final Obstacle createObstacle() {
        return new Obstacle();
    }
}
