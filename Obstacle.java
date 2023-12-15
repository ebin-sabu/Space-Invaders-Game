
import java.awt.*;
import java.util.Random;

public final class Obstacle {
    private int x;
    private final int y = 400;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public static final int OBSTACLE_SIZE = 50;

    private int hitCount;

    /**
     * Constructor for creating an Obstacle.
     * Initializes the obstacle with specific properties.
     */

    public Obstacle() {
        // Randomly set x position for the obstacle
        Random random = new Random();
        this.x = random.nextInt(750);
        this.hitCount = 0;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    /**
     * Renders the obstacle on the game panel.
     * 
     * @param g2d The Graphics2D object used for drawing.
     */

    public final void render(Graphics2D g2d) {
        // Render obstacle
        g2d.setColor(Color.GRAY);
        g2d.fillRect(x, y, WIDTH, HEIGHT);
    }

    /**
     * Checks if this obstacle intersects with a bullet.
     * This method is used for collision detection between the obstacle and a
     * bullet.
     *
     * @param bullet The bullet to check for intersection.
     * @return true if this obstacle intersects with the bullet, false otherwise.
     */

    public final boolean intersects(Bullet bullet) {
        // Check if the bullet intersects with the obstacle
        return bullet.getX() < x + WIDTH && bullet.getX() + Bullet.BULLET_SIZE > x &&
                bullet.getY() < y + HEIGHT && bullet.getY() + Bullet.BULLET_SIZE > y;
    }

    /**
     * Checks if this obstacle intersects with an alien.
     * This method is used for collision detection between the obstacle and an
     * alien.
     *
     * @param alien The alien to check for intersection.
     * @return true if this obstacle intersects with the alien, false otherwise.
     */

    public final boolean intersects(Alien alien) {
        return new Rectangle(x, y, OBSTACLE_SIZE, OBSTACLE_SIZE).intersects(
                new Rectangle(alien.getX(), alien.getY(), Alien.ALIEN_SIZE, Alien.ALIEN_SIZE));
    }

    /**
     * Handles the impact of a bullet hitting the obstacle.
     * Increases the hit count and removes the obstacle if the hit count exceeds a
     * threshold.
     */
    public final void hit() {
        // Increase hit count and check if the obstacle should disappear
        hitCount++;
        if (hitCount >= 5) {
            x = -1; // Move the obstacle off-screen to "remove" it
        }
    }
}
