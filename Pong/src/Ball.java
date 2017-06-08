
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;

public class Ball {

    private int x, y, moveX, moveY; //ball's coordinates
    private boolean score = false; //checks if a player has scored
    private Rectangle rect;

    public Ball() {
        x = 250;
        y = 250;
        moveX = moveY = 4; //ball's movement speed
        rect = new Rectangle();
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, 30, 30);
        rect.setBounds(x + 2, y + 3, 25, 25);

    }

    //bounce of top and bottom eadges, and if being blocked by players
    public void rebound(Rectangle p, Rectangle Op) {
        if (rect.intersects(p) || rect.intersects(Op)) {
            moveX *= -1;
        }
    }

    public boolean playerScored(int x) {
        if (rect.x >= x) {
            score = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean opponentScored() {
        if (rect.x <= 0) {
            score = true;
            return true;
        } else {
            return false;
        }
    }

    public int getY() {
        return y;
    }

    public void update(boolean playing) {
        if (playing) {
            x += moveX;
            y += moveY;
        }

        if (score) {
            //respawn in the middle of the screen
            x = 250;
            y = 250;
            score = false;
        }

        if (y <= 0 || y >= 480) {
            moveY *= -1;
        }
    }
}
