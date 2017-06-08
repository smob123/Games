package flappy.bird;

import javax.swing.*;

public class FlappyBird
{

    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Game game = new Game();
        
        frame.getContentPane().add(game);
        frame.pack();
        frame.setVisible(true);
        
    }
}
