
import javax.swing.*;

public class Driver {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = new Game();

        frame.getContentPane().add(game);
        frame.pack();
        frame.setVisible(true);
    }
}
