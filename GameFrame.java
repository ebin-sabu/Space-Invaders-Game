
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class GameFrame extends JFrame {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    /**
     * Constructor to create and set up the game frame.
     */

    public GameFrame() {
        setTitle("Space Invaders");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.update();
                gamePanel.repaint();
            }
        });
        timer.start();
    }
}
