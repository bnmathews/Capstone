import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Viewer
 * 
 * @author @bnmathews
 * @version 30 March 2015
 */
public class Viewer
{
    // the dimensions of the window itself
    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    
    private JPanel panel;
    private JFrame frame;
    
    private Tower tower;
    public static void main(String[] args)
    throws InterruptedException
    {
        Viewer viewer = new Viewer();
    }
    
    public Viewer()
    throws InterruptedException
    {
        boolean keepGoing = true;
        
        // create the master panel
        panel = new JPanel();
        panel.setBackground(Color.black);
        
        int b = (int)(Math.random() * 4);
        
        if (b == 0)
            b = 1;
            
        
        
        // create the window itself
        frame = new JFrame();
        frame.setTitle("Not a Fractal Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(panel);
        
        tower = new Tower(frame,0,0,150,300,3,5,10);
        
        frame.add(tower);
        frame.setVisible(true);
        
        while (keepGoing == true)
        {
            tower.moveElevatorDown();
            tower.updateResidents();
            //tower.addResident(); //assuming there is a space open
            //tower.getAwayResidents();
            frame.repaint();
            Thread.sleep(100);
        }
    }
}