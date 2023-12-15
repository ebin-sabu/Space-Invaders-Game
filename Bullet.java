
import java.awt.*;

/**
 * Represents a bullet in the Space Invaders game.
 * This class manages the properties and behaviors of bullets being fired both
 * from aliens and player
 */

public final class Bullet {
    private int x;
    private int y;
    private int direction;

    public static final int BULLET_SIZE = 5;
    public static final int BULLET_SPEED = 5;

    /**
     * Constructs a new Bullet with specified coordinates and direction.
     * 
     * @param x         The x-coordinate of the bullet.
     * @param y         The y-coordinate of the bullet.
     * @param direction The direction of the bullet's movement.
     */

    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /**
     * Updates the bullet's position based on its direction and speed.
     */

    public final void update() {
        y += direction * BULLET_SPEED;
    }

    /**
     * Renders the bullet on the game panel.
     * 
     * @param g2d The Graphics2D object used for drawing.
     */

    public final void render(Graphics2D g2d) {
        // Set the color to red
        g2d.setColor(Color.RED);
        g2d.fillRect(x, y, BULLET_SIZE, BULLET_SIZE);
    }

    /**
     * 
     * @return y The y-coordinate of the bullet.
     */

    public final int getY() {
        return y;
    }

    /**
     * 
     * @return x The x-coordinate of the bullet.
     */

    public final int getX() {
        return x;
    }
}
