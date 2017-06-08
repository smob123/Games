package flappy.bird;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Barriers extends JPanel
{
    private boolean play = false;
    private int x, y_top, y_bottom; //pipes' y coordinates.
    private int points = 0; //number of points player has scored.
    private boolean in; //checks if player hits a pipe.
    private Random rand = new Random(5);
    private Random gen = new Random(5);
    private ImageIcon pipes1, pipes2, pipes3; //different pipe positions.
    private Rectangle top_boundries, bottom_boundries; //boundries surronding the pipes.
    private int state;
    
    public Barriers()
    {
        y_top = 0;
        y_bottom = 500;
        this.x = x;
        
        pipes();
        top_boundries = new Rectangle();
        bottom_boundries = new Rectangle();
        
        state = gen.nextInt(3);
    }
    
    public void pipes()
    {
        pipes1 = new ImageIcon("src\\resources\\b1.png");

        pipes2 = new ImageIcon("src\\resources\\b2.png");
        
        pipes3 = new ImageIcon("src\\resources\\b3.png");
    }
    
    public void draw(Graphics g, boolean playing)
    {
        g.setColor(Color.blue);
        g.drawString("Points: " + points , 10, 20);
        
        if(playing && !in)
        {
            switch(state)
            {
                case 0:
            pipes1.paintIcon(this, g, x, y_top);
            top_boundries.setSize(140, 115);
            top_boundries.setLocation(x + 3, y_top);
            
            
            bottom_boundries.setSize(145, 350);
            bottom_boundries.setLocation(x + 5, y_bottom - 145);
            break;
            
            case 1:
            pipes2.paintIcon(this, g, x, y_top);
            top_boundries.setSize(140, 250);
            top_boundries.setLocation(x + 3, y_top - 40);
            
            bottom_boundries.setSize(140, 300);
            bottom_boundries.setLocation(x + 5, y_bottom - 45);
            break;
            
            case 2:
               pipes3.paintIcon(this, g, x, y_top);
               top_boundries.setSize(130, 300);
               top_boundries.setLocation(x + 5, y_top );
               
               bottom_boundries.setSize(130, 200);
               bottom_boundries.setLocation(x + 5, y_bottom);
            break;
            }
            
        play = true;
        }
        else
            if(in)
            {
             playing = false;
             play = false;
             points = 0;
            }
        
        while(x <= -10)
            {
                x = rand.nextInt(900) + 600;
                state = gen.nextInt(3);
            }
    }
    
    public void Points(Rectangle rect)
    {
        if(rect.x >= x && rect.x <= x + 3)
            if(!rect.intersects(top_boundries) && !rect.intersects(bottom_boundries))
                points += 100;
    }
    
    public boolean inRange(Rectangle rect)
    {
        in = false;
        
        if(rect.intersects(top_boundries) || rect.intersects(bottom_boundries))
        {
            in = true;
            return true;
        }
        else
            return false;
    }
    
    public void update()
    {
        if(play)
            {
            x -= 3;
            }
            
            else
              x = 700;
    }
    }
