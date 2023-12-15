
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an alien in the Space Invaders game.
 * This class manages the properties and behaviors of an alien,
 * including its position, movement, and firing mechanics.
 */

public final class Alien {
    private int x;
    private int y;
    private int direction; // 1 for right, -1 for left
    private int bulletCooldown;
    private boolean isFiring;
    private long lastBulletTime;

    private List<Bullet> alienBullets;
    public static final int ALIEN_SIZE = 30;

    /**
     * Constructs an Alien with specified coordinates.
     * 
     * @param x The x-coordinate of the alien.
     * @param y The y-coordinate of the alien.
     */

    public Alien(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = 1;
        this.alienBullets = new ArrayList<>();
        this.isFiring = false;
        this.lastBulletTime = System.currentTimeMillis();
    }

    /**
     * Returns the list of bullets fired by the alien.
     * 
     * @return A list of Bullet objects.
     */

    public final List<Bullet> getAlienBullets() {
        return alienBullets;
    }

    /**
     * Updates the alien's position and manages bullet firing.
     */

    public final void update() {
        x += direction;
        long currentTime = System.currentTimeMillis();
        if (!isFiring && currentTime - lastBulletTime > 2000 + Math.random() * 2000 && bulletCooldown <= 0) {
            fireBullet();
            lastBulletTime = currentTime;
            isFiring = true;
            bulletCooldown = 100;
        } else {
            isFiring = false;
        }

        if (bulletCooldown > 0) {
            bulletCooldown--;
        }
    }

    private final void fireBullet() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBulletTime > 2000 + Math.random() * 2000) {
            alienBullets.add(new Bullet(x + 15, y + 30, 1));
        }
    }

    public final void moveDown() {
        y += 10; // Move one layer down
        changeDirection(); // Change direction after moving down
    }

    private final void changeDirection() {
        direction *= -1; // Change direction to opposite horizontal direction
    }

    /**
     * Renders the alien on the game panel.
     * 
     * @param g2d The Graphics2D object used for drawing.
     */

    public final void render(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.fillRect(x, y, 15, 15);
    }

    /**
     * Checks if this alien intersects with a bullet.
     * This method is used for collision detection between the alien and a bullet.
     *
     * @param bullet The bullet to check for intersection.
     * @return true if this alien intersects with the bullet, false otherwise.
     */

    public final boolean intersects(Bullet bullet) {
        return x < bullet.getX() + 5 &&
                x + 30 > bullet.getX() &&
                y < bullet.getY() + 5 &&
                y + 30 > bullet.getY();
    }

    /**
     * Checks if this alien intersects with a obstacle.
     * This method is used for collision detection between the alien and an
     * obstacle.
     *
     * @param obstacle The obstacle to check for intersection.
     * @return true if this alien intersects with the obstacle, false otherwise.
     */

    public final boolean intersects(Obstacle obstacle) {
        return x < obstacle.getX() + Obstacle.WIDTH &&
                x + ALIEN_SIZE > obstacle.getX() &&
                y < obstacle.getY() + Obstacle.HEIGHT &&
                y + ALIEN_SIZE > obstacle.getY();
    }

    /**
     * 
     * @return x The x-coordinate of the alien.
     */

    public final int getX() {
        return x;
    }

    /**
     * 
     * @return y The y-coordinate of the alien.
     */

    public final int getY() {
        return y;
    }
}
