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
    
    private JPanel panel2;
    private JFrame frame2;
    
    private ResidentReadout readout;
    
    private Tower tower;
    
    private int frameDelay = 200;
    
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
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.black);
        
        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
        panel2.setBackground(Color.black);
        
        int b = (int)(Math.random() * 4);
        
        if (b == 0)
            b = 1;
            
        readout = new ResidentReadout();
           
        frame = new JFrame();
        
        JFrame frame2 = new JFrame();
        
        tower = new Tower(frame,0,0,150,300,3,50,100,frameDelay);
        
        panel.add(tower);
        
        panel2.add(readout);
        
        // create the window itself
        frame.setTitle("Tower");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(panel);
        frame.setVisible(true);
        
        frame2.add(panel2);
        frame2.setVisible(true);
        
        while (keepGoing == true)
        {
            readout.setResidents(tower.getAllResidents());
            tower.moveElevatorDown();
            tower.updateResidents();
            tower.addResident(); //assuming there is a space open
            //tower.getAwayResidents();
            
            if (tower.checkFull() == true)
            {
                tower.extendFloors(1,1);
            }
            
            frame.repaint();
            Thread.sleep(frameDelay);
        }
    }
}