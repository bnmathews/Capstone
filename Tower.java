import java.awt.*;
import javax.swing.JPanel;
import java.util.Random; 

/**
 * Panel
 * 
 * @author @bnmathews
 * @version 30 March 2015
 */
public class Tower extends JPanel
{
    // the dimensions of the Tree Panel
    private final int PANEL_WIDTH = 1000;
    private final int PANEL_HEIGHT = 1000;
    
    private int startingFloors;
    private int startingRooms;
    private Room[][] rooms;
    
    public Tower(int floors,int rooms)
    {
        startingFloors = floors;
        startingRooms = rooms;
        setBackground(Color.black);
        makeFloors(floors,rooms);
    }
   
    public void makeNewColor()
    {
        // create a new Random object
        Random r = new Random();
        
        // make a new Color based on a full range of random integers created by object r
        Color newColor = new Color(r.nextInt(255-1),r.nextInt(255-1),r.nextInt(255-1));
    }
    
    public Room[][] makeFloors(int numFloors, int numRooms)
    {
        rooms = new Room[numFloors][numRooms];
        for (int row = 0; row < rooms.length; row++)
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                if (col % 2 == 0)
                {
                    Room elevator = new Elevator();
                    insertRoom(elevator,col);
                    System.out.println("Elevator added");
                }
                else
                {
                    rooms[row][col] = new Room();
                }
            }
        }
        
        return rooms;
    }
    
    public void extendFloors()
    {
        Room[][] newRooms = new Room[rooms.length][rooms[1].length+1];
        for (int row = 0; row < rooms.length; row++)
        {
            for(int col = 0; col < rooms[row].length; col++)
            {
                newRooms[row][col] = rooms[row][col];
            }
        }
        rooms = newRooms;
    }
    
    public void insertRoom(Room roomToInsert, int insertionPoint)
    {
        extendFloors(); // should create an null Room at the end of the 2d array
            for (int row = 0; row < rooms.length; row++)
            {
                for (int col = 0; col <= insertionPoint; col++)
                {
                    if (col == insertionPoint)
                    {
                        Room tempRoom = rooms[row][col];
                        for(int c2 = insertionPoint; c2 < rooms[1].length; c2++)
                        {
                            tempRoom = rooms[row][c2];
                            rooms[row][c2] = tempRoom;
                        }
                        rooms[row][col] = roomToInsert;
                    }
                }
            }
    }
    
    public void displayFloors()
    {
        System.out.println();
        for (int row = 0; row < rooms.length; row++)
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                if (rooms[row][col] != null)
                {
                    System.out.print(rooms[row][col].getName());
                }
                else
                    System.out.print("n");
            }
            System.out.println();
        }
    }
    
    public void paintComponent (Graphics page)
    {
        super.paintComponent(page);
        page.setColor (Color.white);
        drawBlock(page);
    }
    
    public void drawBlock(Graphics page)
    {
        int increase = 0;
        int heightIncrease = 0;
        System.out.println("Current Floor Plan:");
        displayFloors();
        for (int row = 0; row < rooms.length; row++)
        {
            for (int col = 0; col < rooms[row].length; col++)
            {
                /*
                if (col % 2 == 0)
                {
                    extendFloors();
                    Room elevator = new Elevator();
                    insertRoom(elevator,col);
                    System.out.println("Elevator added");
                }
                */
                //else
                //{
                    if (rooms[row][col] != null)
                    {
                        rooms[row][col].constructRoom(page,increase,heightIncrease);
                        increase+=30;
                    }
                    
                //}
            }
            increase = 0;
            heightIncrease+=30;
        }
        System.out.println("Updated Floor Plan:");
        displayFloors();
    }
}
