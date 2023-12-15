
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents the main game panel in the Space Invaders game.
 * This class manages the game's visual components, including the player,
 * aliens, bullets, and obstacles.
 * It also handles key events for player actions and updates the game state.
 */

public final class GamePanel extends JPanel implements KeyListener {
    private Player player;
    private List<Alien> aliens;
    private List<Bullet> bullets;
    private List<Bullet> alienBullets;
    private List<Obstacle> obstacles;
    private int totalAliens;
    private GameRenderer gameRenderer;

    private int alienDirection = 1; // 1 for right, -1 for left

    /**
     * Constructor for GamePanel.
     * Initialises the game objects and sets up the game environment.
     */

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);

        player = GameObjectFactory.createPlayer(400, 500);
        aliens = new ArrayList<>();
        bullets = new ArrayList<>();
        alienBullets = new ArrayList<>();
        gameRenderer = new GameRenderer();

        setFocusable(true);
        addKeyListener(this);

        // Initialise aliens
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                aliens.add(GameObjectFactory.createAlien(50 + col * 100, 50 + row * 50));
            }
        }

        totalAliens = aliens.size();

        obstacles = new ArrayList<>();

        // Initialise obstacles
        for (int i = 0; i < 5; i++) {
            obstacles.add(GameObjectFactory.createObstacle());
        }

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        timer.start();
    }

    /**
     * Updates the state of the game, including the movement of aliens, bullets, and
     * collision checks.
     * This method is called periodically to advance the game state.
     */

    public final void update() {
        player.update();

        Iterator<Obstacle> obstacleIterator = obstacles.iterator();
        while (obstacleIterator.hasNext()) {
            Obstacle obstacle = obstacleIterator.next();

            // Check for collision with bullets from player on obstacle
            Iterator<Bullet> bulletIterator = bullets.iterator();
            while (bulletIterator.hasNext()) {
                Bullet bullet = bulletIterator.next();
                if (obstacle.intersects(bullet)) {
                    obstacle.hit();
                    bulletIterator.remove(); // Remove the bullet
                }
            }

            // Check for collision with bullets from aliens on obstacle
            for (Alien alien : aliens) {
                List<Bullet> alienBullets = alien.getAlienBullets();
                alienBullets.removeIf(bullet -> {
                    if (obstacle.intersects(bullet)) {
                        obstacle.hit();
                        return true; // Remove the bullet
                    }
                    return false;
                });
            }

            // Check for collision obstacle and alien
            Iterator<Alien> alienIterator = aliens.iterator();
            while (alienIterator.hasNext()) {
                Alien alien = alienIterator.next();
                if (obstacle.intersects(alien)) {
                    obstacleIterator.remove(); // Remove the obstacle
                    alienIterator.remove(); // Remove the alien
                }
            }
        }

        Iterator<Alien> alienIterator = aliens.iterator();
        while (alienIterator.hasNext()) {
            Alien alien = alienIterator.next();
            alien.update();

            // Check for collision with alien and player
            if (player.intersects(alien)) {

                // Handle collision with player (game over)
                System.out.println(GameState.getInstance().getScore());
                System.out.println("Game Over!");
                System.exit(0);
            }

            // Check for collision with alien and bullets
            Iterator<Bullet> bulletIterator = bullets.iterator();
            while (bulletIterator.hasNext()) {
                Bullet bullet = bulletIterator.next();
                if (alien.intersects(bullet)) {
                    bulletIterator.remove();
                    alienIterator.remove();
                    GameState.getInstance().increaseScore(10);
                }
            }

            // Check for edge and move the group downwards
            if (alien.getX() <= 0 || alien.getX() + 30 >= getWidth()) {
                moveAliensDown();
                break; // Move down
            }

            if (aliens.isEmpty()) {
                // Reset the game
                resetGame();
            }
        }
        // Update player bullets
        Iterator<Bullet> playerBulletIterator = bullets.iterator();
        while (playerBulletIterator.hasNext()) {
            Bullet bullet = playerBulletIterator.next();
            bullet.update();
            if (bullet.getY() < 0) {
                playerBulletIterator.remove();
            }
        }

        // Update alien bullets
        for (Alien alien : aliens) {
            List<Bullet> alienBullets = alien.getAlienBullets();
            Iterator<Bullet> bulletIterator = alienBullets.iterator();
            while (bulletIterator.hasNext()) {
                Bullet bullet = bulletIterator.next();
                bullet.update();
                if (player.intersects(bullet)) {
                    // Handle collision with player (game over or life lost)
                    GameState.getInstance().decreaseLives(); // Reduce a life when hit
                    if (GameState.getInstance().getLives() == 0) {
                        System.out.println(GameState.getInstance().getScore());
                        System.out.println("Game Over!");
                        System.exit(0);
                    } else {
                        // Reset player position and remove bullets on collision
                        player = GameObjectFactory.createPlayer(400, 500);
                        bullets.clear();
                    }
                }
                if (bullet.getY() > getHeight()) {
                    bulletIterator.remove();
                }
            }
        }
        // Remove bullets that go off-screen
        bullets.removeIf(bullet -> bullet.getY() < 0);
    }

    /**
     * Moves the aliens down and reverses their horizontal movement direction.
     * This method is called when aliens reach the edge of the game panel.
     */

    private final void moveAliensDown() {
        alienDirection *= -1; // Change horizontal direction
        for (Alien alien : aliens) {
            alien.moveDown();
        }
    }

    /**
     * Resets the game to its initial state.
     * This method is called when the game is over or when the player wins.
     */

    private final void resetGame() {
        // Reset player, bullets, and obstacles
        player = GameObjectFactory.createPlayer(400, 500);
        bullets.clear();

        // Remove obstacles using Iterator
        Iterator<Obstacle> obstacleIterator = obstacles.iterator();
        while (obstacleIterator.hasNext()) {
            Obstacle obstacle = obstacleIterator.next();
            obstacleIterator.remove();
        }

        // Remove aliens using Iterator
        Iterator<Alien> alienIterator = aliens.iterator();
        while (alienIterator.hasNext()) {
            Alien alien = alienIterator.next();
            alienIterator.remove();
        }

        // Initialise new aliens
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                aliens.add(GameObjectFactory.createAlien(50 + col * 100, 50 + row * 50));
            }
        }

        // Initialise obstacles
        for (int i = 0; i < 5; i++) {
            obstacles.add(GameObjectFactory.createObstacle());
        }

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        timer.start();

        totalAliens = aliens.size(); // Update totalAliens with the new count
        System.out.println("Game Reset!");
    }

    /**
     * Paints the game components onto the panel.
     * This method is called by the Swing framework to render the game graphics.
     *
     * @param g The Graphics object used for drawing.
     */

    @Override
    protected final void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);

        // Render the player
        gameRenderer.render(g2d, player);

        // Render each obstacle
        for (Obstacle obstacle : obstacles) {
            gameRenderer.render(g2d, obstacle);
        }

        // Render each alien and their bullets
        for (Alien alien : aliens) {
            gameRenderer.render(g2d, alien);
            for (Bullet bullet : alien.getAlienBullets()) {
                gameRenderer.render(g2d, bullet);
            }
        }

        // Render player's bullets
        g2d.setColor(Color.WHITE);
        for (Bullet bullet : bullets) {
            gameRenderer.render(g2d, bullet);
        }

        // Display the score
        g2d.setColor(Color.WHITE);
        g2d.drawString("Score: " + GameState.getInstance().getScore(), 10, 20);
        g2d.drawString("Lives: " + GameState.getInstance().getLives(), 10, 40);
    }

    /**
     * Handles key-typed events. This method is part of the KeyListener interface.
     *
     * @param e The KeyEvent object representing the key event.
     */

    @Override
    public final void keyTyped(KeyEvent e) {
    }

    /**
     * Handles key-pressed events for player movement and actions.
     * This method is part of the KeyListener interface.
     *
     * @param e The KeyEvent object representing the key event.
     */

    @Override
    public final void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bullets.add(GameObjectFactory.createBullet(player.getX() + 20, player.getY(), -1));
        }
    }

    /**
     * Handles key-released events. This method is part of the KeyListener
     * interface.
     *
     * @param e The KeyEvent object representing the key event.
     */

    @Override
    public final void keyReleased(KeyEvent e) {
    }
}