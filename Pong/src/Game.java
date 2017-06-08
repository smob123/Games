
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;

public class Game extends JPanel {

    private final int x = 0; //player's x coordinate
    private int y = 250; //player's y coordinate
    private int oneScore = 0, twoScore = 0; //scores for both player and AI
    private Opponent op;
    private Ball ball;
    private Rectangle player;
    private Point p; //Mouse location
    private boolean play = false; //determens when game starts
    private Timer time;
    private JLabel label; //displays the player who scored

    public Game() {
        setPreferredSize(new Dimension(602, 500));
        setBackground(Color.black);

        op = new Opponent();
        ball = new Ball();

        label = new JLabel();

        MouseListner ml = new MouseListner();
        addMouseListener(ml);
        addMouseMotionListener(ml);

        add(label);

        time = new Timer(10, new TimeListenr());
        time.start();
    }

    public void play(Graphics g) {
        g.setColor(Color.white);

        player = new Rectangle(x, y, 15, 70);
        g.fillRect(player.x, player.y, player.width, player.height);

        //op = new Opponent();
        op.draw(g, twoScore);

        if (play) {
            ball.draw(g);
            ball.rebound(player, op.rect);
            //op.follow(ball.getY());

            if (ball.opponentScored()) {
                twoScore++;
                play = false;
                label.setForeground(Color.red);
                label.setFont(new Font("Helvetica", Font.BOLD, 20));
                label.setText("Opponent has scored");
            } else if (ball.playerScored(getWidth())) {
                oneScore++;
                play = false;
                label.setForeground(Color.blue);
                label.setFont(new Font("Helvetica", Font.BOLD, 20));
                label.setText("You have scored");
            }

        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.blue);
        g.drawString("Player 1 score: " + oneScore, 10, 20); //display player's score
        play(g);

        repaint();
    }

    private class TimeListenr implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            ball.update(play);
            if (play) {
                op.follow(ball.getY());
            }

            repaint();
        }
    }

    private class MouseListner extends MouseAdapter {

        public void mouseClicked(MouseEvent event) {
            label.setText("");
            play = true; //start game when mouse is clicked
        }

        public void mouseMoved(MouseEvent event) {
            //capture mouse's vertical movement
            p = event.getPoint();
            y = p.y;

            repaint();
        }
    }
}
