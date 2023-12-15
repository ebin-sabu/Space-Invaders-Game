import java.awt.Graphics2D;

public final class GameRenderer {
    // Interface for handling rendering
    interface RenderHandler {
        void render(Graphics2D g2d, Object object, RenderHandler nextHandler);

        void setNext(RenderHandler nextHandler);
    }

    // Obstacle Renderer as an inner class
    final class ObstacleRenderer implements RenderHandler {
        private RenderHandler next;

        public final void render(Graphics2D g2d, Object object, RenderHandler nextHandler) {
            if (object instanceof Obstacle) {
                ((Obstacle) object).render(g2d);
            } else if (next != null) {
                next.render(g2d, object, next);
            }
        }

        public final void setNext(RenderHandler nextHandler) {
            this.next = nextHandler;
        }
    }

    // Player Renderer as an inner class
    final class PlayerRenderer implements RenderHandler {
        private RenderHandler next;

        public final void render(Graphics2D g2d, Object object, RenderHandler nextHandler) {
            if (object instanceof Player) {
                ((Player) object).render(g2d);
            } else if (next != null) {
                next.render(g2d, object, next);
            }
        }

        public final void setNext(RenderHandler nextHandler) {
            this.next = nextHandler;
        }
    }

    // Alien Renderer as an inner class
    final class AlienRenderer implements RenderHandler {
        private RenderHandler next;

        public final void render(Graphics2D g2d, Object object, RenderHandler nextHandler) {
            if (object instanceof Alien) {
                ((Alien) object).render(g2d);
            } else if (next != null) {
                next.render(g2d, object, next);
            }
        }

        public final void setNext(RenderHandler nextHandler) {
            this.next = nextHandler;
        }
    }

    // Bullet Renderer as an inner class
    final class BulletRenderer implements RenderHandler {
        private RenderHandler next;

        public final void render(Graphics2D g2d, Object object, RenderHandler nextHandler) {
            if (object instanceof Bullet) {
                ((Bullet) object).render(g2d);
            } else if (next != null) {
                next.render(g2d, object, next);
            }
        }

        public final void setNext(RenderHandler nextHandler) {
            this.next = nextHandler;
        }
    }

    // Rest of the GameRenderer class
    private RenderHandler chain;

    /**
     * Constructor for GameRenderer.
     * Initializes the chain of responsibility for rendering.
     */

    public GameRenderer() {
        // Building the chain of responsibility
        this.chain = new ObstacleRenderer();
        RenderHandler playerRenderer = new PlayerRenderer();
        RenderHandler alienRenderer = new AlienRenderer();
        RenderHandler bulletRenderer = new BulletRenderer();

        this.chain.setNext(playerRenderer);
        playerRenderer.setNext(alienRenderer);
        alienRenderer.setNext(bulletRenderer);
        // Additional renderers can be linked here
    }

    public final void render(Graphics2D g2d, Object object) {
        chain.render(g2d, object, chain);
    }
}
