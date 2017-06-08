package flappy.bird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel
{
    private final int x = 100; //Character's x pos
    private int y = 230; //Character's y pos
    private Barriers bar;
    private final int inc = 70;
    private Timer time;
    private boolean run = false, flapping;
    private Rectangle boundries;
    private ImageIcon bird, clouds, grass;
    private boolean game_over;
    private JLabel label;
    private int cx = 1000; //clouds' x position
    private int gx = 0; //grasses' x positions

    public Game()
    {
        setPreferredSize(new Dimension(1000, 695));
        setBackground(Color.cyan);
        boundries = new Rectangle();
         bar = new Barriers();
         
         label = new JLabel();
         
         clouds = new ImageIcon("src\\resources\\clouds.png");
         bird = new ImageIcon("src\\resources\\bird.png");
         grass = new ImageIcon("src\\resources\\grass.png");
         time = new Timer(5, new TimeListener());
         
         addKeyListener(new KeyboardListenr());
         setFocusable(true);
         time.start();
         
         add(label);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        drawEnvironment(g);
        
        repaint();
    }
    
    public void drawEnvironment(Graphics g)
    {
        clouds.paintIcon(this, g, cx, 0);
        grass.paintIcon(this, g, gx, 150);
        
        bird.paintIcon(this, g, x, y);
        boundries.setSize(130, 90);
        boundries.setLocation(bird.getIconWidth() - 40, y + 15);
       
        bar.draw(g, run);
    }
    
    public void GameOver()
    {
        if(bar.inRange(boundries))
        {
            run = false;
            game_over = true;
        }
        
    }
    
    private class TimeListener implements ActionListener
    {    
        
        public void actionPerformed(ActionEvent event)
        {  
            
            if(run)
            {
              bar.update();
              if(!flapping)
                  y += 1;
              
            cx -= 2;
            gx -= 2;
            }
            
            if(cx == -50)
                cx = 1000;
            
            if(gx <= -100)
                gx = 0;
            
            bar.Points(boundries);
            
            if(y >= 700)
            {
                label.setForeground(Color.red);
                label.setFont(new Font("Helvetica", Font.BOLD, 100));
                label.setText("Game Over");
                y = 700;
                game_over = true;
                repaint();
            }
            else
                if(y <= 0)
                  y = 0;
            else
                {
                label.setText("");
                }
            
            GameOver();
            
            if(game_over)
            {
                y += 5;
            }
        }
    }
    
    private class KeyboardListenr extends KeyAdapter
    {
        public void keyPressed(KeyEvent event)
        {
            
            switch(event.getKeyCode())
            {
                case KeyEvent.VK_SPACE:
                
                run = true;
                flapping = true;
                
                y -= inc;
                
                if(game_over)
            {
                game_over = false;
                y = 250;
            }
                break;
            }
            
            flapping = false;
        
        }
}
}