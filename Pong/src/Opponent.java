
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class Opponent extends JPanel {

    private final int x = 587; //AI's x coordinate
    private int y = 250; // AI's y coordinate
    Rectangle rect;

    public void draw(Graphics g, int score) {
        rect = new Rectangle(x, y, 15, 70);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);

        g.setColor(Color.red);
        g.drawString("Player 2 score: " + score, 470, 20); //display opponent's score
    }

    public void follow(int direction) {
        y = direction; //follow the ball's vertical movement
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
