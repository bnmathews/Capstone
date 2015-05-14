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
    private JFrame frame2;
    
    private ResidentReadout readout;
    
    private Tower tower;
    
    private int frameDelay = 500;
    
    public Viewer()
    throws InterruptedException
    {
        boolean keepGoing = true;
        
        // create the master panel
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.black);
        
        int b = (int)(Math.random() * 4);
        
        if (b == 0)
            b = 1;
            
        readout = new ResidentReadout();
        readout.setLayout(new BoxLayout(readout,BoxLayout.Y_AXIS));
           
        frame = new JFrame();
        
        JFrame frame2 = new JFrame();
        frame2.setSize(WIDTH, HEIGHT);
        
        tower = new Tower(frame,frame2,0,0,150,300,3,20,50,frameDelay);
        
        panel.add(tower);
        
        frame2.add(readout);
        frame2.setVisible(true);
        
        // create the window itself
        frame.setTitle("Tower");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(panel);
        frame.setVisible(true);
        
        
        
        while (keepGoing == true)
        {
            tower.moveElevatorDown();
            tower.updateResidents();
            tower.addResident(); //assuming there is a space open
            //tower.getAwayResidents();
            
            readout.setResidents(tower.getAllResidents());
            /*
            if (tower.checkFull() == true)
            {
                //tower.extendFloors(1,1);
            }
            */
            
            frame.repaint();
            frame2.repaint();
            Thread.sleep(frameDelay);
        }
    }
    
    public static void main(String[] args)
    throws InterruptedException
    {
        Viewer viewer = new Viewer();
    }
}