import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Tower Viewer
 * 
 * @author @bnmathews
 * @version 14 May 2015
 */
public class Viewer
{
    // the dimensions of the window itself
    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    
    private JPanel panel;
    private JFrame frame;
    private JFrame frame2;
    
    // the display of the resident's lives
    private ResidentReadout readout;
    
    // the building
    private Tower tower;
    
    // the amount of time (ms) taken in between frames
    private int frameDelay = 300;
    
    // the X position where the bottom left of the tower will be horizontally
    private int startingX = 150;
    
    // the Y position where the bottom left of the tower will be vertically
    private int startingY = 300;
    
    // amount of floors (rows) to give the tower
    private int startingFloors = 7;
    
    // amount of rooms (columns) to give the tower
    private int startingRooms = 7;
    
    // minimum amount of time (ms) a resident should spend out of the building
    private int res_minTimeOut = 20;
    
    // maximum amount of time (ms) a resident should spend out of the building
    private int res_maxTimeOut = 50;
    
    public Viewer()
    throws InterruptedException
    {
        boolean keepGoing = true;
        
        // create the master panel
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.setBackground(Color.black);
        
        // create the display of resident's actions
        readout = new ResidentReadout();
        readout.setLayout(new BoxLayout(readout,BoxLayout.Y_AXIS));
           
        frame = new JFrame();
        
        // creates the readout window
        JFrame frame2 = new JFrame();
        frame2.setTitle("Resident Lives");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(400, 500);
        
        tower = new Tower(frame,frame2,startingFloors,startingRooms,startingX,startingY,res_minTimeOut,res_maxTimeOut,frameDelay);
        
        panel.add(tower);
        
        frame2.add(readout);
        frame2.setVisible(true);
        
        // create the tower window
        frame.setTitle("Tower");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(panel);
        frame.setVisible(true);
        
        
        while (keepGoing == true)
        {
            tower.moveElevatorDown();
            tower.updateResidents();
            
            // attempts to add a resident to the tower
            tower.addResident();
            
            //updates the readout screen with info on the current residents
            readout.setResidents(tower.getAllResidents());
            
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