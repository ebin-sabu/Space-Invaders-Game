
import java.awt.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the player in the Space Invaders game.
 * This class manages the player's position, movement, and actions such as
 * firing bullets.
 */
public final class Player {
    private int x;
    private int y;
    private long lastBulletTime;

    private final List<Bullet> bullets;

    public static final int PLAYER_SIZE = 40;

    /**
     * Constructs a new Player with specified coordinates.
     * 
     * @param x The x-coordinate of the player.
     * @param y The y-coordinate of the player.
     */

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.bullets = new ArrayList<>();
        this.lastBulletTime = System.currentTimeMillis();
    }

    /**
     * Moves the player to the left within the bounds of the game screen.
     */

    public final void moveLeft() {
        x = Math.max(x - 10, 0);
    }

    /**
     * Moves the player to the right within the bounds of the game screen.
     */

    public final void moveRight() {
        x = Math.min(x + 10, 700);
    }

    /**
     * Updates the state of the player and its bullets.
     * This includes moving the bullets and removing any that are out of bounds.
     */

    public final void update() {
        // Update player bullets
        Iterator<Bullet> playerBulletIterator = bullets.iterator();
        while (playerBulletIterator.hasNext()) {
            Bullet bullet = playerBulletIterator.next();
            bullet.update();

            // Remove bullets that are out of bounds
            if (bullet.getY() < 0) {
                playerBulletIterator.remove();
            }
        }
    }

    /**
     * Fires a bullet from the player's position if the cooldown period has elapsed.
     */

    public final void fireBullet() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBulletTime > 4000) {
            bullets.add(new Bullet(x + PLAYER_SIZE / 2, y, -1));
        }
    }

    /**
     * Renders the player on the game panel.
     * 
     * @param g2d The Graphics2D object used for drawing.
     */

    public final void render(Graphics2D g2d) {
        g2d.fillRect(x, y, 40, 40);
    }

    /**
     * Checks if the player intersects with a given rectangle.
     * This method is commonly used for collision detection.
     *
     * @param other The rectangle to check for intersection with the player.
     * @return true if the player intersects with the given rectangle, false
     *         otherwise.
     */

    public final boolean intersects(Rectangle other) {
        return new Rectangle(x, y, 40, 40).intersects(other);
    }

    /**
     * Checks if the player intersects with an alien.
     * This is used to detect collisions between the player and aliens.
     *
     * @param alien The alien to check for intersection.
     * @return true if the player intersects with the given alien, false otherwise.
     */

    public final boolean intersects(Alien alien) {
        return intersects(new Rectangle(alien.getX(), alien.getY(), Alien.ALIEN_SIZE, Alien.ALIEN_SIZE));
    }

    /**
     * Checks if the player intersects with a bullet.
     * This is used to detect collisions between the player and bullets.
     *
     * @param bullet The bullet to check for intersection.
     * @return true if the player intersects with the given bullet, false otherwise.
     */

    public final boolean intersects(Bullet bullet) {
        return intersects(new Rectangle(bullet.getX(), bullet.getY(), Bullet.BULLET_SIZE, Bullet.BULLET_SIZE));
    }

    /**
     * Checks if the player intersects with a given obstacle.
     * This is used to detect collisions between the player and obstacles.
     *
     * @param obstacle The obstacle to check for intersection.
     * @return true if the player intersects with the given obstacle, false
     *         otherwise.
     */

    public final boolean intersects(Obstacle obstacle) {
        return intersects(new Rectangle(obstacle.getX(), obstacle.getY(), Obstacle.WIDTH, Obstacle.HEIGHT));
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }
}
